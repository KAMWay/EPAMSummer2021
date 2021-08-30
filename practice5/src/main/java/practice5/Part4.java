package practice5;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Part4 {

    private static final Logger LOG = Logger.getLogger(Part4.class.getName());

    private static final int M = 4;

    private static final int N = 100;

    private static final String FILE_NAME ="part4.txt";

    private static int[][] array;

    private static int[][] getInput() {
        int[][] inputArray = new int[M][N];
        try {
            Scanner scanner = new Scanner(new File(FILE_NAME), "UTF-8");
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    if (scanner.hasNextInt()) {
                        inputArray[i][j] = scanner.nextInt();
                    }
                }
            }
            scanner.close();
        } catch (IOException ex) {
            LOG.log(Level.WARNING, ex.getMessage());
        }

        return inputArray.clone();
    }

    private static int getMaxValue(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }

        }

        return max;
    }

    private static void singleThread() {
        long time = System.currentTimeMillis();
        int max = array[0][0];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (array[i][j] > max) {
                    max = array[i][j];
                }
            }
        }
        System.out.println(max);
        System.out.println((System.currentTimeMillis() - time));
    }

    private static void multiplyThreads() {
        final int[] temp = new int[M];
        long time = System.currentTimeMillis();

        for (int i = 0; i < M; i++) {
            final int index = i;

            Thread myThread = new Thread(() -> temp[index] = getMaxValue(array[index]));
            myThread.start();

            try {
                myThread.join();
            } catch (InterruptedException e) {
                myThread.interrupt();
                LOG.log(Level.WARNING, e.getMessage());
            }
        }

        System.out.println(getMaxValue(temp));
        System.out.println(System.currentTimeMillis() - time);
    }

    public static void main(final String[] args) {
        array = getInput();
        singleThread();
        multiplyThreads();
    }

}