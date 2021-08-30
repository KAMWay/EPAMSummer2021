package practice5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Part5 {

    private static final Logger LOG = Logger.getLogger(Part5.class.getName());

    private static final int THREADS_NUMBER = 10;

    private static final int COLUMNS = 20;

    private Thread[] threads;

    private final File file;

    static class NewThreads extends Thread {

        private final int number;

        private final long pointer;

        private final RandomAccessFile raf;

        public NewThreads(int number, RandomAccessFile raf) {
            this.number = number;
            this.pointer = (long) number * (COLUMNS + 1);
            this.raf = raf;
        }

        @Override
        public void run() {
            synchronized (raf) {
                for (int i = 0; i <= COLUMNS; i++) {
                    try {
                        raf.seek(pointer + i);
                        raf.writeBytes(String.valueOf(number));
                        raf.seek(pointer + COLUMNS);
                        raf.writeBytes(System.lineSeparator());
                    } catch (IOException e) {
                        LOG.log(Level.WARNING, e.getMessage());
                    }
                }

                try {
                    TimeUnit.MILLISECONDS.sleep(1);
                } catch (InterruptedException e) {
                    interrupt();
                    LOG.log(Level.WARNING, e.getMessage());
                }
            }
        }

    }

    public Part5(String fileName) {
        this.file = new File(fileName);
    }

    public static String getInput(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            Scanner scanner = new Scanner(new File(fileName), "cp1251");
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine()).append(System.lineSeparator());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            LOG.log(Level.WARNING, e.getMessage());
        }

        return sb.toString();
    }

    public void start() {
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")){
            threads = new Thread[THREADS_NUMBER];
            for (int i = 0; i < threads.length; i++) {
                threads[i] = new NewThreads(i, raf);
                threads[i].start();
            }
            join();
        } catch (IOException e) {
            LOG.log(Level.WARNING, e.getMessage());
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

    public static void main(final String[] args) {
        Part5 part5 = new Part5("part5.txt");
        part5.start();
        System.out.print(Part5.getInput("part5.txt"));
    }

}