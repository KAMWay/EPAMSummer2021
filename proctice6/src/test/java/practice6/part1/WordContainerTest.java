package practice6.part1;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class WordContainerTest {

    private WordContainer wc;

    @Before
    public void setUp() {
        wc = new WordContainer();
        wc.setWord("a");
        wc.setWord("d");
        wc.setWord("c");
        wc.setWord("b");
        wc.setWord("c");
    }

    @Test
    public void testSetWord() {
        List<Word> wordsList = new ArrayList<>();
        wordsList.add(new Word("a", 1));

        assertEquals(wordsList.get(0), wc.getWords().get(0));
    }

    @Test
    public void testGetWord() {
        assertEquals("a", wc.getWords().get(0).getContent());
    }

    @Test
    public void testSortAndToString() {
        String str = "c : 2^a : 1^b : 1^d : 1".replace("^", System.lineSeparator());
        wc.sort();
        assertEquals(str, wc.toString());
    }

}
