package practice4;

import java.io.*;
import java.security.SecureRandom;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Part2 {

    private static final Logger LOG = Logger.getLogger(Part2.class.getName());

    private static void sort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = array.length - 1; j > i; j--) {
                if ((array[j - 1]) > (array[j])) {
                    int tmp = array[j - 1];
                    array[j - 1] = array[j];
                    array[j] = tmp;
                }
            }
        }
    }

    private static String getInput(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            Scanner scanner = new Scanner(new File(fileName), "Cp1251");
            while (scanner.hasNextInt()) {
                sb.append(scanner.nextInt()).append(" ");
            }
            scanner.close();
            return sb.toString().trim();
        } catch (IOException ex) {
            LOG.log(Level.WARNING, ex.getMessage());
        }
        return sb.toString();
    }

    private static String arrayToString(int[] array) {
        StringBuilder sb = new StringBuilder();
        for (int i : array) sb.append(i).append(" ");
        return sb.toString().trim();
    }

    private static void setInput(String fileName, int[] array) {
        byte[] buffer = arrayToString(array).getBytes();
        try (FileOutputStream output = new FileOutputStream(fileName)) {
            output.write(buffer, 0, buffer.length);
        } catch (IOException ex) {
            LOG.log(Level.INFO, ex.getMessage());
        }
    }

    private static int[] getRandomIntArray(int number, int bound) {
        SecureRandom random = new SecureRandom();
        int[] array = new int[number];
        for (int i = 0; i < number; i++) {
            array[i] = random.nextInt(bound);
        }
        return array;
    }

    public static void main(String[] args) {
        int[] array = getRandomIntArray(10, 50);
        setInput("part2.txt", array);
        sort(array);
        setInput("part2_sorted.txt", array);

        String input = getInput("part2.txt");
        System.out.println("input ==> " + input);
        input = getInput("part2_sorted.txt");
        System.out.println("output ==> " + input);
    }

}