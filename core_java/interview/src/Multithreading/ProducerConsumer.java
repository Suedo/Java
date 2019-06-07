package Multithreading;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProducerConsumer {

    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<>(10);

        /* Runs, but says "Process finished with exit code -1" when stopped in IDE
        int coreCount = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(coreCount);

        for (int i = 0; i < 10 ; i++) {
            executorService.execute(new MyProducer(blockingQueue));
            executorService.execute(new MyConsumer(blockingQueue));
        }
        */

        Thread producer = new Thread(new MyProducer(blockingQueue));
        Thread consumer = new Thread(new MyConsumer(blockingQueue));

        producer.start();
        consumer.start();
    }
}


class MyProducer implements Runnable {

    BlockingQueue<Integer> queue;
    private int counter;

    MyProducer(BlockingQueue<Integer> queue) {
        super();
        this.queue = queue;
        counter = 0;
    }

    @Override
    public void run() {
        try {
            while(true){
                this.queue.put(counter);
                System.out.println("Producer adds " + counter);
                counter++;
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class MyConsumer implements Runnable {

    BlockingQueue<Integer> queue;

    MyConsumer(BlockingQueue<Integer> queue) {
        super();
        this.queue = queue;
    }

    @Override
    public void run() {

        try {
            while (true) {
                System.out.println("Consumer takes " + this.queue.take());
                Thread.sleep(100);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}