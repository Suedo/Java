package app;

public class someTask implements Runnable {

    @Override
    public void run() {
        System.out.println(String.format("Task running in %s", Thread.currentThread()));
    }

}