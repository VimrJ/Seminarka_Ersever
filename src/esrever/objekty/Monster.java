package esrever.objekty;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;

import esrever.Hra;
import esrever.framework.HraObjekt;
import esrever.framework.ObjektID;
import esrever.framework.Texture;

public class Monster extends HraObjekt{
	
	Texture tex = Hra.getInstance();
	private int type;
	
	public Monster(float x, float y, ObjektID id) {
		super(x, y, id);
		
	}
	
	public void tick(LinkedList<HraObjekt> object) {
		
	}

	public void render(Graphics g) {
		g.setColor(Color.YELLOW);
		g.drawRect((int)x, (int)y+9, 40, 30);
	}

	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y+9, 40, 30);
	}
}
