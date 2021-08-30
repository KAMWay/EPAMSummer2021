package practice5;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Part1 {

    private static final long TIME = 2000;

    private static final long PER_TIME = 500;

    private static final Logger LOG = Logger.getLogger(Part1.class.getName());

    static class MyThread extends Thread {

        @Override
        public void run() {
            printOnSchedule(this.getName());
        }

        private void printOnSchedule(String str) {
            long t = System.currentTimeMillis();
            while (System.currentTimeMillis() - t < TIME) {
                System.out.println(str);
                try {
                    sleep(PER_TIME);
                } catch (InterruptedException e) {
                    interrupt();
                    LOG.log(Level.WARNING, e.getMessage());
                }
            }
        }

    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            printOnSchedule(Thread.currentThread().getName());
        }

        private void printOnSchedule(String str) {
            long t = System.currentTimeMillis();
            while (System.currentTimeMillis() - t < TIME) {
                System.out.println(str);
                try {
                    Thread.sleep(PER_TIME);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    LOG.log(Level.WARNING, e.getMessage());
                }
            }
        }

    }

    public static void main(String[] args) {
        startThreadWithJoin(new MyThread());
        startThreadWithJoin(new Thread(new MyRunnable()));
    }

    private static <T extends Thread> void startThreadWithJoin(T thread) {
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            thread.interrupt();
            LOG.log(Level.WARNING, e.getMessage());
        }
    }
}