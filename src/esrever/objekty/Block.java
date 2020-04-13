package esrever.objekty;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.File;
import java.util.LinkedList;

import esrever.Hra;
import esrever.framework.HraObjekt;
import esrever.framework.ObjektID;
import esrever.framework.Texture;

public class Block extends HraObjekt {			//Tøída na Objekt (Blok)
	
	Texture tex = Hra.getInstance();
	private int type;
	
	public Block(float x, float y, ObjektID id) {
		super(x, y, id);
	}

	public void tick(LinkedList<HraObjekt> object) {
		
	}

	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.drawRect((int)x, (int)y, 40, 40);
	}

	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 40, 40);
	}

}
