import java.util.*;

class Sample {                  
  public static void main(String[] args) {           
    //goto is to structured programming as assignment is to functional programming
    
    for(int i = 0; i < 10; i++) {
      if(i > 5) {
        System.out.println(i);
      }
    }                       
    
    //Compile. Then use javap -c Sample to view the bytecode. Notice goto.
    //We are not allowed to use goto, but there is goto under the hood.
    //Likewise, FP discourages us from using assignment, but there may be assignments in layers below.
  }
}


