import java.io.File;
import java.util.Arrays;

class FileSorter{
	
	public FileSorter(){
		File fileName = new File("F:/videos/coursera/algorithms");
		File[] fileArr = fileName.listFiles();
		Arrays.sort(fileArr);
		for(File f : fileArr){
			System.out.println(f.getName());
		}
	}
	
	public static void main(String[] args){
		new FileSorter();
	}
}