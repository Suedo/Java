import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;


public class buffers{
	
	private static final int semesters = 8;
	
	public static void main(String[] args){
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int semResult=0,g=0;
		while(g<semesters){
			try{
				System.out.print("enter semester marks : ");
				String entry = in.readLine();
				semResult += Integer.parseInt(entry);
				System.out.println("semester "+g+" marks added to total");
				g++;
			}catch(IOException e){
				System.out.println("problem reading number");
			}catch(NumberFormatException e){
				System.out.println("not a valid number");
			}
		}
		
		System.out.println("total of "+g+" semesters is "+semResult+"\nGPA is: "+(semResult/semesters));
	}
	

}
