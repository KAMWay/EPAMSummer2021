package practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListImpl implements List {

    public static class Node {
        Object element;
        Node next;

        Node(Object element, Node next) {
            this.element = element;
            this.next = next;
        }
    }

    private Node head;
    private int size = 0;

    @Override
    public void clear() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {

        Node currHead = head;

        @Override
        public boolean hasNext() {
            return currHead != null;
        }

        @Override
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            Node currNode = currHead;
            currHead = currNode.next;

            return currNode.element;
        }

    }

    @Override
    public void addFirst(Object element) {
        if (element != null) {
            head = new Node(element, this.head);
            size++;
        }
    }

    @Override
    public void addLast(Object element) {
        if (head == null) {
            addFirst(element);
        } else {
            Node currNode = this.head;
            while (currNode.next != null) {
                currNode = currNode.next;
            }
            currNode.next = new Node(element, null);
            size++;
        }
    }

    @Override
    public void removeFirst() {
        if (head != null) {
            head = head.next;
            size--;
        }
    }

    @Override
    public void removeLast() {
        if (head != null) {
            Node currNode = head;
            Node nextNode = currNode.next;
            while (nextNode.next != null) {
                currNode = currNode.next;
                nextNode = currNode.next;
            }
            currNode.next = null;
            size--;
        }
    }

    @Override
    public Object getFirst() {
        if (head == null) return null;
        else return head.element;
    }

    @Override
    public Object getLast() {
        if (head == null) return null;

        Node currNode = this.head;
        while (currNode.next != null) {
            currNode = currNode.next;
        }

        return currNode.element;
    }

    @Override
    public Object search(Object element) {
        if (head == null || element == null) return null;

        Node currNode = this.head;
        while (currNode.next != null) {
            if (currNode.element.equals(element)) return element;
            currNode = currNode.next;
        }

        return null;
    }

    @Override
    public boolean remove(Object element) {
        if (head == null) return false;
        if (element == null) return true;

        Node currNode = head;
        if (currNode.element.equals(element)) {
            head = head.next;
            size--;
            return true;
        }

        Node nextNode = currNode.next;
        while (nextNode.next != null) {
            if (nextNode.element.equals(element)) {
                currNode.next = nextNode.next;
                size--;
                return true;
            }
            currNode = currNode.next;
            nextNode = currNode.next;
        }

        return false;
    }

    @Override
    public String toString() {
        if (this.head == null) return "[]";

        StringBuilder out = new StringBuilder("[");
        Iterator<Object> iterator = iterator();
        while (iterator.hasNext()) {
            out.append(iterator.next()).append(iterator.hasNext() ? ", " : "]");
        }

        return out.toString();
    }

    public static void main(String[] args) {
        ListImpl object = new ListImpl();
        System.out.println(object);
        object.removeFirst();
        object.removeLast();

        System.out.println(object.remove(null));

        object.addLast("B");
        object.addFirst("A");
        object.addLast("C");
        System.out.println(object);

        System.out.println("---");
        System.out.println(object.size());

        object.addLast("D");
        System.out.println(object);

        System.out.println(object.getFirst());
        System.out.println(object.getLast());

        System.out.println(object.search("F"));

        System.out.println(object.remove(null));

        System.out.println(object);
        Iterator<Object> iterator = object.iterator();
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + (iterator.hasNext() ? " " : ""));
        }

        object.clear();
        System.out.print("\n" + object);
    }
}
