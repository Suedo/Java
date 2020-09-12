package perfectNum;
// seive of eratosthenes

public class PrimeSeive {

	private boolean[] arr;

	public PrimeSeive(int size) {
		arr = new boolean[size+1];
		
		for (int i = 2; i <= size; i++)
			arr[i] = true;
		
		for (int i = 2; i <= size; i++) 
			if(arr[i]) 
				for(int j = 2 * i; j <= size; j = j + i)
					arr[j] = false;

	}
	public boolean isPrime(int elem){
		if(elem >= arr.length) throw new IndexOutOfBoundsException("out of bounds!");
		return arr[elem]==true;
	}
	public void display(){
		for(int i = 0 , count = 0 ; i < arr.length ; i++){
			if(arr[i]) System.out.format("%3d.	%5d\n", (++count),i);
		}
	}
	
	public static void main(String[] args) {
		new PrimeSeive(1000).display();
	}
}
