import java.util.ArrayList;
import java.util.Collections;


public class freqTester {
	public static void main(String[] args) {
		String str = "1111155446665511";
		char[] arr = str.toCharArray();
		ArrayList<Character> list = new ArrayList<Character>();
		for (char c : arr) {
			list.add(c);
		}
		System.out.println(Collections.frequency(list,new Character('5')));
		int max = 0;
		for(int i = 0 ; i < arr.length ; i++){
			max = 0;
			for(int j = i+1 ; j < arr.length ; j++){
				if(arr[i]==arr[j]) max++;
			}
			System.out.println(arr[i] + "  :  " + max);
		}
	}
}
