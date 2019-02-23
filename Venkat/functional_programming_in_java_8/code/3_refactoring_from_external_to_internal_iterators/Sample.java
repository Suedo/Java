import java.util.*;
import java.util.function.*;

class Sample {                                       
  public static void main(String[] args) {           
    List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

    //External iterators
    
    //familiar and complex
    // for(int i = 0; i < numbers.size(); i++) {
    //   System.out.println(numbers.get(i));
    // }
              
    //familiar and simpler than the previous one
    // for(int e : numbers) {
    //   System.out.println(e);
    // }

    //Internal iterators  
    // numbers.forEach(new Consumer<Integer>() {
    //   public void accept(Integer value) {
    //     System.out.println(value);
    //   }
    // });


    // numbers.forEach((Integer value) -> System.out.println(value));

    // numbers.forEach((value) -> System.out.println(value));

    // numbers.forEach(value -> System.out.println(value));

    numbers.forEach(System.out::println);
  }
}


