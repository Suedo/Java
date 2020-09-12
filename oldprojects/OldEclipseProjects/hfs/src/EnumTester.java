public class EnumTester {


	public static void main(String[] args) {
		curr one = curr.ATANA;
		curr two = curr.CHARANA;
		curr three = curr.TAKA;
		

	}

}

enum curr {
	CHARANA(25), ATANA(50), TAKA(100);
	private int value;

	private curr(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
