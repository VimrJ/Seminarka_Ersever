package esrever;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BufferedImageLoader {	//T��da na nahr�v�n� obr�zk�, bohu�el n�jak nefunguje a h�z� null pointer..
	
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
