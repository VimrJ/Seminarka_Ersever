package esrever.framework;

import java.awt.image.BufferedImage;
import java.io.File;

import esrever.BufferedImageLoader;


public class Texture {		//Tøída na textury 
	
		private BufferedImage text;
		private File zem;
		//public BufferedImage[] block = new BufferedImage[2];
	public Texture() {
		zem=new File("zem.png");
		BufferedImageLoader loader = new BufferedImageLoader();
		text=loader.loadImage(zem);
		
		getTextures();
	}
	
	private void getTextures(){
		//block[0] = zem;
	}
}

