package esrever.objekty;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

import esrever.framework.HraObjekt;
import esrever.framework.ObjektID;
	
public class Monster extends HraObjekt{						//Tøída na Objekt ("Monstrum")

	
	public Monster(float x, float y, ObjektID id) {
		super(x, y, id);
		
	}
	
	public void tick(LinkedList<HraObjekt> object) {
		
	}
	
	public void render(Graphics g) {						//Z dùvodù vysvìtlených v dokumentaci jsem udìlal "Pixer art" aby "monstrum" vypadalo malinko lépe.
		g.setColor(Color.DARK_GRAY);
		g.fillOval((int)x+35, (int)y+30, 25, 4);
		g.setColor(Color.GRAY);
		g.fillOval((int)x+10, (int)y, 30, 15);
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect((int)x, (int)y+9, 40, 30);
		g.setColor(Color.BLACK);
		g.fillRect((int)x+3, (int)y+13, 8, 8);
		g.setColor(Color.WHITE);
		g.fillRect((int)x+4, (int)y+14, 4, 4);
		g.setColor(Color.GRAY);
		g.fillOval((int)x+10, (int)y+20, 20, 10);
	}

	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y+9, 40, 30);
	}
}
