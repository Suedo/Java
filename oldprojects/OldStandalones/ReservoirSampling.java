import java.util.TreeMap;

public class ReservoirSampling {
	public ReservoirSampling(){
		String[] data = "A B C D E F G H I J K L M O P".split(" ");
		double[] freq = new double[data.length];
		int k = 3;
		String[] sample = new String[k];

		// the tree map stores the count of appearance of each letter during
		// the total sampling procedure
		TreeMap<String, Double> dmap = new TreeMap<String, Double>();
		for (int index = 0; index < data.length; index++) {
			dmap.put(data[index], 0.0);
		}

		for (int loop = 0; loop < 1000000; loop++) {
			int i = 0;
			// initiate the sample
			while (i < k) {
				sample[i] = data[i++];
			}
			// start sampling from the reservoir
			for (; i < data.length; i++) {
				int r = (int) (Math.random() * (i + 1));
				if (r < k) {
					sample[r] = data[i];
				}
			}
			// update the count of each entry in the map
			for (String s : sample) {
				dmap.put(s, dmap.get(s) + 1);
			}
		}
		int index = 0;
		for (Double s : dmap.values().toArray(new Double[dmap.size()])) {
			freq[index++] = s;
			// System.out.println(s);
		}
		// calculate statistics
		double mean, stddev , cov;
		mean = stats.mean(freq);
		stddev = stats.stddev(freq);
		cov = (stddev / mean) * 100;
		System.out.println(" mean                   : " + mean);
		System.out.println(" coeff of variation(%)  : " + cov);
	}
	
	public static void main(String[] args) {
		new ReservoirSampling();
	}
}
