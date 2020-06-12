package esrever;

import esrever.framework.HraObjekt;

public class Kamera{						//Tøída kamera, zajišující pohyb kamery s hráèem, díky èemu není velikost levelu omezen podle velikost rozlišení okna
	
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
