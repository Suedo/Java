import java.util.*;

class Sample {                                       
  //A method is a function that is attached to a class or object.
  //A function has four parts:
  //1. name
  //2. Return type
  //3. Parameter list
  //4. Body
  
  //Lambdas are anonymous functions whose type is inferred. They have two things:
  // (parameter list) -> body
  
  public static void main(String[] args) {           
    // Thread thread = new Thread(new Runnable() {
    //   public void run() {
    //     ;
    //   }
    // });

    Thread thread = new Thread(() -> System.out.println("In another thread"));
    
    thread.start();
    
    System.out.println("In main thread");
    
    //We can pass objects to functions
    //We can create objects within functions
    //We can return objects from functions.
    
    //Higher order functions:
    //We can pass functions to functions
    //We can create functions within functions
    //We can return functions from functions.
    
  }
}


