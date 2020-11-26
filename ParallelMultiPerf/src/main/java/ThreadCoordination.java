public class ThreadCoordination {

  public static class LongRunningTask extends Thread {
    @Override
    public void run() {
      for (int i = 0; i < 1000; i++) {
        System.out.print(i + " ");
        try {
          Thread.sleep(10);
        } catch (InterruptedException e) {
          System.out.println("prematurely interrupted, at i = " + i);
          return; // imp, else thread will keep looping, as otherwise exception is not handled, but just logged
        }
      }
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
