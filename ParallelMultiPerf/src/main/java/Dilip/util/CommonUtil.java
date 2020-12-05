package Dilip.util;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.time.StopWatch;

import static java.lang.Thread.sleep;

@Log4j2
public class CommonUtil {
  
  public static StopWatch stopWatch = new StopWatch();
  
  public static void delay(long delayMilliSeconds) {
    try {
      sleep(delayMilliSeconds);
    } catch (Exception e) {
      log.error("Exception is :" + e.getMessage());
    }
  }
  
  public static String transForm(String s) {
    CommonUtil.delay(500);
    return s.toUpperCase();
  }
  
  public static void startTimer() {
    stopWatch.start();
  }
  
  public static void timeTaken() {
    stopWatch.stop();
    log.info("Total Time Taken : " + stopWatch.getTime());
  }
  
  public static void stopWatchReset() {
    stopWatch.reset();
  }
  
  public static int noOfCores() {
    return Runtime.getRuntime().availableProcessors();
  }
}
