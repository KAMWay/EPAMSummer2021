package practice4;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Demo {

    private static final InputStream STD_IN = System.in;
    private static final Logger LOG = Logger.getLogger(Demo.class.getName());

    public static String getInput(String fileName) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader =
                     new BufferedReader(
                             new InputStreamReader(
                                     new FileInputStream(fileName), "Cp1251"))) {
            do {
                String str = reader.readLine();
                sb.append(str);
            } while (reader.ready());
        } catch (IOException ex) {
            LOG.log(Level.WARNING, ex.getMessage());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println("=========================== PART1");
        Part1.main(args);

        System.out.println("=========================== PART2");
        Part2.main(args);

        System.out.println("=========================== PART3");
        // set the mock input
        System.setIn(new ByteArrayInputStream(
                "char^String^int^double^stop".replace("^", System.lineSeparator()).getBytes(StandardCharsets.UTF_8)));
        Part3.main(args);
        // restore the standard input
        System.setIn(STD_IN);

        System.out.println("=========================== PART4");
        Part4.main(args);

        System.out.println("=========================== PART5");
        // set the mock input
        System.setIn(new ByteArrayInputStream(
                "table ru^table en^apple ru^stop".replace("^", System.lineSeparator()).getBytes(StandardCharsets.UTF_8)));
        Part5.main(args);
        // restore the standard input
        System.setIn(STD_IN);

        System.out.println("=========================== PART6");
        // set the mock input
        System.setIn(new ByteArrayInputStream(
                "Latn^Cyrl^asdf^latn^cyrl^stop".replace("^", System.lineSeparator()).getBytes(StandardCharsets.UTF_8)));
        Part6.main(args);
        // restore the standard input
        System.setIn(STD_IN);
    }

}
