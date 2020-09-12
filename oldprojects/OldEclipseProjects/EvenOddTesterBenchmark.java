/*
 * this was to test two methods of finding whether a number is even or odd.
 * m1 : use bitwise AND operation with the last bit (LSB)
 * m2 : succesive division and multiplication by 2
 */
public class EvenOddTesterBenchmark {
	private final static int count = 500000000;
	private final static long num = 55465465465465L;
	private final static int loops = 25;
	private long runTime;
	private long bitArr[] = new long[loops];
	private long mulDivArr[] = new long[loops];
	private double meanVal;

	private void bitwiser() {
		long result;
		for (int i = 0; i < count; i++) {
			result = num & 1;
		}
		// System.out.println("run time for bitwise op : " + runTime);
	}

	private void muldiv() {
		long result;
		for (int i = 0; i < count; i++) {
			result = (num / 2) * 2;
		}
		// System.out.println("run time for muldiv op : " + runTime);
	}

	public EvenOddTesterBenchmark() {
		// run loops and gather info
		for (int i = 0; i < loops; i++) {
			runTime = System.currentTimeMillis();
			bitwiser();
			runTime = System.currentTimeMillis() - runTime;
			bitArr[i] = runTime;
			runTime = System.currentTimeMillis();
			muldiv();
			runTime = System.currentTimeMillis() - runTime;
			mulDivArr[i] = runTime;
		}
		// calculate mean
		meanVal = stats.mean(bitArr);
		System.out.println("bitwise time : " + meanVal);
		meanVal = stats.mean(mulDivArr);
		System.out.println("muldiv time : " + meanVal);

	}

	public static void main(String[] args) {
		new EvenOddTesterBenchmark();
	}
}
