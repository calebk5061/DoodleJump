import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		
		try {
			
			// setup the file reading using Scanner class
			Scanner scanner = new Scanner( new File("highscores"));
			
			//  reading lines from the scanner
			while(scanner.hasNextLine()){
				
				// read one line at a time
				String line = scanner.nextLine();
				System.out.println(line);
				
				// break up the line into individual Strings
				String[] individual = line.split(" ");
				
				// convert a String integer to a real int we
				// can do math with etc
				System.out.println(Integer.valueOf(individual[1]));
				
			}
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
