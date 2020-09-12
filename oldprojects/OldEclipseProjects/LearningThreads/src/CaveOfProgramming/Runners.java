package CaveOfProgramming;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * Inspired from Effective Java ver2
 */
public class Runners {

	private int count = 5;
	CountDownLatch start = new CountDownLatch(1);
	CountDownLatch ready = new CountDownLatch(count);
	CountDownLatch done = new CountDownLatch(count);
	Random r = new Random(System.currentTimeMillis());

	ExecutorService executor = Executors.newFixedThreadPool(count);

	public void go() {
		for (int i = 0; i < count; i++) {
			executor.submit(new Runnable() {
				@Override
				public void run() {
					String name = Thread.currentThread().getName();
					try {
						System.out.format("%s : Ready \n", name);
						ready.countDown();
						start.await(); // wait for the gun shot!
						System.out.format("%s : SPARTAAAAAA..... \n", name);
						Thread.sleep(r.nextInt(8000));
						System.out.format("%s : Finished \n", name);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} finally {
						done.countDown();
					}

				}
			});
		}
		executor.shutdown(); // without this , the code will run forever.
		try {
			System.out.println("All runners ready ?? ");
			ready.await(); // wait for all runners to be ready
			long time = System.currentTimeMillis();
			start.countDown(); // BOOM! and the runners are off!
			done.await(); // wait for all runners to finish lap
			time = System.currentTimeMillis() - time;
			System.out.println("All runners have crossed the finish line!");
			System.out.println("Time taken : " + time);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		new Runners().go();
	}

}
