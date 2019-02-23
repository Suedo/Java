import java.util.*;

class Sample {                                       
  public static void main(String[] args) {           
    
    //Imperative: what and how.    
    //Declarative: what not how
    //Functional: Declarative + higher-order functions
    
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    
    //find the total of sqrt of even numbers

    //Imperative:
    double result = 0;       
    
    for(int e : numbers) {
      if(e % 2 == 0) {
        result += Math.sqrt(e);
      }
    }
    
    System.out.println(result);
    
    //Functional
    System.out.println(
      numbers.stream()
        .filter(e -> e % 2 == 0) //functional (and declarative)
        .mapToDouble(Math::sqrt)  //functional (and declarative)
        .sum());
  }
}


