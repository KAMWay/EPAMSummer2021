package practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueImpl implements Queue {

    private Object[] queue;
    private int maxSize;
    private int size;
    private int head;
    private int tail;

    public QueueImpl(int maxSize) {
        this.maxSize = maxSize;
        queue = new Object[maxSize];
        tail = -1;
        head = 0;
        size = 0;
    }

    public QueueImpl() {
        this(0);
    }

    @Override
    public void clear() {
        queue = new Object[maxSize];
        tail = -1;
        head = 0;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {

        private int currHead = head;
        private int index = size;

        @Override
        public boolean hasNext() {
            return size != 0 && index != 0;
        }

        @Override
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Object element = queue[currHead++];
            if (currHead == maxSize) currHead = 0;
            index--;
            return element;
        }

    }

    @Override
    public void enqueue(Object element) {
        if (size == maxSize) {
            Object[] tmpQueue = new Object[maxSize + 1];
            Iterator<Object> elementIt = iterator();
            int index = 0;

            while (elementIt.hasNext()) {
                tmpQueue[index++] = elementIt.next();
            }
            head = 0;
            tail = maxSize - 1;
            size = maxSize++;
            queue = tmpQueue;
        }

        if (tail == maxSize - 1) {
            tail = 0;
        }
        queue[++tail] = element;
        size++;
    }

    @Override
    public Object dequeue() {
        if (size == 0) return null;

        Object element = queue[head];
        queue[head++] = null;
        if (head == maxSize) {
            head = 0;
        }
        size--;

        return element;
    }

    @Override
    public Object top() {
        return queue[head];
    }

    @Override
    public String toString() {
        if (this.size == 0) return "[]";

        StringBuilder out = new StringBuilder("[");
        Iterator<Object> iterator = iterator();
        while (iterator.hasNext()) {
            out.append(iterator.next()).append(iterator.hasNext() ? ", " : "]");
        }

        return out.toString();
    }

    public static void main(String[] args) {
        QueueImpl object = new QueueImpl();
        System.out.println(object);

        object.enqueue("A");
        object.enqueue("B");
        object.enqueue("C");
        System.out.println(object);

        System.out.println(object.size());

        object.enqueue("D");
        System.out.println(object);
        System.out.println(object.size());

        System.out.println(object.dequeue());
        System.out.println(object);

        System.out.println(object.top());

        Iterator<Object> iterator = object.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + (iterator.hasNext() ? " " : ""));
        }

        object.clear();
        System.out.print("\n" + object);
    }

}