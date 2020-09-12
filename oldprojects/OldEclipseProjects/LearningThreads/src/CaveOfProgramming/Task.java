package CaveOfProgramming;

import java.util.concurrent.CountDownLatch;

public class Task implements Runnable {

	private final CountDownLatch latch;
	private static long totalTime; 
	private static int count;
	private static Object lock = new Object();
	public static long getTime(){ return totalTime; }
	public static int getCount(){ return count; }
	
	public Task(CountDownLatch latch) {
		this.latch = latch;
	}
	
	public void run() {
		count++;
		long startTime = System.currentTimeMillis();
		try {
			Thread.sleep(2000);
			synchronized (lock) { // Task.class can also be used
				totalTime += System.currentTimeMillis() - startTime;
			}
			latch.countDown();
		} catch (InterruptedException e) {
			System.out.format("%s says Bum!\n",Thread.currentThread().getName());
		}
	}
}
