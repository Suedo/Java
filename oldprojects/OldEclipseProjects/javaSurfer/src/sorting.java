class sorting<E> {
	public static void exch(Comparable[] a, int i, int j) {
		Comparable temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static boolean less(Comparable e1, Comparable e2) {
		return e1.compareTo(e2) < 0;
	}

	public void getArray() {
	}

	public void sortArray() {
	}

	public void displayArray() {
	}
}
