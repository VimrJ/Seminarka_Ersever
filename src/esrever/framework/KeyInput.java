package esrever.framework;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import esrever.Handler;

public class KeyInput extends KeyAdapter{						//Tøída na Klávesovı input
	
	Handler handler;
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i<handler.objekt.size();i++) {
			HraObjekt tempObjekt = handler.objekt.get(i);
			
			if(tempObjekt.getID()==ObjektID.Hrac) {									//Hrac skoci max 6 bloku s "double jumpem" lze i 7 double jump lze udelat jen kdy je hraè na bloku ve vıšce, bez vıskoku se hne do strany a a poté skoèí.
				if(key == KeyEvent.VK_D) tempObjekt.setVelX(5);
				if(key == KeyEvent.VK_A) tempObjekt.setVelX(-5);
				if(key == KeyEvent.VK_SPACE && !tempObjekt.isJumping()){
					tempObjekt.setJumping(true);
					tempObjekt.setVelY(-12);
				}
				if(key == KeyEvent.VK_RIGHT) tempObjekt.setVelX(5);
				if(key == KeyEvent.VK_LEFT) tempObjekt.setVelX(-5);
				if(key == KeyEvent.VK_W && !tempObjekt.isJumping()){
					tempObjekt.setJumping(true);
					tempObjekt.setVelY(-12);
				}
				if(key == KeyEvent.VK_UP && !tempObjekt.isJumping()){
					tempObjekt.setJumping(true);
					tempObjekt.setVelY(-12);
				}
				
			}
		}
		
		if(key == KeyEvent.VK_ESCAPE) {
			System.exit(1);
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		
		for(int i = 0; i<handler.objekt.size();i++) {
			HraObjekt tempObjekt = handler.objekt.get(i);
			
			if(tempObjekt.getID()==ObjektID.Hrac) {
				if(key == KeyEvent.VK_D) tempObjekt.setVelX(0);
				if(key == KeyEvent.VK_A) tempObjekt.setVelX(0);
				if(key == KeyEvent.VK_RIGHT) tempObjekt.setVelX(0);
				if(key == KeyEvent.VK_LEFT) tempObjekt.setVelX(0);
				
			}
		}
	}

}
