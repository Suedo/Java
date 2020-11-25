package CaveOfProgramming;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyThreadPoolApp {
	public static void main(String[] args) {
		long execTime = System.currentTimeMillis();
		CountDownLatch latch = new CountDownLatch(10);
		ExecutorService executor = Executors.newFixedThreadPool(10);
		
		for (int i = 0; i < 10; i++) {
			executor.submit(new Task(latch));
		}
		System.out.println("threads submitted and waiting execution");

		executor.shutdown();
		try {
			latch.await();// no need for latches if using awaitTermination() 
//			executor.awaitTermination(1, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			System.out.format("%s says Bum!\n",Thread.currentThread().getName());
		}
		execTime = System.currentTimeMillis() - execTime;
		System.out.format("%d threads finished execution \n",Task.getCount());
		System.out.println("thread time : " + Task.getTime());
		System.out.println("main time : " + execTime);
	}
}
