import java.util.*;

class Sample {                                       
  public static void main(String[] args) {           
    //Before jumping to functional, it helps to thing declarative
    
    List<String> names = Arrays.asList("Dory", "Gill", "Bruce", "Nemo", "Darla", "Marlin", "Jacques");
    
    //Imperative 
    boolean found = false;
    
    for(String name : names) {
      if(name.equals("Nemo")) {
        found = true;
        break;
      }
    }         
    
    if(found) {
      System.out.println("Nemo was found");
    } else {
      System.out.println("Nemo not found");
    }
    
    //Declarative
    if(names.contains("Nemo")) {
      System.out.println("Nemo was found");
    } else {
      System.out.println("Nemo not found");
    }
    
    //focus on what instead of how
  }
}


