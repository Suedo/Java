package DiningPhilosophers;

public class PhilosopherSpecification{
	public final int id;
	public final Philosopher left;
	public final Philosopher right;
	public final long thinkTime;
	
	public PhilosopherSpecification(int id , Philosopher l , Philosopher r , int t){
		this.id = id;
		left = l;
		right = r;
		thinkTime = t;			
	}
}