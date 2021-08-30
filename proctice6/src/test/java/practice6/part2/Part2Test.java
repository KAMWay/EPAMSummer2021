package practice6.part2;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class Part2Test {

    private List<Integer> list;
    private List<Integer> array;

    @Before
    public void setUp() {
        array = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7);
    }

    @Test
    public void testRemoveByIndexArrayList() {
        list = new ArrayList<>(array);
        Part2.removeByIndex(list, 4);
        assertEquals(1, list.size());
    }

    @Test
    public void testRemoveByIndexLinkedList() {
        list = new LinkedList<>(array);
        Part2.removeByIndex(list, 2);
        assertEquals(1, list.size());
    }

    @Test
    public void testRemoveByIteratorArrayList() {
        list = new ArrayList<>(array);
        Part2.removeByIterator(list, 3);
        assertEquals(1, list.size());
    }

    @Test
    public void testRemoveByIteratorLinkedList() {
        list = new LinkedList<>(array);
        Part2.removeByIterator(list, 4);
        assertEquals(1, list.size());
    }

}