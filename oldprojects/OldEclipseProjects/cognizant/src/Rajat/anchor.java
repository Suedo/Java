package Rajat;

public class anchor {
	public static void main(String[] args) {
		String s1 = "abcXXXsssabcXXXabcppp";
		char[] arr = s1.toCharArray();
		int count;
		int max = 0;

		for (int i = 0; i < arr.length - 1; i++) {
			count = 1;
			int j = i + 1;
			while (j < arr.length && arr[i] == arr[j]) {
				j++;
				count++;
			}
			if (count % 3 == 0)
				max = max + (count / 3);
			i = j - 1;
		}
		System.out.println(max);
	}
}
