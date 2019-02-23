import java.util.*;

class Sample {                                       
  public static void main(String[] args) {           
    List<Integer> numbers = Arrays.asList(1, 2, 3);
    
    numbers.stream()
      .map(e -> e * 2) // e is the parameter to the labda, much like args is the parameter to main
      .forEach(System.out::println);
      
    
    //lambda expresssions do not carry state
    
    int factor = 2; // this is effectively final, as good as being declared final
    
    numbers.stream()
      .map(e -> e * factor) //this is a closure
      .forEach(System.out::println);           
      
    //closures carry state. They close-over (hence closure) the defining scope to bind to
    //variables that are not passed in as parameters. Here factor is closed over.
    
    //closures carry (immutable) state
  }
}


