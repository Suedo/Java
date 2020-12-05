package pogrebinsky;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.function.IntPredicate;
import java.util.function.IntUnaryOperator;
import java.util.stream.IntStream;

public class HackerAndPoliceDemo {
  
  private static Integer MAX_PASSWORD = 9999;
  
  @AllArgsConstructor
  private static class Vault {
    private int password;
    
    public boolean isCorrect(int pw) {
      try {
        Thread.sleep(5);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
//      System.out.println("Guessing: " + pw);
      return this.password == pw;
    }
  }
  
  private static abstract class Hacker extends Thread {
    protected Vault vault;
    protected int MAX_PASSWORD = 9999;
    
    public Hacker(Vault vault) {
      this.vault = vault;
      this.setName("Hacker");
      this.setPriority(Thread.MAX_PRIORITY);
    }
    
    @Override
    public synchronized void start() {
      System.out.println("Starting Thread: " + this.getName());
      super.start();
    }
  }
  
  /**
   * Will count from 0 to MAX_PASSWORD, incrementing by 1
   */
  public static class AscendingHacker extends Hacker {
    public AscendingHacker(Vault vault) {
      super(vault);
    }
    
    @Override
    public void run() {
      System.out.println("Running AscendingHacker...");
      IntPredicate limit = i -> i <= MAX_PASSWORD;
      IntPredicate correctPassword = i -> this.vault.isCorrect(i);
      IntUnaryOperator step = i -> i + 1;
      boolean passwordFound = !IntStream.iterate(0, limit, step).noneMatch(correctPassword);
      if (passwordFound) {
        System.out.println(String.format("%s guessed the correct passowrd", this.getName()));
        System.exit(0);
      }
    }
  }
  
  /**
   * Will count from MAX_PASSWORD down to 0, decrementing by 1
   */
  public static class DescendingHacker extends Hacker {
    public DescendingHacker(Vault vault) {
      super(vault);
    }
    
    @Override
    public void run() {
      System.out.println("Running DescendingHacker...");
      IntPredicate limit = i -> i >= 0;
      IntPredicate correctPassword = i -> this.vault.isCorrect(i);
      IntUnaryOperator step = i -> i - 1;
      boolean passwordFound = !IntStream.iterate(MAX_PASSWORD, limit, step).noneMatch(correctPassword);
      if (passwordFound) {
        System.out.println(String.format("%s guessed the correct passowrd", this.getName()));
        System.exit(0);
      }
    }
  }
  
  public static class Police extends Thread {
    @Override
    public void run() {
      for (int i = 10; i > 0; i--) {
        try {
          // basically give 10 seconds to the hackers
          System.out.println("Police will arrive in " + i + " seconds");
          Thread.sleep(1000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      System.out.println("Game over hackers");
      System.exit(0);
    }
  }
  
  public static void main(String[] args) {
    int password = 420;
    Vault vault = new Vault(password);
    
    ArrayList<Thread> threadList = new ArrayList<>();
    threadList.add(new AscendingHacker(vault));
    threadList.add(new DescendingHacker(vault));
    threadList.add(new Police());
    
    for (Thread t : threadList) t.start();
  }
  
}
