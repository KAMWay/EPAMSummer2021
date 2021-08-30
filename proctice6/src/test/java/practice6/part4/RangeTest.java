package practice6.part4;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class RangeTest {

    private Range range;

    @Before
    public void setUp() {
        range = new Range(3, 10);
    }

    @Test
    public void testRange() {
        final List<Integer> list = new ArrayList<>();
        for (Integer el : range) {
            list.add(el);
        }
        assertEquals(Arrays.asList(3, 4, 5, 6, 7, 8, 9, 10), list);
    }

    @Test
    public void testFirstBound() {
        assertEquals(3, range.getFirstBound());
    }

    @Test
    public void testSecBound() {
        assertEquals(10, range.getSecBound());
    }

    @Test
    public void testReversedOrderIsFalse() {
        assertFalse(range.isReversedOrder());
    }

    @Test
    public void testIterator() {
        assertTrue(range.iterator().hasNext());
    }

}
