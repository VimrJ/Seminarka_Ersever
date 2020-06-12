package esrever;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;

import esrever.framework.HraObjekt;
import esrever.framework.ObjektID;
import esrever.objekty.Block;
import esrever.objekty.Coin;
import esrever.objekty.Flag;
import esrever.objekty.Hrac;
import esrever.objekty.Lava;
import esrever.objekty.Monster;
import esrever.objekty.Water;

@SuppressWarnings("unused")
public class Handler {														//Tøída hlavnì na dìlání objektù
	public LinkedList<HraObjekt> objekt = new LinkedList<HraObjekt>();
	
	private HraObjekt tempObjekt;
	
	private Kamera kam;
	
	public Handler(Kamera kam) {
		this.kam = kam;
	}
	
	public void tick() {
		for(int i =0; i < objekt.size(); i++) {
			tempObjekt = objekt.get(i);
			
			tempObjekt.tick(objekt);
		}
	}
	
	public void render(Graphics g) {					//render objektù
		for(int i =0; i < objekt.size(); i++) {
			tempObjekt = objekt.get(i);
			
			tempObjekt.render(g);
		}
	}
	
	public void LoadImageLevel(BufferedImage level) {
		int w = level.getWidth();
		int h = level.getHeight();
		
		System.out.println(w +", "+ h);
		
		for(int xx = 0; xx < h; xx++) {					// Dvojitý for cyklus -> zjistí na kterem pixelu je a veme z toho RGB
			for(int yy = 0; yy < w; yy++) {
				int pixel = level.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red == 255 && green == 255 & blue == 255) addObjekt(new Block(xx*40, yy*40, ObjektID.Block));
				if(red == 255 && green == 242 & blue == 0) addObjekt(new Monster(xx*40, yy*40, ObjektID.Monster));
				if(red == 0 && green == 255 & blue == 0) addObjekt(new Coin(xx*40, yy*40, ObjektID.Coin));					//Dìlání z pixelu objekt
				if(red == 0 && green == 0 & blue == 255) addObjekt(new Hrac(xx*40, yy*40, this, kam,ObjektID.Hrac));
				if(red == 255 && green == 0 & blue == 0) addObjekt(new Lava(xx*40, yy*40, ObjektID.Lava));
				if(red == 0 && green == 162 & blue == 232) addObjekt(new Water(xx*40, yy*40, ObjektID.Water));
				if(red == 163 && green == 73 & blue == 164) addObjekt(new Flag(xx*40, yy*40, ObjektID.Flag));
			}
		}
	}
		
	
	public void addObjekt(HraObjekt objekt) {							//pøidávání objektù
		this.objekt.add(objekt);
	}
	public void removeObjekt(HraObjekt objekt) {						//mazání objektù
		this.objekt.remove(objekt);
	}
	
	public void createLevel() {												//Používáno na test kolize pøed sepsáním kódu na dìlání levelu podle barvy pixelù v obrázku
		//for(int xx=0; xx<Hra.WIDTH*2; xx += 40)
			//addObjekt(new Block(xx, Hra.HEIGHT-100, ObjektID.Block));
		
		//for(int xx=0; xx<Hra.WIDTH*2; xx += 40)
			//addObjekt(new Block(xx, 60, ObjektID.Block));
		
		//for(int xx=60; xx<Hra.HEIGHT-100; xx += 40)
			//addObjekt(new Block(0, xx, ObjektID.Block));
		
		//addObjekt(new Block(400, Hra.HEIGHT-220, ObjektID.Block));     	//-140 je 1 block vysoko
		//addObjekt(new Block(720, Hra.HEIGHT-220, ObjektID.Block));		//mezera max 6 blokù na preskok
		//addObjekt(new Block(680, Hra.HEIGHT-260, ObjektID.Block));
	}
}
