import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

// note : slow. A windows .bat would be many orders faster.

public class MakeTXT {

	private File folder;
	private File[] fileList;
	String src, dest;

	public MakeTXT(String src) {
		this.src = src;
		folder = new File(src);
		fileList = folder.listFiles();
		System.out.println("\n**************************************************************\n");
		System.out.println("\n\ninput files : ");
		for (File f : fileList) {
			System.out.println(f.getName());
		}
	}

	public MakeTXT(String src, String dest) {
		this.src = src;
		this.dest = dest;
		folder = new File(src);
		fileList = folder.listFiles();
		System.out.println("\n**************************************************************\n");
		System.out.println("\n\ninput files : ");
		for (File f : fileList) {
			System.out.println(f.getName());
		}
	}

	private boolean valid(File f) {

		String fname = f.getName();
		if (fname.length() > 5 && fname.substring(0, 5).equals("\'TCHN")) return true;
		else { System.out.println("rejected : " + fname); return false; }
	}
	
	public void textItInPlace() {
		String oldname, tempname;
		System.out.println("\n\nTexting in place. \nOutput filenames : ");
		for (int i = 0; i < fileList.length; i++) {
			if (valid(fileList[i])) {
				oldname = fileList[i].getName();
				tempname = oldname.substring(1, oldname.length() - 1);
				System.out.println(tempname + ".txt");
				fileList[i].renameTo(new File(src + "/" + tempname + ".txt"));
			}
		}
		System.out.println("destination folder : " + src + "/");
	}

	public void textItToNew() {
		String oldname, tempname;
		System.out.println("\n**************************************************************\n");
		System.out.println("\n\nTexting to " + dest + " \nOutput filenames : ");
		for (int i = 0; i < fileList.length; i++) {
			if (valid(fileList[i])) {
				oldname = fileList[i].getName();
				tempname = oldname.substring(1, oldname.length() - 1);
				System.out.println(dest + "/" + tempname + ".txt");
				fileList[i].renameTo(new File(dest + "/" + tempname + ".txt"));
			}
		}
		System.out.println("destination folder : " + dest + "/");
	}

	public static void main(String[] args) {
		BufferedReader helper = new BufferedReader(new InputStreamReader(System.in));
		String srcPath, destPath;
		System.out.println("\n**************************************************************\n");
		System.out.println("if you want to place converted files in same folder : press 1");
		System.out.println("if you want to place converted files in another folder : press 2"
						+ "\n(this will require path for the new folder)");

		try {
			int ans = Integer.parseInt(helper.readLine());
			switch (ans) {
			case 1:
				System.out.println("\n**************************************************************\n");
				System.out.println("enter path of source folder : ");
				srcPath = helper.readLine();
				new MakeTXT(srcPath).textItInPlace();
				break;
			case 2:
				System.out.println("\n**************************************************************\n");
				System.out.println("enter path of source folder : ");
				srcPath = helper.readLine();
				System.out.println("\n**************************************************************\n");
				System.out.println("enter path of destination folder(path must be of an existing folder):");
				destPath = helper.readLine();
				new MakeTXT(srcPath, destPath).textItToNew();
				break;
			default:
				System.out.println("entered value : " + ans + " is not valid");
				break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
