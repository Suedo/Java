import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class ResizingCircularArray<E> {

	private int head = 0;
	private int tail = 0;
	private int size = 0; // a measure of non-null elements in the array
	private E[] arr;

	private void resize() {
		System.out.println("resizing array to size: " + 2*size);
		@SuppressWarnings("unchecked")
		E[] tempArr = (E[]) new Object[2 * size];
		System.arraycopy(arr, head, tempArr, 0, size);
		head = 0;
		tail = head + (size - 1);
		arr = tempArr;
	}

	public void enqueue(E item) {
		if (size == arr.length) {
			resize();
		}
		arr[tail++] = item;
		size++;		
//		System.out.println(" head : " + head + " tail : " + tail + " , size : " + size);
	}

	public E dequeue() {
		if(size == 0) throw new java.lang.NullPointerException("size = 0");
		if (size == arr.length / 4) {
			resize();
		}
		E item = arr[head];
		arr[head++]=null;
		size--;
//		System.out.println(" head : " + head + " tail : " + tail + " , size : " + size);
		return item;
	}

	public int size() {
		return size;
	}

	public void display() {
		for (E item : arr)
			System.out.print(" " + item);
		System.out.println("\n\n");
	}

	@SuppressWarnings("unchecked")
	public ResizingCircularArray() {
		arr = (E[]) new Object[5];
	}

	public static void main(String[] args) {

		ResizingCircularArray<String> r = new ResizingCircularArray<String>();
		String line = null;
		String[] parsed;

		try (BufferedReader is = new BufferedReader(new FileReader("CircArrayPoints.txt"))) {
			while ((line = is.readLine()) != null) {
				if (line.equals("stop"))
					break;
				parsed = line.trim().split("[ ]+");
				switch (parsed[0]) {
				case "enq":
//					System.out.println(" adding : " + parsed[1]);
					r.enqueue(parsed[1]);
					break;
				case "deq":
//					System.out.println(" dequeing ");
					System.out.println(r.dequeue());
					break;
				case "disp":
					r.display();
					break;
				default:
					break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}