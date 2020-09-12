package JavaEssentials;

public class test1{
	public static void main(String[] args){
		// m2
		new m2().run();
		new Thread( new m2()).start();
		// anonymous
		new Thread(new Runnable(){
			public void run(){
				System.out.println("anonymous code looks real cool!");
			}
		}).start();
	}
}

class m1 extends Thread{
	public void run(){
		System.out.println("hello from m1");
	}
}

class m2 implements Runnable{
	public void run(){
		System.out.println("hello from m2");
	}
}