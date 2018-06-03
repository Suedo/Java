class Alpha {
	String name = "Alpha";

	Alpha() {
		print();
	}

	void print() {
		System.out.println("Alpha Constructor");
	}
}

class Beta extends Alpha {
	int i = 5;
	String name = "Beta";

	public static void main(String[] args) {
		Alpha a = new Beta();
		a.print();// Line1 executes Beta constructor
		System.out.println("B's main : " + a.name);// Line 2 displays Alpha
													// instance variable
	}

	void print() {
		System.out.println("inside B : " + i);
	}
}