package JavaEssentials;


public class test2 {
	private String[] messages = { "m1", "m2", "m3", "m4" };

	public class MessageLoop implements Runnable {
		int c = 0;

		public void run() {
			Thread.currentThread().setName("messageloop");
			while (c < 50) {
				try {
					System.out.println(messages[(c++) % messages.length]);
					if (c != messages.length)
						Thread.sleep(100);
				} catch (InterruptedException e) {
					System.out.println(Thread.currentThread().getName()
							+ " : I wasn't done!");
					// e.printStackTrace();
					// return;
				}				
			}
		}
	}

	public static void main(String[] args) {
		try {
			Thread t = new Thread(new test2().new MessageLoop());
			t.start();
			while (t.isAlive()) {
				t.join(130);
				System.out.println(Thread.currentThread().getName()
						+ " : interrupting " + t.getName());
				t.interrupt();
//				t.join();
			}
		} catch (InterruptedException e) {
			System.out.println("enough time");
			// e.printStackTrace();
		}

	}
}
