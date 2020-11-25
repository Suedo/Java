import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class ResizingCircularArray<E>{

	private int head = 0;
	private int tail = 0;
	private int size = 0; // a measure of non-null elements in the array
	private E[] arr;
	
	// Modified version of System.arraycopy() to work with circular array.
	private void arrayCopy(E[] srcArr , int srcpos , E[] destArr , int destpos , int length){
		for(int index = 0 ; index < length ; index++){
			destArr[index] = srcArr[head++];
			if(head == srcArr.length){
				head = (head % srcArr.length);
			}
		}
	}

	private void resize() {
		System.out.println("resizing array to size: " + 2 * size);
		@SuppressWarnings("unchecked")
		E[] tempArr = (E[]) new Object[2 * size];
		arrayCopy(arr, head, tempArr, 0, size);
		head = 0;
		tail = size; // tail point to where the NEXT element will land
		arr = tempArr;
	}

	@SuppressWarnings("unchecked")
	public ResizingCircularArray() {
		arr = (E[]) new Object[3];

	}

	public void enqueue(E item) {
		if (item == null)
			throw new NullPointerException(
					" adding null values is not allowed ");
		if (size == arr.length) {
			resize();
		}
		if (tail == arr.length) {
			// going round
			tail = (tail % arr.length);
		}
		arr[tail++] = item;
		size++;
		System.out.println("head : " + head + " tail : " + tail + " , size : "
				+ size);
	}

	public E dequeue() {
		if (!(size > 0))
			throw new java.util.NoSuchElementException("size is negative");
		E item = arr[head];
		arr[head++] = null;
		if (head == (arr.length)) {
			head = (head % arr.length); // =0
		}
		--size;
		if (size == arr.length / 4) {
			resize();
		}
		System.out.println("head : " + head + " tail : " + tail + " , size : "
				+ size);
		return item;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public E sample(int offset) {
		if (offset < 0)
			throw new java.lang.IllegalArgumentException(
					"provided offset is out of bounds");
		return arr[head + offset];
		/*
		 * NOTE : the check for (head+offset)>tail as pointed out by sos will
		 * work in case of linear array , Not in case of Circular array because
		 * when tail comes around in a circle , tail will be < than head and the
		 * above check will create trouble
		 */
	}

	public int size() {
		return size;
	}
	
//	@Override
//	public int compareTo(E o) {
//		if()
//		return 0;
//	}

	public void display() {
		for (E item : arr)
			System.out.print(item + " ");
		System.out.println("\n");
	}

	public static void main(String[] args) {

		ResizingCircularArray<String> r = new ResizingCircularArray<String>();
		String line = null;
		String[] segment, parsed;
		boolean endFlag = false;
		int count = 0;

		try (BufferedReader is = new BufferedReader(new FileReader(
				"CircArrayPoints.txt"))) {
			line = is.readLine();
			segment = line.trim().split(";");
			System.out.println("total commands : " + segment.length);
			for (int i = 0; !segment[i].equals("stop") && !endFlag; i++) {
				parsed = segment[i].split(" ");
				count++;
				switch (parsed[0]) {
				case "enq":
					System.out.println(count+ ".adding : " + parsed[1]);
					r.enqueue(parsed[1]);
					r.display();
					break;
				case "deq":
					if (r.isEmpty()) {
						System.out.println("Empty queue");
						endFlag = true;
						break;
					}
					// print after checking isEmpty() to make sure
					// sample(0) doesn't call null etc
					System.out.println(count+ ".dequeing : " + r.sample(0));
					r.dequeue();
					r.display();
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