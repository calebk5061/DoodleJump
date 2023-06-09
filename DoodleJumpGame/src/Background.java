import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Background {

	private int x, y; 
	private Image img; 	
	private AffineTransform tx;

	public Background() {
		img = getImage("/imgs/background.png"); 
		
		x = 0;
		y = 0;
		
		tx = AffineTransform.getTranslateInstance(x, y);
		init(x, y);
	}
	
	public Background(String fileName) {
		
		img = getImage("/imgs/background.png"); 
		
		x = 0;
		y = 0;

		tx = AffineTransform.getTranslateInstance(x, y);
		init(x, y); 
	}

	
	public void changePicture(String newFileName) {
		img = getImage(newFileName);
		init(x, y);
	}
	
	public void paint(Graphics g) {
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, tx, null);
		
		//g.drawRect(0, 400, 580, 5);
		//g.drawRect(0, 730, 580, 5);

	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(1.0, 1.0);
	}

	private Image getImage(String path) {
		Image tempImage = null;
		try {
			URL imageURL = Background.class.getResource(path);
			tempImage = Toolkit.getDefaultToolkit().getImage(imageURL);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tempImage;
	}

	
}
