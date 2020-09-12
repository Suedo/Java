import java.util.Arrays;


public class Anagram {
	
	String s1 = "Somjit" , s2 = "SoMJ         It";
	char[] c1 = s1.toUpperCase().toCharArray(), c2=s2.toUpperCase().toCharArray();
	
	public void testAnagram() {
		Arrays.sort(c1);
		Arrays.sort(c2);
		s1 = new String(c1).trim();
		s2 = new String(c2).trim();
		if(s1.equals(s2)){
			System.out.println("Anagram");
		}else{
			System.out.println(s1 + " " + s2 + " < not anagrams");
		}
		
	}
	
	public static void main(String[] args) {
		new Anagram().testAnagram();
	}
	
}
