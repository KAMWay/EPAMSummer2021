package practice5;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Part3 {

    private int counter;

    private int counter2;

    private static final Logger LOG = Logger.getLogger(Part3.class.getName());

    private final Thread[] threads;

    private final int numberOfIterations;

    public Part3(int numberOfThreads, int numberOfIterations) {
        if (numberOfThreads < 0) numberOfThreads = 0;
        if (numberOfIterations < 0) numberOfIterations = 0;
        this.numberOfIterations = numberOfIterations;
        this.threads = new Thread[numberOfThreads];
    }

    public void start() {
        for (Thread thread : threads) {
            thread.start();
        }
    }

    public void join() {
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                thread.interrupt();
                LOG.log(Level.WARNING, e.getMessage());
            }
        }
    }

    public void resetCounter() {
        counter = 0;
        counter2 = 0;
    }

    public void incrementCounters() {
        for (int j = 0; j < numberOfIterations; j++) {
            System.out.printf("%s == %s is %s%s", counter, counter2, counter == counter2, System.lineSeparator());
            try {
                counter++;
                Thread.sleep(100);
                counter2++;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                LOG.log(Level.WARNING, e.getMessage());
            }
        }
    }

    public static void main(final String[] args) {
        Part3 part3 = new Part3(3, 5);

        part3.resetCounter();
        part3.compare();

        part3.resetCounter();
        part3.compareSync();
    }

    public void compare() {
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(this::incrementCounters);
        }
        start();
        join();
    }

    public void compareSync() {
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                synchronized (this) {
                    this.incrementCounters();
                }
            });
        }
        start();
        join();
    }

}