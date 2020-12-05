package pogrebinsky;

public class ThreadCoordination {

  public static class LongRunningTask extends Thread {
    @Override
    public void run() {
      MyThreadUtils.simulateWork(1000);
    }
  }
  
  public static void main(String[] args) throws InterruptedException {
    LongRunningTask longRunningTask = new LongRunningTask();
    longRunningTask.start();
    
    Thread.sleep(200);
    longRunningTask.interrupt();
  }
}

/*
OP:
0 1 2 3 4 5 6 7 8 9 10 11 12 prematurely interrupted, at i = 12
 */
