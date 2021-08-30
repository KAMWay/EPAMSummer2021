package practice6.part3;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class ParkingTest {

    private Parking parking;

    @Before
    public void setUp() {
        parking = new Parking(4);
    }

    @Test
    public void testPrint() {
        assertEquals(Arrays.asList(0, 0, 0, 0), parking.getList());
    }

    @Test
    public void testArriveIsTrue() {
        assertTrue(parking.arrive(2));
    }

    @Test
    public void testArriveIsFalse() {
        for (int i : new int[]{2, 3, 2, 2}) {
            parking.arrive(i);
        }
        assertFalse(parking.arrive(2));
    }

    @Test
    public void testDepartIsTrue() {
        for (int i : new int[]{2, 3, 2, 2, 2}) {
            parking.arrive(i);
        }
        assertTrue(parking.depart(1));
    }

}