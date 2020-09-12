class Animal{

   public void move(){
      System.out.println("Animals can move");
   }
}

class Dog extends Animal{
	@Override
   public void move(){
//      super.move(); // invokes the super class method
      System.out.println("Dogs can walk and run");
   }
}

public class test{

   public static void main(String args[]){

      Animal b = new Dog(); // Animal reference but Dog object
      b.move(); //overriding is determined at runtime , and works wrt the object and not the reference

   }
}
