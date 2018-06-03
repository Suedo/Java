package CaveOfProgramming;

/*
 * This code mainly is a test for reentrant locks and
 * its tryLock() method . I'm happy with the acquireLocks methods
 * as of now. 
 */
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankTransaction {
	Lock lock1 = new ReentrantLock();
	Lock lock2 = new ReentrantLock();

	public class Account {
		
		private int balance;

		public Account(int initialSum) {
			
			this.balance = initialSum;
		}
		public int balance() {
			return balance;
		}

		public void withdraw(int sum) {
			balance = balance - sum;
		}

		public void deposit(int sum) {
			balance = balance + sum;
		}
	}

	public Account createAccount(int cash) {
		return new Account(cash);
	}

	public void transfer(Account src, Account dest, int sum) {
		acquireLocks(lock1, lock2);
		try {
			src.withdraw(sum);
			dest.deposit(sum);
		} finally {
			lock1.unlock();
			lock2.unlock();
		}
	}
/*
 * this is the main reason , i wanted to try out tryLock()
 * 
 */
	private void acquireLocks(Lock l1, Lock l2) {
		boolean gotFirstLock = false;
		boolean gotSecondLock = false;
		while (!(gotFirstLock && gotSecondLock)) {
			if (gotFirstLock)
				l1.unlock();
			if (gotSecondLock)
				l2.unlock();
			gotFirstLock = l1.tryLock();
			gotSecondLock = l2.tryLock();
		}
	}

	public static void main(String[] args) {

		final BankTransaction bt = new BankTransaction();
		final Account acc1 = bt.createAccount(10000);
		final Account acc2 = bt.createAccount(10000);

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				Random r = new Random(System.currentTimeMillis());
				for (int i = 0; i < 100000; i++) {
					bt.transfer(acc1, acc2, r.nextInt(1000));
				}
				System.out.println("one done");
			}
		});
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				Random r = new Random(System.currentTimeMillis());
				/* 
				 * on my laptop , loop had to run atleast a few million times
				 * to produce any visible effect when using transfer() method 
				 * without any locking 
				 */
				for (int i = 0; i < 100000; i++) {
					bt.transfer(acc2, acc1, r.nextInt(500));
				}
				System.out.println("two done");
			}
		});

		t1.start();
		try {
			Thread.sleep(50);
		} catch (InterruptedException e1) {
		}
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out
				.format("Total balance : %d \n balance in acc1 : %d \n balance in acc2 : %d \n",
						(acc1.balance() + acc2.balance()), acc1.balance(),
						acc2.balance());

	}

}
