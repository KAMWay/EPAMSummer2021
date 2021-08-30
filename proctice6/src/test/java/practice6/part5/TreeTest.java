package practice6.part5;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.Assert.*;

public class TreeTest {

    private static final InputStream STD_IN = System.in;
    private static final PrintStream STD_OUT = System.out;
    private Tree<Integer> tree;

    @Before
    public void setUp() {
        tree = new Tree<>();
        tree.add(1);
        tree.add(2);
        tree.add(5);
        tree.add(4);
    }

    @Test
    public void testAddExistElementIsFalse() {
        assertFalse(tree.add(1));
    }

    @Test
    public void testAddNotExistElementIsTrue() {
        assertTrue(tree.add(10));
    }

    @Test
    public void testAddElementsAndPrint() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos));

        tree.add(new Integer[]{6, 0});
        tree.print();

        assertEquals("  0^1^  2^      4^    5^      6^".replace("^", System.lineSeparator()), bos.toString());
    }

    @Test
    public void testRemoveExistElementIsTrue() {
        assertTrue(tree.remove(1));
    }

    @Test
    public void testRemoveExistElementIsFalse() {
        assertFalse(tree.remove(10));
    }

    @After
    public void afterRestoredToDefault() {
        System.setIn(STD_IN);
        System.setOut(STD_OUT);
    }

}
