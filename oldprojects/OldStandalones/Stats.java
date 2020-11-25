/*
 * created mainly for tesing execution times of various codes
 */
final class stats {

	private stats() {}

	public static long sum(long[] a) {
		long sum = 0L;
		for (int i = 0; i < a.length; i++) {
			sum += a[i];
		}
		return sum;
	}
	public static double sum(double[] a) {
		double sum = 0.0;
		for (int i = 0; i < a.length; i++) {
			sum += a[i];
		}
		return sum;
	}

	public static double mean(double[] a) {
		if (a.length == 0)
			return Double.NaN;
		double sum = sum(a);
		return (double) sum / a.length;
	}

	public static double mean(long[] a) {
		if (a.length == 0)
			return Double.NaN;
		long sum = sum(a);
		return (double) sum / a.length;
	}

	public static double stddev(double[] a) {
		return Math.sqrt(var(a));
	}

	public static double var(double[] a) {
		if (a.length == 0)
			return Double.NaN;
		double avg = mean(a);
		double sum = 0.0;
		for (int i = 0; i < a.length; i++) {
			sum += (a[i] - avg) * (a[i] - avg);
		}
		return sum / (a.length);
	}
}
