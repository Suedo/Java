public class Deadlock {
    static class Friend { // inner static class
        
		private final String name;
        public Friend(String name) { // inner static constructor
            this.name = name;		
        }
        public String getName() {
            return this.name;
        }
		// both the threads call the synchronized bowback method at the same time. thus causing a deadlock.
        public synchronized void bow(Friend bower) {
            System.out.format("%s: %s"
                + "  has bowed to me!%n", 
                this.name, bower.getName());
            bower.bowBack(this);
			System.out.println(".");
			
        }
        public synchronized void bowBack(Friend bower) {
            System.out.format("%s: %s"
                + " has bowed back to me!%n",
                this.name, bower.getName());
				System.out.println("L");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        final Friend alphonse = new Friend("Alphonse");// without Friend being a static inner class , this call could not have been made
        final Friend gaston = new Friend("Gaston");
		new Thread(new Runnable() {	
            public void run() { 
				alphonse.bow(gaston); 
			}
        }).start();
		new Thread(new Runnable() {
            public void run() { 
				gaston.bow(alphonse);
			}
        }).start();
    }
}