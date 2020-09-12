package DiningPhilosophers;

import java.math.BigInteger;
import java.util.Random;

public class Tester {
	private static final long seed;
	private static final Random random;
	private final static int numOfMembers = 5;
	private final Philosopher[] pArr = new Philosopher[numOfMembers];
	static {
		seed = System.currentTimeMillis();
		random = new Random(seed);
	}

	/**
	 * In a clockwise arrangement , create 5 philosophers create 5 dirty forks
	 * assign each fork to the lower(id wise) of two contending philosophers let
	 * the philosophers start thinking
	 */
	public void setupTable() { //
		for (int i = 0; i < numOfMembers; i++) {
			long thinkTime = BigInteger.probablePrime(8, random).longValue();
			pArr[i] = new Philosopher(i, thinkTime);
		}
		for(int l = 1 , r = 4 , i = 0; i < numOfMembers ; i++, l++, r++ ){
			l = l % numOfMembers;
			r = r % numOfMembers;
			pArr[i].sayHello(pArr[l], pArr[r]);
		}
		for(int i = 0 ; i < numOfMembers ; i++){
			new Thread(pArr[i]).start();
		}
	}
}
