import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class experiment {

	public static void main(String[] args) {
		String filename = args[0];
		String[] parsed;
		String line;
		try {
			BufferedReader is = new BufferedReader(new FileReader(filename));
			while ((line = is.readLine()) != null) {
				line = line.trim();
				parsed = line.split("[ ]+");
				for (String s : parsed) {
					if (Integer.parseInt(s) < 5) {
						throw new myExceptions("less than 5!");
					}
					System.out.println(s);
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("error reading file : " + filename);
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("error reading line from file ");
			e.printStackTrace();
		} catch (NumberFormatException e) {
			System.out.println("error while parsing line ");
			e.printStackTrace();
		} catch (myExceptions e) {
			e.printStackTrace();
		}

	}

}

class myExceptions extends Exception {
	public myExceptions(String msg) {
		super(msg);
	}
}