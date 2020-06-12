package esrever.framework;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.LinkedList;

public abstract class HraObjekt {								//Tøída na inicializaci jednotlivých objektù
	
	protected float x,y;
	protected float velX = 0, velY = 0;
	protected ObjektID id;
	protected boolean falling = false;
	protected boolean jumping = true;
	protected int monsterCount;
	
	public HraObjekt(float x, float y, ObjektID id) {
		this.x=x;
		this.y=y;
		this.id=id;
		
	}
	
	public abstract void tick(LinkedList<HraObjekt> objekt);  
	public abstract void render(Graphics g);
	public abstract Rectangle getBounds();
	
	public float getX() {
		return x;
	}
	public float getY() {
		return y;
	}
	public void setX(float x) {
		this.x=x;
	}
	public void setY(float y) {
		this.y=y;
	}
	
	public float getVelX() {
		return velX;
	}
	
	
	public float getVelY(){
		return velY;
	}
	public void setVelX(float velX){
		this.velX=velX;
	}
	public void setVelY(float velY){
		this.velY=velY;
	}
	
	public ObjektID getID(){
		return id;
	}

	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public boolean isJumping() {
		return jumping;
	}

	public void setJumping(boolean jumping) {
		this.jumping = jumping;
	}
	
	
}
