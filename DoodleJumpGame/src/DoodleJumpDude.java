import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.net.URL;

public class DoodleJumpDude {
	
	private int x, y;
	private int vx, vy;
	private boolean ignoreCollision = false;
	private Image img; 	
	private AffineTransform tx;
	private int yHitbox = 0;
	long time = 0;
	boolean die = false;
	
	public DoodleJumpDude() {
		img = getImage("/imgs/doodle.png"); 
		
		// initial position
		x = 225;
		y = 650;
		
		// stuff for image
		tx = AffineTransform.getTranslateInstance(x, y);
		init(x, y);
		
		// velocity
		vx = 0;
		vy = 8;
		
	}
	
	public void changePicture(String newFileName) {
		img = getImage(newFileName);
		init(x, y);
	}
	
	public void paint(Graphics g) {
		
		//these are the 2 lines of code needed draw an image on the screen
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(img, tx, null);

		// hitbox
		//g.setColor(Color.BLACK);
		//g.drawRect(x + 10,y + 68 + yHitbox,45,5);
		
		//position updates with velocity in DoodleJumpDude() method
		y += vy;
		
		
		//draws doodle man
		g2.drawImage(img, tx, null);
		
		// when doodle goes up, time counts down
		if (vy <= 0) {
			time -= 100;
			if(vy == 0) {
				time -= 225;
			}
		}
		
		// when timer hits 0, doodle moves back down and reset time
		if (time <= 0) {
			vy = 8;
			time = 1800;
		}
		
		// doodle stays in one place at y = 381 while time counts down
		if (y < 380) {
			y = 381;
			vy = 0;
		}
		
		// doodle teleports from one side to the other
		if (x <= -50) {
			x = 525;
		}
		
		if (x >= 550) {
			x = -25;
		}
		
		//ignores collision when doodle jump moving up
		if (vy <= 0) {
			ignoreCollision = true;
		}
		
		if (vy > 0) {
			ignoreCollision = false;
		}
		
		update();
		
		
	}
	
	public void update() {
		tx.setToTranslation(x,y);
		tx.scale(.2,.2);
		
		//updates hitbox to be more accurate due to size of picture
		if (vy >= 0) {
			yHitbox = -5;
		}
		if(y <= 0) {
			yHitbox = 50;
		}
		
		x += vx;
		
	}
	
	private void init(double a, double b) {
		tx.setToTranslation(a, b);
		tx.scale(0.2, 0.2);
	}
	
	public void scale(double a, double b) {
		
		tx.scale(a, b);
		
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

	public int getHitBoxX() {
		return x + 5;
	}
	
	public int getHitBoxY() {
		return y + 10;
	}
	
	public int getVY() {
		return vy;
	}
	
	public void setVY(int newVY) {
		vy = newVY;
	}
	
	public int getY() {
		return y;
	}
	
	public void setXY(int newX, int newY) {
		x = newX;
		y = newY;
	}
	
	public void setTime(long newTime) {
		time = newTime;
	}
	
	public long getTime() {
		return time;
	}
	
	public boolean getIgnore() {
		return ignoreCollision;
	}
	
	public void setVX(int newVX) {
		vx = newVX;
	}
	
	public void death() {
		
		if (die == false) {
			die = true;
		}
		
	}
	
	public boolean getDie() {
		return die;
	}
	
}
