package app;

public class App {

    public static void main(String[] args) throws Exception {

        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(new someTask());
            t.start();
        }
        System.out.println("*  Main Running in : " + Thread.currentThread());

    }
}