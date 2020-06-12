package esrever.objekty;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import esrever.framework.HraObjekt;
import esrever.framework.ObjektID;

public class Block extends HraObjekt {			//Tøída na Objekt (Blok)
	
	
	public Block(float x, float y, ObjektID id) {
		super(x, y, id);
	}

	public void tick(LinkedList<HraObjekt> object) {
		
	}
	
	public void render(Graphics g) {								//Z dùvodù vysvìtlených v dokumentaci jsem udìlal "Pixer art" aby block vypadala malinko lépe.
		Color Brown=new Color (205,133,63);	
		g.setColor(Brown);
		g.fillRect((int)x, (int)y, 40, 40);
		g.setColor(Color.GRAY);
		g.fillRect((int)x, (int)y+5, 40, 8);
		g.fillRect((int)x, (int)y+25, 40, 8);
	}

	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, 40, 40);
	}

}
