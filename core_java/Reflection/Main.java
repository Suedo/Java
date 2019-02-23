public class Main {
		
	public static void main(String[] args) throws Exception {
		
		Class cls = Car.class;
		Method[] methods = cls.getMethods(); // all methods
		methods = cls.getDeclaredMethods(); // only the methods we wrote/created
		
		
		Class superClass = cls.getSuperclass();
		
		while(superClass!=null){
			// print superClass name: getName()
			superClass = superClass.getSuperclass(); // climb up
		}
		
		
		/**
		 getting methods
		*/
		for(Method m : methods) {
			String name = m.getName();
			String paramCount = m.getParameterCount(); 
			// print them out 
		}
		
		Constructor[] ctors = cls.getDeclaredConstructors();
		// this is kinda silly though, as we already have the car. just a demo
		Car car = ctors[1].newInstance(6); // use the constructor : Car(int doors) , to create an object
		
		
		
		
	}
	
}

public class Car extends Vehicle{

	int speed;
	char driveType;
	
	public Car(){
		this(4);
	}
	
	public Car(int doors){
		
	}
	
	public int drive(char driveType, int desiredSpeed){
		selectDrive(driveType);
		
		while (speed != desiredSpeed) {
			accelerate();
		}
		
		return speed;
		
	}
	
	
	private int accelerate(){
		return ++speed;
	}
	
	private void selectDrive(char driveType){
		this.driveType = driveType;
	}

}

public class Vehicle {
	
	
}