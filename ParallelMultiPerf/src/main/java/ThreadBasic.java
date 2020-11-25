public class ThreadBasic {
  public static void oneBasicThreadDemo() {
    System.out.println("before");
    Thread t = new Thread(() -> System.out.println("From thread"));
    t.start();
    System.out.println("After");
  }
  
  public static void threadWithNameAndPriority() {
    System.out.println("before");
    Thread t = new Thread(() -> System.out.println(String.format("Thread Name: %s, Priority: %d", Thread.currentThread().getName(), Thread.currentThread().getPriority())));
    t.setName("threadWithNameAndPriority");
    t.setPriority(Thread.MAX_PRIORITY);
    t.start();
    System.out.println("After");
  }
  
  public static void main(String[] args) throws InterruptedException {
    ThreadBasic.oneBasicThreadDemo();
//    Thread.sleep(1000);
    ThreadBasic.threadWithNameAndPriority();
  }
}

/* OP
before
After
From thread
main woke up
 */