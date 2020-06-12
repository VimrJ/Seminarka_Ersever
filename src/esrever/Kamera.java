package esrever;

import esrever.framework.HraObjekt;

public class Kamera{						//T��da kamera, zaji��uj�c� pohyb kamery s hr��em, d�ky �emu� nen� velikost levelu omezen podle velikost rozli�en� okna
	
	private float x,y;
	
	public Kamera(float x, float y) {
		this.x=x;
		this.y=y;
	}
	
	
	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public void tick(HraObjekt Hrac) {
		
		x = -Hrac.getX() + Hra.WIDTH/2;
		y= -Hrac.getY() + Hra.HEIGHT/2;
		
	}
}
