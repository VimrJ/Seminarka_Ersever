package esrever;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageLoader {	//Tøída na nahrávání obrázkù, bohužel nìjak nefunguje a hází null pointer..
	
	private BufferedImage image;
	
	
	public BufferedImage loadImage(File f) {
		
		
		try {
			image = ImageIO.read(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return image;
	}
}
