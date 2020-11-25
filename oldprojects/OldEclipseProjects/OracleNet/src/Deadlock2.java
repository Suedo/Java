public class Deadlock2{
	
	public static void main(String[] args) throws InterruptedException {
        final Friend alphonse = new Friend("Alphonse"); // i dont understand why final is needed here
        final Friend gaston = new Friend("Gaston");
		new Thread(new Runnable() {	
            public void run() { 
				alphonse.bow(gaston); // this thread will hold the lock over alphonse's bow()
			}
        }).start();
		new Thread(new Runnable() {
            public void run() { 
				gaston.bow(alphonse); // this thread will hold the lock over gaston's bow()
			}
        }).start();
    }

}

class Friend{
	private final String name;
	
	public Friend(String name) { // inner static constructor
		this.name = name;		
	}
	public String getName() {
		return this.name;
	}
	
	public synchronized void bow(Friend bower) {
		System.out.format("%s : %s" + "  has bowed to me! , must bow back !%n", this.name, bower.getName());
		bower.bowBack(this);
		System.out.println(".");
		
	}
	
	public synchronized void bowBack(Friend bower) {
		System.out.format("%s : %s" + " has bowed back to me!%n", this.name, bower.getName());
			System.out.println("L");
	}
}