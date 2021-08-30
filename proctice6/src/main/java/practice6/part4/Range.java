package practice6.part4;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Range implements Iterable<Integer> {

    private final int firstBound;

    private final int secBound;

    private final boolean reversedOrder;

    public Range(int n, int m) {
        this(n, m, false);
    }

    public Range(int firstBound, int secBound, boolean reversedOrder) {
        if (firstBound < secBound) {
            this.firstBound = firstBound;
            this.secBound = secBound;
        } else {
            this.firstBound = secBound;
            this.secBound = firstBound;
        }
        this.reversedOrder = reversedOrder;
    }

    public int getFirstBound() {
        return firstBound;
    }

    public int getSecBound() {
        return secBound;
    }

    public boolean isReversedOrder() {
        return reversedOrder;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new IteratorImpl();
    }

    private final class IteratorImpl implements Iterator<Integer> {

        private int pointer = reversedOrder ? secBound : firstBound;

        @Override
        public boolean hasNext() {
            return reversedOrder ? pointer >= firstBound : pointer <= secBound;
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return reversedOrder ? pointer-- : pointer++;
        }
    }

}
