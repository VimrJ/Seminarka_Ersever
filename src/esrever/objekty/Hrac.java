package esrever.objekty;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.LinkedList;

import esrever.Handler;
import esrever.Hra;
import esrever.framework.HraObjekt;
import esrever.framework.ObjektID;
import esrever.framework.Texture;

public class Hrac extends HraObjekt{  //Tøída na Objekt (Hrac)
	
	private float width = 30, height=60;
	private float gravity = 0.55f;
	private final float MAX_SPEED = 10;
	private float zivoty=2;
	
	private Handler handler;

	Texture tex = Hra.getInstance();
	
	public Hrac(float x, float y, Handler handler, ObjektID id) {
		super(x, y, id);
		this.handler=handler;
	}

	public void tick(LinkedList<HraObjekt> objekt) {
		x +=velX;
		y += velY;
		
		if(falling || jumping) {
			velY += gravity;
			
			if(velY > MAX_SPEED)
				velY = MAX_SPEED;
		}
		
		Collision(objekt);
	}
	
	private void Collision(LinkedList<HraObjekt> objekt) {									//Øešení kolize
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
		}
	}

	public void render(Graphics g) {
		
		
		g.setColor(Color.GREEN);
		g.fillRect((int)x, (int) y,(int) width, (int)height);
		
		Graphics2D g2d = (Graphics2D) g;
		//g.setColor(Color.RED);	
		//g2d.draw(getBounds());
		//g2d.draw(getBoundsRight());
		//g2d.draw(getBoundsLeft());
		//g2d.draw(getBoundsTop());
		/*g2d.setColor(Color.WHITE);
		g2d.fillRect((int) x-640,(int) y - 380, 1280, 80);
		g2d.fillRect((int) x-640, (int) y + 300, 1280, 81);
		
		
		if(zivoty==3) {
			g.setColor(Color.RED);
			g2d.fillOval((int)x-630, (int) y + 310, 60, 60);
			g.setColor(Color.BLACK);
			int fontSize = 50;
			g.setFont(new Font("TimesRoman",Font.PLAIN, fontSize));
			g.drawString("3", (int) x-550, (int) y + 360); 
			
		}else if(zivoty==2){
			g.setColor(Color.RED);
			g2d.fillOval((int)x-630, (int) y + 310, 60, 60);
			g.setColor(Color.BLACK);
			int fontSize = 50;
			g.setFont(new Font("TimesRoman",Font.PLAIN, fontSize));
			g.drawString("2", (int) x-550, (int) y + 360); 
			
		}else if(zivoty==1){
			g.setColor(Color.RED);
			g2d.fillOval((int)x-630, (int) y + 310, 60, 60);
			g.setColor(Color.BLACK);
			int fontSize = 50;
			g.setFont(new Font("TimesRoman",Font.PLAIN, fontSize));
			g.drawString("1", (int) x-550, (int) y + 360); 
			
		}else System.out.println("MRTEV");*/
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
