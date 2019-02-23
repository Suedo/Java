import java.util.*;
import java.util.stream.*;

class Sample {                                       
  public static void main(String[] args) {           
    List<Integer> numbers = Arrays.asList(1, 2, 3);

    int[] factor = new int[] { 2 };
    
    Stream<Integer> strm = 
      numbers.stream()
        .map(e -> e * factor[0]); //bad idea. Closure is not pure.
        
    //pure functions:
    //1. does not mutate or change anything
    //2. does not depend on anything that changes
    
    factor[0] = 0;
    
    strm.forEach(System.out::println);
    
    //at the least this code is confusing, hard to reason, and may lead to misunderstanding and errors.
    
    //Keep lambdas and closures pure. Don't mutate shared state from within or from outside.
  }
}


