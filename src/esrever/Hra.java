package esrever;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;


import esrever.framework.KeyInput;
import esrever.framework.ObjektID;

public class Hra extends Canvas implements Runnable {								//Hlavn� t��da programu

	
	private static final long serialVersionUID = 5458247496940862702L;

	private boolean running = false;
	private Thread thread;
	
	private int zivoty=2;
	public static int LEVEL=1;
	
	public int getZivoty() {
		return zivoty;
	}

	public void setZivoty(int zivoty) {
		this.zivoty = zivoty;
	}

	public static int WIDTH, HEIGHT;
	
	private BufferedImage level=null;
	private File f;
	
	Handler handler;
	Kamera kam;
	
	Random rand = new Random();
	
	private void init() {								
		
		WIDTH=getWidth();
		HEIGHT=getHeight();
		
		
		f=new File("level.png");
		BufferedImageLoader loader = new BufferedImageLoader();
		level=loader.loadImage(f);
		
		kam=new Kamera(0,0);
		handler = new Handler(kam);
		
		
		//handler.addObjekt(new Hrac(100, 100, handler, ObjektID.Hrac));
		
		//handler.createLevel();
		
		
		handler.LoadImageLevel(level);
		
		this.addKeyListener(new KeyInput(handler));
	}
	
	public synchronized void start() {	
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void run() {							// po��t�n� FPS a tick� + spu�t�n� vl�kna (k�d mam z internetu a ani to neni tak d�le�it�, ale je super to vid�t..)
		init();
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int updates = 0;
		int frames = 0;
		while(running){
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while(delta >= 1){
				tick();
				updates++;
				delta--;
			}
			render();
			frames++;
					
			if(System.currentTimeMillis() - timer > 1000){
				timer += 1000;
				System.out.println("FPS: " + frames + " TICKS: " + updates);
				frames = 0;
				updates = 0;
			}
		}
	}
	
	private void tick() {															
		handler.tick();
		for(int i = 0; i < handler.objekt.size(); i++) {
			if(handler.objekt.get(i).getID() == ObjektID.Hrac)
			kam.tick(handler.objekt.get(i));
		}
		
	}
	
	private void render() {																	//vykreslov�n� 
		
		BufferStrategy bs = this.getBufferStrategy();									//BufferStrategy - vlastn� p�edna��t�n� sn�mk�
		if(bs==null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g2d.translate(kam.getX(), kam.getY());		//V�e mezi translate s kam a -kam bude ovlivn�no kamerou 
		
		handler.render(g);
		
		
		g2d.translate(-kam.getX(), -kam.getY());
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String args[]) {
		new Okno(1280, 760, "Esrever_alpha",new Hra());
	
	}
}
