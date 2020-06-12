package esrever.objekty;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;
import esrever.framework.HraObjekt;
import esrever.framework.ObjektID;

public class Coin extends HraObjekt{				//Tøída na Objekt (Coin)
	
	
	public Coin(float x, float y, ObjektID id) {
		super(x, y, id);
		
	}
	
	public void tick(LinkedList<HraObjekt> object) {				//Z dùvodù vysvìtlených v dokumentaci jsem udìlal "Pixer art" aby coina vypadala malinko lépe.
		
	}

	public void render(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect((int)x+10, (int)y+10, 20, 20);
		g.setColor(Color.BLACK);
		g.drawRect((int)x+15, (int)y+15, 10, 10);
		g.drawRect((int)x+15, (int)y+15, 9, 9);
	}

	
	public Rectangle getBounds() {
		return new Rectangle((int)x+10, (int)y+10, 20, 20);
	}
}