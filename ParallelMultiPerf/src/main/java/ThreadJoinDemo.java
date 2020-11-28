import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ThreadJoinDemo {
  
  public static void main(String[] args) throws InterruptedException {
    List<Double> difficulties = Arrays.asList(100.0, 200.0, 150.0, 300.0, 3000.0);
    List<MyThreadUtils.FakeWorker> workers = new ArrayList<>();
    for (double d : difficulties) {
      workers.add(new MyThreadUtils.FakeWorker().setDifficulty(d));
    }
    
    for (MyThreadUtils.FakeWorker w : workers) {
      w.setDaemon(true); // not elegant, make any long running thread terminate when main does as well
      w.start();
    }
    
    for (MyThreadUtils.FakeWorker w : workers) {
      // w.join(); // if any one thread is long running, this will wait, and thus hang, even though other threads might have finished
      w.join(4000); // wait max for 4 secs, but still `main` doesn't terminate until all threads are done
    }
    
    for (MyThreadUtils.FakeWorker w : workers) {
      if (w.isFinished()) System.out.println("Running time: " + w.runningTime);
      else System.out.println(w.getName() + " is still running");
    }
    
    // end of main, any long running thread is terminated here
  }
  
}
