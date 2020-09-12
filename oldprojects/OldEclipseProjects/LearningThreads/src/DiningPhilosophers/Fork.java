package DiningPhilosophers;

public class Fork {
	
	public final boolean isClean;
	public final int id;
	
	public Fork(int id , boolean isClean){
		this.id = id;
		this.isClean = isClean;
	}
}
