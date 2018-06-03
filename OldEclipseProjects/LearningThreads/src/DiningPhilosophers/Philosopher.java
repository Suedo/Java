package DiningPhilosophers;

import java.math.BigInteger;

public class Philosopher implements Runnable {

	public final int id;
	public Philosopher pLeft;
	public Philosopher pRight;
	public Fork fLeft;
	public Fork fRight;
	final long eatTime = 5000; // eat for 5 seconds
	final long thinkTime;
	private volatile long elapsedTime;
	private boolean isThinking, isEating;

	// philosopher can do one action at a time only
	private final Object action = new Object();

	private synchronized void eat() {
		try {
			isEating = true;
			Thread.sleep(eatTime);
			isEating = false;
			// make forks dirty
			fLeft = new Fork(this.fLeft.id, false);
			fRight = new Fork(this.fRight.id, false);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private synchronized void think() {
		try {
			isThinking = true;
			Thread.sleep(thinkTime);
			isThinking = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public boolean isThinking() {
		return isThinking;
	}

	public boolean isEating() {
		return isEating;
	}

	/*
	 * check who has called if fork dirty (already finished eating), clean it
	 * then give it away. else keep it ( to eat ).
	 */
	private Fork giveFork(Philosopher diner) {
		Fork forkToGive;

		if (this.pLeft.equals(diner)) {
			// give left fork to left philosopher
			if (this.fLeft.isClean)
				forkToGive = null; // don't give
			else {
				forkToGive = new Fork(this.fLeft.id, true); // give the fork
			}

		} else if (diner.pRight.equals(this)) {
			// give right fork to right philosopher
			if (this.fRight.isClean)
				forkToGive = null;
			else {
				forkToGive = new Fork(this.fRight.id, true);
			}
		} else {
			// default value , i'm not yet sure if this code
			// can be theoretically reached
			forkToGive = null;
		}

		return forkToGive;

	}

	private synchronized Fork getFork(Philosopher diner) {
		return diner.giveFork(this);
	}

	public void sayHello(Philosopher l, Philosopher r) {
		this.pLeft = l;
		this.pRight = r;
	}
	public Philosopher(int id, long thinkTime) {
		this.id = id;
		this.thinkTime = thinkTime;
	}
	/**
	 * If you don't think , you don't get to eat. When you do get to eat , check
	 * if you have both forks , if not , request for the forks you don't have.
	 * check if received both forks and finally eat . Also , set forks to dirty
	 * when you are done. Hold them until someone makes a request
	 */
	@Override
	public void run() {
		while(true){
			think();
			final long startTime = System.currentTimeMillis();
			elapsedTime = 0;
			while(fLeft == null || fRight == null){
				if(fLeft == null){
					fLeft = getFork(pLeft);					
				}
				if(fRight == null){
					fRight = getFork(pRight);					
				}
				// i'm hoping to use elapsed time as a priority for threads to get forks
				// but i'm not sure about where and how to synchronize.
				elapsedTime = System.currentTimeMillis() - startTime;
			}
			assert(fLeft!=null && fRight!=null):"eating without two forks";
			eat();
		}
	}
}