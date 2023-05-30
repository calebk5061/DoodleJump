import java.io.FileWriter;
import java.io.IOException;

public class DriverWriting {
	
	String score;
	

	public static void main(String[] args) throws IOException {
		
		FileWriter writer = new FileWriter("highscores", true);
		
		
		
		//always close files after you are done
		writer.close();
		
	}

}
