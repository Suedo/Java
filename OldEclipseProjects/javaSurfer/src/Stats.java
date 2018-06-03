/*
 * created mainly for tesing execution times of various codes
 */
final class stats {
	
	private stats() {
		// empty
	}

	public static double mean(long[] a) {
		if (a.length == 0)
			return Double.NaN;
		long sum = sum(a);
		return (double) sum / a.length;
	}

	public static long sum(long[] a) {
		long sum = 0L;
		for (int i = 0; i < a.length; i++) {
			sum += a[i];
		}
		return sum;
	}

}