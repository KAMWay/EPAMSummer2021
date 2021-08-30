package practice6.part6;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class Part6Test {

    private static final InputStream STD_IN = System.in;
    private static final PrintStream STD_OUT = System.out;
    private List<String> input;
    private ByteArrayOutputStream bos;

    @Before
    public void setUp() {
        bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        String in = "jaguar chimpanzee bison whale marmot bison lemur panther camel " +
                "lizard wolf bear gecko mongoose leopard sable sable dingo whale jaguar " +
                "rat lemur lemur gorilla zebra tortoise asp lion tapir tortoise gorilla " +
                "cheetah bison marten marmot cheetah camel snake marmot zebra asp cheetah " +
                "lizard gecko gorilla asp lion tortoise kangaroo whale penguin yak cheetah " +
                "mouse panther";
        input = new ArrayList<>(Arrays.asList(in.split(" ")));
    }

    @Test
    public void testPart61() {
        Map<String, Long> wordsFrequencyMap = Part61.getWordsWithFrequency(input);
        Long frequency = Part61.getMostOften3Frequency(wordsFrequencyMap);
        Part61.print(Part61.getFirst3StringWithFrequency(input, wordsFrequencyMap, frequency));

        assertEquals(
                "whale ==> 3^cheetah ==> 4^bison ==> 3^".replace("^", System.lineSeparator()),
                bos.toString()
        );
    }

    @Test
    public void testPart62() {
        Map<String, Integer> wordsLengthMap = Part62.getWordsWithLength(input);
        Map<String, Integer> sortMap = Part62.getFirst3SortString(wordsLengthMap);
        Part62.print(sortMap);

        assertEquals(
                "chimpanzee ==> 10^mongoose ==> 8^tortoise ==> 8^".replace("^", System.lineSeparator()),
                bos.toString()
        );
    }

    @Test
    public void testPart63() {
        List<String> duplicatesList = Part63.getDuplicatesStringsList(input);
        duplicatesList = Part63.getFirst3DuplicateToList(input, duplicatesList);
        duplicatesList = Part63.reversStringAndUpper(duplicatesList);
        duplicatesList.forEach(System.out::println);

        assertEquals(
                "RAUGAJ^NOSIB^ELAHW^".replace("^", System.lineSeparator()),
                bos.toString()
        );
    }

    @After
    public void afterRestoredToDefault() {
        System.setIn(STD_IN);
        System.setOut(STD_OUT);
    }

}
