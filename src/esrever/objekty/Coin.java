package esrever.objekty;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import esrever.Hra;
import esrever.framework.HraObjekt;
import esrever.framework.ObjektID;
import esrever.framework.Texture;

public class Coin extends HraObjekt{
	
	Texture tex = Hra.getInstance();
	private int type;
	
	public Coin(float x, float y, ObjektID id) {
		super(x, y, id);
		
	}
	
	public void tick(LinkedList<HraObjekt> object) {
		
	}

	public void render(Graphics g) {
		g.setColor(Color.PINK);
		g.drawRect((int)x+10, (int)y+10, 20, 20);
	}

	
	public Rectangle getBounds() {
		return new Rectangle((int)x+10, (int)y+10, 20, 20);
	}
}