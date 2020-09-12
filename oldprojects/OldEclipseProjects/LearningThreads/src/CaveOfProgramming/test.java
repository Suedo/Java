package CaveOfProgramming;

public class test {
	public static void main(String[] args) throws InterruptedException {
		final Runner r = new Runner();
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				Thread.currentThread().setName("T1");
				r.m1();
			}
		});
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				Thread.currentThread().setName("T2");
				r.m2();
			}
		});
		
		t1.start();
		t2.start();
		
//		t1.join();
//		t2.join();
		
		r.finished();
		
		
	}
}
