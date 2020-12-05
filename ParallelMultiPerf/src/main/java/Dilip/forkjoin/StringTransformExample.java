package Dilip.forkjoin;

import Dilip.util.DataSet;
import lombok.extern.log4j.Log4j2;

import java.util.ArrayList;
import java.util.List;

import static Dilip.util.CommonUtil.delay;
import static Dilip.util.CommonUtil.stopWatch;

@Log4j2
public class StringTransformExample {
  
  public static void main(String[] args) {
    
    stopWatch.start();
    List<String> resultList = new ArrayList<>();
    List<String> names = DataSet.namesList();
    log.info("names : " + names);
    
    names.forEach((name) -> {
      String newValue = addNameLengthTransform(name);
      resultList.add(newValue);
    });
    stopWatch.stop();
    log.info("Final Result : " + resultList);
    log.info("Total Time Taken : " + stopWatch.getTime());
  }
  
  
  private static String addNameLengthTransform(String name) {
    delay(500);
    return name.length() + " - " + name;
  }
}
