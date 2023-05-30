
public class Score {
	
	private int x, y;
	private int total = 0;
	
	public Score() {
		
		x = 420;
		y = 60;
		
	}
	
	public void updateScore(Platform p) {
		
		total += (740 - p.getY())/10 ;
		
	}
	
	public void resetScore() {
		
		total = 0;
		
	}
	
	public int getScore() {
		return total;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	
	public void setX(int newX) {
		x = newX;
	}

	public void setScore(int i) {
		total = i;
	}
	
}
