public class test{
	
	public static void main(String[] args) {
		double num = 1e60;
		double estimate = 1.0;
		
		while(!(Math.abs(estimate*estimate - num)/num < .001)){
			estimate = (estimate + (num/estimate))/2;
		}
		System.out.format("square root of %f is \n %f\n",num,estimate);
		
		System.out.println(Math.sqrt(1e60));
		
	}
}