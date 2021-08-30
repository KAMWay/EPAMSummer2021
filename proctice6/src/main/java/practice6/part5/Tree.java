package practice6.part5;

public class Tree<E extends Comparable<E>> {

    private Node<E> rootNode;

    public boolean add(E element) {
        if (rootNode == null) {
            rootNode = new Node<>(element, null, null);

            return true;
        }

        return add(rootNode, element);
    }

    private boolean add(Node<E> node, E element) {
        if (element.compareTo(node.element) < 0) {
            if (node.leftNode == null) {
                node.leftNode = new Node<>(element, null, null);

                return true;
            }

            return add(node.leftNode, element);
        }

        if (element.compareTo(node.element) > 0) {
            if (node.rightNode == null) {
                node.rightNode = new Node<>(element, null, null);

                return true;
            }

            return add(node.rightNode, element);
        }

        return false;
    }

    public void add(E[] elements) {
        if (elements != null) {
            for (E element : elements) {
                add(element);
            }
        }
    }

    public boolean remove(E element) {
        Node<E> currentNode = rootNode;
        Node<E> parentNode = rootNode;
        boolean isLeftChild = true;

        while (!element.equals(currentNode.element)) {
            parentNode = currentNode;
            if (element.compareTo(currentNode.element) < 0) {
                isLeftChild = true;
                currentNode = currentNode.leftNode;
            } else {
                isLeftChild = false;
                currentNode = currentNode.rightNode;
            }
            if (currentNode == null)

                return false;
        }

        if (currentNode.leftNode == null && currentNode.rightNode == null) {
            setNodeValue(currentNode, parentNode, isLeftChild, null);
        } else if (currentNode.rightNode == null) {
            setNodeValue(currentNode, parentNode, isLeftChild, currentNode.leftNode);
        } else if (currentNode.leftNode == null) {
            setNodeValue(currentNode, parentNode, isLeftChild, currentNode.rightNode);
        } else {
            Node<E> heirNode = getSuccessor(currentNode);
            if (currentNode == rootNode)
                rootNode = heirNode;
            else if (isLeftChild)
                parentNode.leftNode = heirNode;
            else
                parentNode.rightNode = heirNode;
        }

        return true;
    }

    private void setNodeValue(Node<E> currentNode, Node<E> parentNode, boolean isLeftChild, Node<E> value) {
        if (currentNode == rootNode) {
            rootNode = value;
        } else if (isLeftChild) {
            parentNode.leftNode = value;
        } else {
            parentNode.rightNode = value;
        }
    }

    private Node<E> getSuccessor(Node<E> node) {
        Node<E> parentNode = node;
        Node<E> heirNode = node;
        Node<E> currentNode = node.rightNode;

        while (currentNode != null) {
            parentNode = heirNode;
            heirNode = currentNode;
            currentNode = currentNode.leftNode;
        }

        if (heirNode != node.rightNode) {
            parentNode.leftNode = heirNode.rightNode;
            heirNode.rightNode = node.rightNode;
        }

        return heirNode;
    }

    public void print() {
        if (rootNode != null) {
            print(rootNode, "");
        }
    }

    private void print(Node<E> current, String separator) {
        if (current == null) {

            return;
        }

        print(current.leftNode, separator + "  ");
        System.out.println(separator + current.element);
        print(current.rightNode, separator + "  ");
    }

    private static final class Node<E> {

        final E element;

        Node<E> leftNode;

        Node<E> rightNode;

        Node(E element, Node<E> leftNode, Node<E> rightNode) {
            this.element = element;
            this.leftNode = leftNode;
            this.rightNode = rightNode;
        }

    }

}