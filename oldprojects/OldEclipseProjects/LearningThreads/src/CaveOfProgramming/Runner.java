package CaveOfProgramming;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Runner {
	private int count = 0;
	int num , den ;
	Random r = new Random(System.currentTimeMillis());
	Lock lock = new ReentrantLock();
	Condition con = lock.newCondition();

	public void increment() {
		for (int i = 0; i < 1000000; i++){
			num = r.nextInt(1000) + 1; 
			den = r.nextInt(1000) + 1;
			num = num/den;
			count++;			
		}
	}

	public void m1() {		
		lock.lock();
		try{
			System.out.println(Thread.currentThread().getName() + " starts with " + count);
			increment();
			System.out.println(Thread.currentThread().getName() + " ends with " + count);
		}finally{
			con.signal();
			lock.unlock();
		}			
	}

	public void m2() {
		lock.lock();		
		try{
			con.await();
			System.out.println(Thread.currentThread().getName() + " starts with " + count);
			increment();
			System.out.println(Thread.currentThread().getName() + " ends with " + count);
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		finally{
			lock.unlock();
		}
	}

	public void finished() {
		synchronized (lock) {
			System.out.println("count : " + count);
		}		
	}

}
