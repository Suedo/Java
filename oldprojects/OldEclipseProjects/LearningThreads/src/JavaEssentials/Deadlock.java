package JavaEssentials;

public class Deadlock {
    static class Friend {
    	Object bowLock = new Object();
        Object bowBackLock = new Object();
    	private final String name;
        public Friend(String name) {
            this.name = name;
        }
        public String getName() {
            return this.name;
        }
        public void bow(Friend bowTo) {
        	synchronized(bowLock){
	        	System.out.format( "%s making a bow to %s\n", this.name, bowTo.getName());
	        	bowTo.bowBack(this);
            }            
        }
        public void bowBack(Friend bower) {
        	synchronized(bowBackLock){
        		System.out.format("%s bows back to %s\n",this.name, bower.getName());
        	}
        }
    }

    public static void main(String[] args) {
        final Friend alphonse =
            new Friend("Alphonse");
        final Friend gaston =
            new Friend("Gaston");
        new Thread(new Runnable() {
            public void run() { alphonse.bow(gaston); }
        }).start();
        new Thread(new Runnable() {
            public void run() { gaston.bow(alphonse); }
        }).start();
    }
}