public class out {
	String s = "global thoughts";
	class inn{ // inner class
		public String innerThoughts(){
			return s;
		}
	}
	public Object outerThoughts(){ // outer class
		final String s = "outer thoughts"; // why need final here ?
		/*class inn{ // inner class
			public String innerThoughts(){
				return s; //without final , this showed an error that cannot refer to a non final variable inside an inner class defined in a diff method
			}
		}*/
		return new inn(); // in an effort that whatever piece of code gets this , it can invoke its innerThoughts method . ( in vain though )
	}
	
	public static void main(String[] args){
		out outer = new out(); 
		// inn inner = new inn(); this doesnt work obviously , as inn is not really inner class. and also , i cant think of a way to receive the class inn{} in here either.
		
		//outer.outerThoughts().innerThoughts(); // this also wont work , as returned type is Object , and cannot cast it to inn{}
		out.inn inner =  outer.new inn();
		System.out.println(inner.innerThoughts());
	}
}
