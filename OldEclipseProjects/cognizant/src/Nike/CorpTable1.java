package Nike;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;

public class CorpTable1 {
	// This map stores jobs mapped to their status
	private static HashMap<String, String> hmap = new HashMap<String , String>();
	private static BufferedReader jobMapReader,IpListReader,controlReader;
	private static PrintWriter writer;
	private static String[] parsed;
	
	public CorpTable1(){
		
		String line;
		
		try {
			// set up print writer and reader
			writer = new PrintWriter(new FileWriter("/cognizant/src/Nike/JobStatusOP.txt"));
			jobMapReader = new BufferedReader(new FileReader("/cognizant/src/Nike/JobMap.txt"));
			IpListReader = new BufferedReader(new FileReader("/cognizant/src/Nike/JobListIP.txt"));
			controlReader = new BufferedReader(new InputStreamReader(System.in));
			
			loadJobDetails();
			
		} catch (IOException e) {e.printStackTrace();}		
	}
	
	private static void loadJobDetails() throws IOException{
		String line;
		while((line=jobMapReader.readLine())!=null){
			line = line.trim();
			parsed = line.split("\t+");
			if(parsed.length!=2) { System.out.println("Error In JobMap loading"); break; }
			hmap.put(parsed[0],parsed[1]);
		}
	}
	private static void getJobIP() throws IOException{
		String line;
		
		while((line=IpListReader.readLine())!=null){
			
		}
	}
	private static void getJobOP(){}
	
	public static void go(){
		
		getJobIP();
		getJobOP();
		
		
	}
}
