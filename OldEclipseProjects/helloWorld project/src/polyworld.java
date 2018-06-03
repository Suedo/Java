/*
a basic example of polymorphism,inheritance, overloading and overriding
*/
import java.util.Scanner;

class Doctor{
	boolean worksAtHospital;
	
	public void treatPatient(){
		System.out.print("doctor\n he is now");
		System.out.println(" gonna give you medicine :) ");
	}
}

class surgeon extends Doctor{
	public void treatPatient(){ // overriding
		System.out.print("surgeon\n he is now");
		System.out.println(" doing operation!"); 
	}
}

class familyDoctor extends Doctor{
	boolean makesHouseCalls;
	
	public void treatPatient(){ // overriding
		System.out.print("family doctor\n he is now");
		System.out.println(" giving advice..");
	}
}

class goToDoctor{
	public void treatPatient(Doctor d){ // d is an object of type Doctor. d can also be a subclass of Doctor
										//this is overloading (note: overloading has nothing to do with polymorphism or inheritance by itself)
		d.treatPatient();
	}
}

public class polyworld{	
	
	private static Scanner input;

	public static void main(String[] args){
		input = new Scanner(System.in);
		int whichDoctor;
		
		System.out.println(" who do you want to treat you?\n 1.Doctor?\n 2.Surgeon?\n 3.familyDoctor?");
		whichDoctor=input.nextInt();
		
		System.out.print("option entered: " + whichDoctor + ">");
		goToDoctor x = new goToDoctor();
		
		Doctor doc = new Doctor();
		Doctor sur = new surgeon(); // polymorphism : doctor is superclass, surgeon is subclass
		Doctor fam = new familyDoctor(); // polymorphism : doctor is superclass, family doctor is subclass
		
		if(whichDoctor==1)
		x.treatPatient(doc); // passing object as argument
		else if(whichDoctor==2)
		x.treatPatient(sur);
		else if(whichDoctor==3)
		x.treatPatient(fam);
		
	}
}