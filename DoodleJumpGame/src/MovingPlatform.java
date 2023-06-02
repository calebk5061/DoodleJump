import java.awt.Graphics;

public class MovingPlatform extends Platform{

	public MovingPlatform(int y) {
		
		super(y);
		vx = (int)(Math.random()*15)-7;
		while (vx > -4 && vx < 4) {
			vx = (int)(Math.random()*15)-7;
		}
		
		changePicture("/imgs/blue.png");
		//System.out.println(vx);
	}

}
