package ch02;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Tester {

	public static void main(String[] args) {
//		InterviewQs.groupingStrings();

//		System.out.println(Paths.get("").toAbsolutePath());
		
		System.out.println("Starting..");
		long startTime = System.currentTimeMillis();
		try {
			for (int i = 0; i < 50; i++)
				InterviewQs.findNamesWith(Paths.get("Files/names.txt"), "S");
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Execution took: " + (System.currentTimeMillis() - startTime) + "ms");
	}

}
