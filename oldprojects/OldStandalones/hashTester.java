/*
 * a basic example to show that two equivalent strings have same hashCode().
 * and such types of properties property can be used in overriding equals() and hashCode() as and when required.
 * an example of such a requirement maybe : we have two separate objects , but meaning-wise they are same , 
 * like two instances of a song class (HFS Java : pg559and onwards). the two instances refer to the same song , 
 * but if we try to put them in a set to remove duplicacy , we will still find that duplicate issues of the same song are there.
 * why ? because how we determine duplicates is different from how the compiler does so.
 * when two song instances have same "meaning" , to us , they are same , but to the compiler , 
 * two instances == different hashCode() for each , thus each are different.
 * we may want to override hashCode() function of the set we are adding the songs to in such a case ,  and we can do that
 * by returning hasCode() of certain string parameters of the song object , if they are same , hashCode() returns same value , 
 * and the set considers them as same , and does not add the "duplicate". 
 * 
 */
public class hashTester {
	public String s1 = "somjit";
	public String s2 = "somjit";
	public boolean flag = s1.hashCode() == s2.hashCode();
	
	public static void main(String[] args){
		hashTester t = new hashTester();
		System.out.println("flag : " + t.flag); // op : "flag : true" 
	}
}
