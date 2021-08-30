package practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImpl implements Array {

    private int size;
    private Object[] array;

    public ArrayImpl(int maxSize) {
        size = 0;
        array = new Object[maxSize];
    }

    public ArrayImpl() {
        this(0);
    }

    @Override
    public void clear() {
        this.array = new Object[]{};
    }

    @Override
    public int size() {
        return array.length;
    }

    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {

        private int index = 0;

        @Override
        public boolean hasNext() {
            return array.length != 0 && index < array.length;
        }

        @Override
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return array[index++];
        }

    }

    private void resizeArray() {
        Object[] arrayNew = new Object[array.length + 1];
        for (int index = 0; index < size; index++) {
            arrayNew[index] = array[index];
        }
        array = arrayNew;
    }

    @Override
    public void add(Object element) {
        if (size > array.length - 1) {
            resizeArray();
        }

        array[size++] = element;
    }

    @Override
    public void set(int index, Object element) {
        if (index < array.length) {
            array[index] = element;
        }
    }

    @Override
    public Object get(int index) {
        if (index < array.length && index >= 0) {
            return array[index];
        }
        return null;
    }

    @Override
    public int indexOf(Object element) {
        for (int index = 0; index < array.length; index++) {
            if (array[index] == null) continue;
            if (array[index].equals(element)) {
                return index;
            }
        }

        return -1;
    }

    @Override
    public void remove(int index) {
        if (array != null && index < array.length) {
            Iterator<Object> elementIt = iterator();
            Object[] arrayNew = new Object[array.length - 1];

            int indexArray = 0;
            int countIt = 0;
            while (elementIt.hasNext()) {
                if (countIt++ != index) {
                    arrayNew[indexArray++] = elementIt.next();
                } else elementIt.next();
            }
            array = arrayNew;
            size--;
        }
    }

    @Override
    public String toString() {
        if (array.length == 0) return "[]";

        StringBuilder out = new StringBuilder("[");
        Iterator<Object> iterator = iterator();
        while (iterator.hasNext()) {
            out.append(iterator.next()).append(iterator.hasNext() ? ", " : "]");
        }

        return out.toString();
    }

    public static void main(String[] args) {
        ArrayImpl object = new ArrayImpl();

        object.add("A");
        object.add("B");
        object.add("C");
        System.out.println(object);
        System.out.println(object.size());

        object.add("E");
        System.out.println(object);

        object.set(3, "D");
        object.set(3, null);
        System.out.println(object);
        System.out.println(object.size());

        System.out.println(object.get(1));
        System.out.println(object.indexOf("B"));

        object.remove(3);
        object.remove(4);
        System.out.println(object);

        Iterator<Object> iterator = object.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + (iterator.hasNext() ? " " : ""));
        }

        object.clear();
        System.out.println("\n" + object);
    }

}