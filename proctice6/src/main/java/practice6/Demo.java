package practice6;

import com.epam.rd.java.basic.practice6.part1.Part1;
import com.epam.rd.java.basic.practice6.part2.Part2;
import com.epam.rd.java.basic.practice6.part3.Part3;
import com.epam.rd.java.basic.practice6.part4.Part4;
import com.epam.rd.java.basic.practice6.part5.Part5;
import com.epam.rd.java.basic.practice6.part6.Part6;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Demo {

    private static final InputStream STD_IN = System.in;
    private static final String FILENAME = "part6.txt";

    public static void main(String[] args) {
        System.out.println("=========================== PART1");
        System.setIn(new ByteArrayInputStream(
                "asd 43 asdf asd 43^434 asdf^kasdf asdf stop asdf^stop".replace("^", System.lineSeparator()).getBytes(StandardCharsets.UTF_8)));
        Part1.main(args);
        System.setIn(STD_IN);

        System.out.println("=========================== PART2");
        System.setIn(new ByteArrayInputStream(
                "10000 4^".replace("^", System.lineSeparator()).getBytes(StandardCharsets.UTF_8)));
        Part2.main(args);
        System.setIn(STD_IN);

        System.out.println("=========================== PART3");
        System.setIn(new ByteArrayInputStream(
                "4^".replace("^", System.lineSeparator()).getBytes(StandardCharsets.UTF_8)));
        Part3.main(args);
        System.setIn(STD_IN);

        System.out.println("=========================== PART4");
        Part4.main(args);

        System.out.println();
        System.out.println("=========================== PART5");
        Part5.main(args);

        System.out.println("=========================== PART6");
        Part6.main(new String[]{"--input", FILENAME, "--task", "frequency"});
        Part6.main(new String[]{"-i", FILENAME, "-t", "length"});
        Part6.main(new String[]{"--input", FILENAME, "--task", "duplicates"});
    }

}
