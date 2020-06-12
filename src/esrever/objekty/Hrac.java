package esrever.objekty;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;

import esrever.BufferedImageLoader;
import esrever.Handler;
import esrever.Hra;
import esrever.Kamera;
import esrever.framework.HraObjekt;
import esrever.framework.ObjektID;

public class Hrac extends HraObjekt{  //Tøída na Objekt (Hrac)
	
	private float width = 30, height=60;
	private float gravity = 0.55f;
	private final float MAX_SPEED = 10;
	private boolean lives=true;
	private Kamera kam;
	
	private BufferedImage level=null;
	private File f;
	
	private Handler handler;

	
	public Hrac(float x, float y, Handler handler, Kamera kam, ObjektID id) {
		super(x, y, id);
		this.handler=handler;
		this.kam=kam;
	}

	public void tick(LinkedList<HraObjekt> objekt) {
		x += velX;
		y += velY;
		
		if(falling || jumping) {
			velY += gravity;
			
			if(velY > MAX_SPEED)
				velY = MAX_SPEED;
		}
		
		Collision(objekt);
		
		if(lives==false) {													//podmínka, která zjišuje, jestli je hráè na ivu, pokud ne tak ho respawne tím e vytvoøí novou instanci hráèe
			f=new File("level.png");
			BufferedImageLoader loader = new BufferedImageLoader();
			level=loader.loadImage(f);
			handler.removeObjekt(this);
				int w = level.getWidth();
				int h = level.getHeight();
				
				for(int xx = 0; xx < h; xx++) {					
					for(int yy = 0; yy < w; yy++) {
						int pixel = level.getRGB(xx, yy);
						int red = (pixel >> 16) & 0xff;
						int green = (pixel >> 8) & 0xff;
						int blue = (pixel) & 0xff;
						if(red == 0 && green == 0 & blue == 255) handler.addObjekt(new Hrac(xx*40, yy*40, handler, kam, ObjektID.Hrac));
					}
				}
				lives=true;
			}
		}
	
	
	private void Collision(LinkedList<HraObjekt> objekt) {									//Øešení kolize. Dìlá rùzné vìci, podle druhu objektu s kterım se hráè setká
		for(int i = 0; i<handler.objekt.size();i++) {
			HraObjekt tempObjekt = handler.objekt.get(i);
			
			if(tempObjekt.getID()==ObjektID.Block) {
				
				if(getBounds().intersects(tempObjekt.getBounds())) {
					y = tempObjekt.getY() - height+1;
					velY = 0;
					falling = false;
					jumping = false;
				}else
				{
					falling = true;
				}
				
				if(getBoundsTop().intersects(tempObjekt.getBounds())) {
					y = tempObjekt.getY() + 40;
					velY = 0;
					
				}else
				{
					falling = true;
				}
				
				if(getBoundsRight().intersects(tempObjekt.getBounds())) {
					x = tempObjekt.getX() - width;
					
				}else
				{
					falling = true;
				}
				
				if(getBoundsLeft().intersects(tempObjekt.getBounds())) {
					x = tempObjekt.getX() + 40;
					
				}else
				{
					falling = true;
				}
			}
			if(tempObjekt.getID()==ObjektID.Monster) {
				
				if(getBounds().intersects(tempObjekt.getBounds())) {
					
					handler.removeObjekt(tempObjekt);
					monsterCount++;
					System.out.println(monsterCount);
				}
				if(getBoundsTop().intersects(tempObjekt.getBounds())) {
					
					handler.removeObjekt(tempObjekt);
					monsterCount++;
					System.out.println(monsterCount);
				}
				if(getBoundsLeft().intersects(tempObjekt.getBounds())) {
	
					handler.removeObjekt(tempObjekt);
					monsterCount++;
					System.out.println(monsterCount);
				}
				if(getBoundsRight().intersects(tempObjekt.getBounds())) {
					
					handler.removeObjekt(tempObjekt);
					monsterCount++;
					System.out.println(monsterCount);
				}
			
			}
			if(tempObjekt.getID()==ObjektID.Coin) {
				
				if(getBounds().intersects(tempObjekt.getBounds())) {
						lives=false;
				}
				if(getBoundsTop().intersects(tempObjekt.getBounds())) {
					lives=false;
			}
				if(getBoundsLeft().intersects(tempObjekt.getBounds())) {
					lives=false;
			}
				if(getBoundsRight().intersects(tempObjekt.getBounds())) {
					lives=false;
			}
			}
			if(tempObjekt.getID()==ObjektID.Water) {
				
				if(getBounds().intersects(tempObjekt.getBounds())) {
						lives=false;
				}
			}
			if(tempObjekt.getID()==ObjektID.Lava) {
				
				if(getBounds().intersects(tempObjekt.getBounds())) {
					y+=1000;
				}
			}
			if(tempObjekt.getID()==ObjektID.Flag) {
				//zmena levelu
				if(getBounds().intersects(tempObjekt.getBounds())) {
					Hra.LEVEL++;
					y+=2000;
				}
			}
		}
		}
		
		
	

	public void render(Graphics g) {									//Z dùvodù vysvìtlenıch v dokumentaci jsem udìlal "Pixer art" aby hráè vypadal malinko lépe.
			
		
		g.setColor(Color.GRAY);
		g.fillRect((int)x, (int) y,(int) width, (int)height);
		g.setColor(Color.WHITE);
		g.setColor(Color.BLACK);
		g.fillRect((int)x+19, (int)y+12, 8, 8);
		g.setColor(Color.WHITE);
		g.fillRect((int)x+22, (int)y+14, 4, 4);
		g.setColor(Color.YELLOW);
		g.fillRect((int)x, (int) y+3,(int) width, 7);
		g.fillRect((int)x, (int) y-3,(int) 4, 7);
		g.fillRect((int)x+9, (int) y-3,(int) 4, 7);
		g.fillRect((int)x+18, (int) y-3,(int) 4, 7);
		g.fillRect((int)x+26, (int) y-3,(int) 4, 7);
	}
	
	
	public Rectangle getBounds() {
		return new Rectangle((int) ((int)x+(width/2)-((width/2)/2)), (int) ((int) y+(height/2)),(int) width/2, (int)height/2);
	}
	
	public Rectangle getBoundsRight() {
		return new Rectangle((int) ((int)x+width-5), (int) y+5,(int) 5, (int)height-10);
	}
	
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x, (int) y+5,(int) 5, (int)height-10);
	}
	
	public Rectangle getBoundsTop() {
		return new Rectangle((int) ((int)x+(width/2)-((width/2)/2)), (int) y,(int) width/2, (int)height/2);
	}
	
}
