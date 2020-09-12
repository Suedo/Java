package week2;

public class Summation {

	public Double calcFrom(int lower, int upper, Function f) {
		double acc = 0;
		while (lower <= upper) {
			acc += f.definition(lower++);
		}
		return acc;
	}

	public static void main(String[] args) {
		Summation sigma = new Summation();

		// implement definition(double) according to your mathematical function
		// this one does a sum of integers
		System.out.println(sigma.calcFrom(1, 19, new Function() {

			@Override
			public double definition(double x) {
				return x; // this can be more complex
			}
		}));
	}
}
