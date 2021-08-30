package practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackImpl implements Stack {

    private int maxSize;
    private Object[] stack;
    private int top;

    public StackImpl() {
        this(0);
    }

    public StackImpl(int maxSize) {
        this.maxSize = maxSize;
        stack = new Object[maxSize];
        top = -1;
    }

    @Override
    public void clear() {
        stack = new Object[maxSize];
        top = -1;
    }

    @Override
    public int size() {
        return top + 1;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {

        int currTop = top;

        @Override
        public boolean hasNext() {
            return currTop >= 0;
        }

        @Override
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return stack[currTop--];
        }

    }

    @Override
    public void push(Object element) {
        if (top == maxSize - 1) {
            Object[] tmpStack = new Object[++maxSize];
            for (int index = top; index >= 0; index--) {
                tmpStack[index] = stack[index];
            }
            stack = tmpStack;
        }

        stack[++top] = element;
    }

    @Override
    public Object pop() {
        if (top < 0) return null;

        Object element = stack[top];
        stack[top--] = null;

        return element;
    }

    @Override
    public Object top() {
        return (top < 0) ? null : stack[top];
    }

    @Override
    public String toString() {
        if (top < 0) return "[]";

        StringBuilder out = new StringBuilder("[");
        for (int index = 0; index <= top; index++) {
            out.append(stack[index]).append(index < top ? ", " : "]");
        }

        return out.toString();
    }

    public static void main(String[] args) {
        StackImpl object = new StackImpl();
        System.out.println(object);

        object.push("A");
        object.push("B");
        object.push("C");
        System.out.println(object);
        System.out.println(object.pop());
        System.out.println(object.pop());
        System.out.println(object.pop());
        System.out.println(object.pop());


        object.push("D");
        System.out.println(object);

        System.out.println(object.pop());
        System.out.println(object);
        System.out.println(object.top());


        Iterator<Object> iterator = object.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + (iterator.hasNext() ? " " : ""));
        }
    }

}
