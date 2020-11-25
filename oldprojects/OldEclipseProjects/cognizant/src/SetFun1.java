import java.util.Arrays;
import java.util.HashSet;

public class SetFun1 {
	public static void main(String[] args) {
		int[] a = {1,1,2,1};
		HashSet<Integer> hset = new HashSet<>();
		for(int i : a){
			hset.add(i);
		}
		Integer[] res = hset.toArray(new Integer[hset.size()]);
		for (Integer i : res) {
			System.out.println(i);
		}
	}
}
