package esrever;

import java.awt.Graphics;
import java.util.LinkedList;

import esrever.framework.HraObjekt;
import esrever.framework.ObjektID;
import esrever.objekty.Block;

@SuppressWarnings("unused")
public class Handler {														//T��da hlavn� na d�l�n� objekt�
	public LinkedList<HraObjekt> objekt = new LinkedList<HraObjekt>();
	
	private HraObjekt tempObjekt;
	
	public void tick() {
		for(int i =0; i < objekt.size(); i++) {
			tempObjekt = objekt.get(i);
			
			tempObjekt.tick(objekt);
		}
	}
	
	public void render(Graphics g) {
		for(int i =0; i < objekt.size(); i++) {
			tempObjekt = objekt.get(i);
			
			tempObjekt.render(g);
		}
	}
	
	public void addObjekt(HraObjekt objekt) {
		this.objekt.add(objekt);
	}
	public void removeObjekt(HraObjekt objekt) {
		this.objekt.remove(objekt);
	}
	
	public void createLevel() {												//Pou��v�no na test kolize p�ed seps�n�m k�du na d�l�n� levelu podle barvy pixel� v obr�zku
		//for(int xx=0; xx<Hra.WIDTH*2; xx += 40)
			//addObjekt(new Block(xx, Hra.HEIGHT-100, ObjektID.Block));
		
		//for(int xx=0; xx<Hra.WIDTH*2; xx += 40)
			//addObjekt(new Block(xx, 60, ObjektID.Block));
		
		//for(int xx=60; xx<Hra.HEIGHT-100; xx += 40)
			//addObjekt(new Block(0, xx, ObjektID.Block));
		
		//addObjekt(new Block(400, Hra.HEIGHT-220, ObjektID.Block));     	//-140 je 1 block vysoko
		//addObjekt(new Block(720, Hra.HEIGHT-220, ObjektID.Block));		//mezera max 6 blok� na preskok
		//addObjekt(new Block(680, Hra.HEIGHT-260, ObjektID.Block));
	}
}
