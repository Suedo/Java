package pogrebinsky;

import java.time.Duration;
import java.time.Instant;

public class MyThreadUtils {
  
  /**
   * simulates long running work by napping #scale times, each 10 ms duration
   * @param scale double, 10 ms delay for each count, 0 - 1000 should be a good range
   */
  public static void simulateWork(double scale) {
    System.out.println("Starting work, diff: " + scale);
    for (int i = 0; i < scale; i++) {
      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
        System.out.println("prematurely interrupted, at i = " + i);
        return; // imp, else thread will keep looping, as otherwise exception is not handled, but just logged
      }
    }
  }
  
  public static class FakeWorker extends Thread {
    private boolean isFinished = false;
    private double difficulty = 0;
    public long runningTime = 0L;
    
    @Override
    public void run() {
      Instant start = Instant.now();
      double d = difficulty > 0 ? difficulty : Math.abs(Math.random() * 1000) + 10;
      simulateWork(d);
      Instant end = Instant.now();
      runningTime = Duration.between(start, end).toMillis();
      isFinished = true;
    }
    
    public boolean isFinished() {
      return isFinished;
    }
    
    public FakeWorker setDifficulty(double d) {
      assert d > 0 : "difficulty should be greater than 0";
      difficulty = d;
      return this;
    }
  }
}
