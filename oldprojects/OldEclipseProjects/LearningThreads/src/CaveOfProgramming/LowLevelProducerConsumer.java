package CaveOfProgramming;

import java.util.ArrayList;
import java.util.Random;

public class LowLevelProducerConsumer {
	ArrayList<Integer> list = new ArrayList<Integer>();
	private final int size = 10;
	private int elems = 0;
	Object lock = new Object();
	Random r = new Random(System.currentTimeMillis());

	public void producer() {
		while (true) {
			synchronized (lock) {
				try {
					while (elems < size) {
						list.add(r.nextInt(100));
						elems++;
					}
				} finally {
					// allows consumer to remove an entry
					lock.notify();
				}
			}
		}
	}

	public void consumer() throws InterruptedException {
		Thread.sleep(100); // any better way to ensure producer runs first ?
		int ran = 0;
		while (true) {
			// consumer tries to acquire lock here , but it can do so only after
			// producer has called notify
			synchronized (lock) {
				
				// do i need a lock.wait() here somewhere ?
				
				ran = r.nextInt(10);
				
				if (ran == 7) { // just an arbitrary condition
					 
					int loc = r.nextInt(list.size());
					int data = list.remove(loc);
					System.out.format(
							"%d removed from index %d , size : %d\n",
							data, loc, list.size());
					// decrementing elems to let the producer work again
					elems--;
					Thread.sleep(300);
				}
				
				// release the lock so that producer can fill things up again
				lock.notify();
			}
		}

	}

	public static void main(String[] args) {
		final LowLevelProducerConsumer low = new LowLevelProducerConsumer();
		Thread t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				low.producer();
			}
		});
		Thread t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					low.consumer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		t1.start();
		t2.start();
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
