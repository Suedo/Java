package perfectNum;

import java.util.ArrayList;

public class MersenneSeive {

	private ArrayList<Integer> MersenneIndex = new ArrayList<Integer>();
	private ArrayList<Integer> MersennePrimes = new ArrayList<Integer>();
	private PrimeSeive ps;

	public MersenneSeive(final int size) {
		int mSize = (int) Math.pow(2, size);
		ps = new PrimeSeive(mSize + 1);
		int m = 0;
		for (int p = 2; (m = (int) Math.pow(2, p) - 1) <= mSize; p++) {
			if (ps.isPrime(p) && ps.isPrime(m)) {
				MersenneIndex.add(p);
				MersennePrimes.add(m);
			}
		}
	}

	public void display() {
		System.out.println("\n Primes upto " + (long) Math.pow(2, 25));
		ps.display();
		System.out.println("\n Mersenne primes upto " + (long) Math.pow(2, 25));
		System.out.println(MersennePrimes.toString());
		System.out.println("\n perfect numbers : ");
		for (int p : MersenneIndex) {
			System.out.println(" "
					+ (long) (Math.pow(2, p - 1) * (Math.pow(2, p) - 1)));
		}
	}

	public static void main(String[] args) {
		long t = System.currentTimeMillis();
		MersenneSeive ms = new MersenneSeive(20);
		ms.display();
		System.out.println("\n\nExecution time : " + (System.currentTimeMillis() - t));
	}

}
