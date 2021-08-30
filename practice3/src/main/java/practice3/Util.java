package practice3;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Util {

    private static final Logger LOG = Logger.getLogger(Util.class.getName());

    public static String getInput(String fileName) {
        StringBuilder sb = new StringBuilder();
        try {
            Scanner scanner = new Scanner(new File(fileName), "UTF-8");
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine()).append(System.lineSeparator());
            }
            scanner.close();
            return sb.toString().trim();
        } catch (IOException ex) {
            LOG.log(Level.INFO, ex.getMessage());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String input = Util.getInput("part1.txt");
        System.out.println(input);
    }

}