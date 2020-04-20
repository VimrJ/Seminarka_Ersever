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
import esrever.framework.Texture;
import esrever.objekty.Block;
import esrever.objekty.Coin;
import esrever.objekty.Hrac;
import esrever.objekty.Monster;

public class Hra extends Canvas implements Runnable {								//Hlavní tøída programu

	
	private static final long serialVersionUID = 5458247496940862702L;

	private boolean running = false;
	private Thread thread;
	
	private int zivoty=2;
	
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
	static Texture tex;
	
	Random rand = new Random();
	
	private void init() {								
		
		WIDTH=getWidth();
		HEIGHT=getHeight();
		
		tex=new Texture();
		
		f=new File("level.png");
		BufferedImageLoader loader = new BufferedImageLoader();
		level=loader.loadImage(f);
		
		handler = new Handler();
		
		kam=new Kamera(0,0);
		
		//handler.addObjekt(new Hrac(100, 100, handler, ObjektID.Hrac));
		
		//handler.createLevel();
		
		
		LoadImageLevel(level);
		
		this.addKeyListener(new KeyInput(handler));
	}
	
	public synchronized void start() {
		if(running)
			return;
		
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	public void run() {							// poèítání FPS a tickù + spuštìní vlákna (kód mam z internetu a ani to neni tak dùležitý, ale je super to vidìt..)
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
	
	private void render() {																	//vykreslování 
		
		BufferStrategy bs = this.getBufferStrategy();									//BufferStrategy - vlastnì pøednaèítání snímkù
		if(bs==null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		Graphics2D g2d = (Graphics2D) g;
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		g2d.translate(kam.getX(), kam.getY());		//Vše mezi translate s kam a -kam bude ovlivnìno kamerou 
		
		handler.render(g);
		
		
		g2d.translate(-kam.getX(), -kam.getY());
		
		g.dispose();
		bs.show();
	}
	
	private void LoadImageLevel(BufferedImage level) {
		int w = level.getWidth();
		int h = level.getHeight();
		
		System.out.println(w +", "+ h);
		
		for(int xx = 0; xx < h; xx++) {					// Dvojitý for cyklus -> zjistí na kterem pixelu je a veme z toho RGB
			for(int yy = 0; yy < w; yy++) {
				int pixel = level.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red == 255 && green == 255 & blue == 255) handler.addObjekt(new Block(xx*40, yy*40, ObjektID.Block));
				if(red == 255 && green == 242 & blue == 0) handler.addObjekt(new Monster(xx*40, yy*40, ObjektID.Monster));
				if(red == 0 && green == 255 & blue == 0) handler.addObjekt(new Coin(xx*40, yy*40, ObjektID.Coin));					//Dìlání z pixelu objekt
				if(red == 0 && green == 0 & blue == 255) handler.addObjekt(new Hrac(xx*40, yy*40, handler, ObjektID.Hrac));
			}
		}
		
	}
	
	public static Texture getInstance() {
		return tex;
	}
	
	public static void main(String args[]) {
		new Okno(1280, 760, "Esrever_alpha",new Hra());
	
	}
}
