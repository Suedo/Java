package CaveOfProgramming;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;

public class BlockingQueueApp {
	BlockingQueue<Integer> ints = new ArrayBlockingQueue<Integer>(10);
	Random random = new Random(System.currentTimeMillis());

	private void producer() {
		while (true) {
			try {
				ints.put(random.nextInt(100));
			} catch (InterruptedException e) {
			}
		}
	}

	private void consumer() throws InterruptedException {
		int r;
		while (true) {			
			r = random.nextInt(10);
			if (r == 5) {
				System.out.format("elem : %d , size : %d\n",
						ints.take(), ints.size());
				Thread.sleep(2000);
			}
		}
	}

	public static void main(String[] args) {
		final BlockingQueueApp b = new BlockingQueueApp();
		final CountDownLatch l = new CountDownLatch(1);
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					l.await();
					b.producer();
				} catch (InterruptedException e) {
				}

			}
		}).start();
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					l.await();					
					b.consumer();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}).start();
		
		System.out.println("guns loaded\nlets go..");
		l.countDown();

	}
}
