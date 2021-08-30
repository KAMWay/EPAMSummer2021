package practice5;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Spam {

    private Thread[] threads;

    private static final Logger LOG = Logger.getLogger(Spam.class.getName());

    private static class Worker extends Thread {

        private final String message;

        private final int delay;

        public Worker(String message, int delay) {
            this.message = message;
            this.delay = delay;
        }

        @Override
        public void run() {
            while (true) {
                System.out.println(message);
                try {
                    Worker.sleep(delay);
                } catch (InterruptedException e) {
                    interrupt();
                    break;
                }
            }
        }

    }

    public Spam(final String[] messages, final int[] delays) {
        if (messages.length == delays.length) {
            threads = new Thread[messages.length];
            for (int i = 0; i < messages.length; i++) {
                threads[i] = new Worker(messages[i], delays[i]);
            }
        }
    }

    public void start() {
        for (Thread thread : threads) {
            thread.start();
        }
    }

    public void stop() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    public static void main(final String[] args) {
        String[] messages = new String[]{"@@@", "bbbbbbb"};
        int[] delays = new int[]{333, 222};

        Spam spam = new Spam(messages, delays);
        spam.start();

        while(true) {
            try {
                if( System.in.read()== KeyEvent.VK_ENTER) {
                    spam.stop();
                    break;
                }
            } catch (IOException e) {
                LOG.log(Level.WARNING, e.getMessage());
            }
        }
    }

}
