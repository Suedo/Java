
public class ThreadTests {
	String s[] = {"abcd","efg","ijkl","elomelo-p"};
	public static void main(String[] args){
		ThreadTests t = new ThreadTests();
		int i = 0;
		try{
			while(i<t.s.length){
				Thread.sleep(1000);
				System.out.println(t.s[i++]);
			}
			
		}catch(InterruptedException e){ e.printStackTrace();}
	}
}
