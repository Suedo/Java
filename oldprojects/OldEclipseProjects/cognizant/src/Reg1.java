public class Reg1 {
	public static void main(String[] args) {
		String[] s = new String[8];
		s[0] = "somjitnag@gmail.co.in";
		s[1] = "somjit.nag@google.co.in";
		s[2] = "somjitnag@gmail.com";
		s[3] = "somjitnag192@gmail.com";
		s[4] = "somjit.nag192@yahoo.com";
		s[5] = "somjitnag192@yahoo.co.in";
		s[6] = "som.jit.nag192@yahoo.co.in";
		s[7] = "somjitnag192@yahoo.co.in";
		
		String regex = "[a-z]+\\.?[a-z]+(\\d+)?@[a-z]{3,5}(.co)?\\.?(com|in)$";
		for (String str : s) {
			if(str.matches(regex)) System.out.println(str);
		}
	}
}
