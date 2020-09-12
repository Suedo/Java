public class PrimeTest {
	int[] data;
	boolean[] status;

	private void setup() {
		data = new int[100];
		status = new boolean[100];
		for (int i = 0; i < data.length; i++) {
			data[i] = i;
			status[i] = true;
		}

	}

	public PrimeTest() { // this is a constructor
		setup();
		for (int num = 2; num < data.length; num++) {
			for (int j = num + 1; j < data.length; j++) {
				if (data[j] % num == 0 && status[j])
					status[j] = false;

			}
		}
		for (int i = 1; i < data.length; i++) {
			if (status[i])
				System.out.println(i);
		}
	}

	public static void main(String[] args) {
		new PrimeTest();
	}

}