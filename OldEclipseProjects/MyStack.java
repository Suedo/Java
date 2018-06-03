/*
basic elementary resizing array based stack.
will add underflow pop() exception handling in future editions
*/
class MyStack{
	
	String[] arr = new String[5];
	int size = 0;
	
	public void push(String s){
		if(size == arr.length){
			resize(2*size);
		}
//		System.out.println(" " + s);
		arr[size++] = s;
	}
	public String pop(){
		if(size == arr.length/4){
			resize(2*size);
		}
		String item = arr[--size];
		arr[size] = null;
//		System.out.println(" " + item);
		return item;		
	}
	public void printStack(){
		System.out.println("\n\n\n printing the stack in actual order : ");
		for(int i = ( arr.length - 1 ) ; i > -1 ; i--){
			System.out.println(arr[i]);
		}
	}
	private void resize(int capacity){
//		System.out.println("\n\n\n resizing array to size: " + capacity );
		String[] newArr = new String[capacity];
		for(int i = 0 ; i < size ; i++ ){ // copying contents of arr into newArr 
			newArr[i] = arr[i];
		}
		arr = newArr;	
	}
	public static void main(String[] args){
		MyStack m = new MyStack();
//		System.out.println("\n pushing : ");
		m.push("one");
		m.push("two");
		m.push("three");
		m.push("four");
		m.push("five");
		m.push("six");
		m.push("seven");
		m.push("eight");
//		System.out.println("\n popping : ");
		m.pop();
		m.pop();
		m.pop();
		m.pop();
		m.pop();
		m.pop();
		m.pop();
		m.push("and only");
		m.printStack();
	
	}
}