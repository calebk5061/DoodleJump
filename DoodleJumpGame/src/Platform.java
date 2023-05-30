import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class Platform {
	
	protected int vx = 0;
	protected int x, y;
	private Image img; 	
	private AffineTransform tx;
	
	public Platform() {
		img = getImage("/imgs/platform.png"); 
		
		// initial position
		x = (int)(Math.random() * 450) - 45;
		y = (int)(Math.random() * 735) - 50;

		
		// stuff for image
		tx = AffineTransform.getTranslateInstance(x, y);
		init(x, y);
		
	}
	
	public Platform(int x, int y) {
		img = getImage("/imgs/platform.png"); 
		
		// initial position
		this.x = x;
		this.y = y;
		
		
		// stuff for image
		tx = AffineTransform.getTranslateInstance(x, y);
		init(x, y);
	
		//Rectangle r = new Rectangle(x + 43,y + 100,75,10);
		
	}
	
	public Platform(int y) {
		
		// random x variable for new platforms above the screen
		x = (int)(Math.random() * 450) - 50;
		this.y = y;

		
		img = getImage("/imgs/platform.png"); 
		tx = AffineTransform.getTranslateInstance(x, y);
		init(x, y);
		
	}
	
	public void changePicture(String newFileName) {
		img = getImage(newFileName);
		init(x, y);
	}
	
	public void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(1.85, 2.0);
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
	
	
	public boolean overlapCheck(Platform c) {
		
		// if 2 platforms are too close to each other
		// the pc will print out a new platform with
		// new x and y positions
		if(x >= c.getX() - 75 && x < c.getX() + 75) {
			if(y >= c.getY() - 10 && y < c.getY() + 10) {
				return true;
			}
		}
		return false;
	}
	
	public void newX() {
		x = (int)(Math.random() * 435) - 35;
	}
	
	public void newY() {
		y = (int)(Math.random() * 735) - 50;
	}
	
	public int getX() {
		return x + 43;
	}
	
	public int getY() {
		return y + 100;
	}
	
	public boolean collision(DoodleJumpDude a) {
		
		// if doodle jump dude hits the platform, doodle jumps up, 
		if((a.getHitBoxX() < x + 118 && a.getHitBoxX() > x - 10) && (a.getHitBoxY() <= y + 50 && a.getHitBoxY() >= y + 40)) {
			a.setVY(-8);
			return true;
		}
		return false;
	}
	
	public void paint(Graphics g, boolean down) {
		
		//these are the 2 lines of code needed draw an image on the screen		
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, tx, null);
		
		//g.setColor(Color.red);
		
		//g.drawRect(x + 43,y + 100,75,10);
		
		x+=vx;
		
		if(x > 410 || x < -50) {
			vx *= -1;
		}
		
		tx = AffineTransform.getTranslateInstance(x, y);
		init(x, y);
		
		// for stuff in Frame class
		if(down == true) {	
			y += 16;
		}
		
		if(down == false) {
			y+=0;
		}
		
	}
	

}
