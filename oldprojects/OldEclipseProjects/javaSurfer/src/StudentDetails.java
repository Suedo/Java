import java.util.*;
import java.io.*;

class Student {
	private final int id;
	private final int age;
	private String name;

	Student(int id, int age, String name) {
		this.id = id;
		this.age = age;
		this.name = name;
	}

	public int age() {
		return age;
	}

	public String toString() {
		return String.format("%3d  %3d  %s", id, age, name);

	}

}

class AgeComparator implements Comparator<Student> {

	@Override
	public int compare(Student o1, Student o2) {
		return o1.age() - o2.age();
	}

}

public class StudentDetails {

	public static void main(String[] args) {
		/*
		 * this is known as a try-with-resources statement , introduced in java
		 * 7 its helps because it auto closes the streams after execution , to
		 * avoid resource/memory leak
		 */
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(
				System.in))) {
			System.out.print("> ");
			String line;
			ArrayList<Student> sList = new ArrayList<>();
			String[] parts;
			while ((line = reader.readLine()) != null) {

				if (line.equals("q"))	break; // press "q" to quit
				System.out.print("> ");
				parts = line.split("[ ]+");
				int id = Integer.parseInt(parts[0]);
				int age = Integer.parseInt(parts[1]);
				String name = parts[2];

				sList.add(new Student(id, age, name));
			}
			/*
			 * comparator would be better for sorting stuff based on complicated
			 * stuff , or when you want to sort based on multiple parameter ,
			 * for int based sort , better would be to have student class
			 * implement comparable which thus enforces a total order for the
			 * class , and then , just doing Collections.sort(slist) would have
			 * done the job.
			 */
			Collections.sort(sList, new AgeComparator());

			for (Student s : sList) {
				System.out.println(s);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
