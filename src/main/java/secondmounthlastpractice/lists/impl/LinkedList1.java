package secondmounthlastpractice.lists.impl;


import secondmounthlastpractice.lists.IList;

public class LinkedList1 implements IList {
    Node root = null;

    public LinkedList1() {
    }

    static class Node {
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    @Override
    public void clear() {
        if (root == null) {
            return;
        }
        clear(root.next);
        root = null;
    }

    private void clear(Node element) {
        if (element == null) {
            return;
        }
        clear(element.next);
        element.next = null;
    }

    @Override
    public int size() {
        return size(root, 0);
    }

    private int size(Node element, int size) {
        if (element == null) {
            return size;
        }
        return size(element.next, size + 1);
    }

    @Override
    public boolean add(int value) {
        if (root == null) {
            root = new Node(value);
            return true;
        } else {
            add(value, root);
        }
        return true;
    }

    private void add(int value, Node element) {
        if (element.next == null) {
            element.next = new Node(value);
            return;
        }
        add(value, element.next);
    }

    @Override
    public int[] toArray() {
        if (root == null) {
            return new int[]{};
        }
        int[] array = new int[size()];
        return toArray(root, 0, array);
    }

    private int[] toArray(Node element, int currentIndex, int[] array) {
        if (element.next == null) {
            array[currentIndex] = element.value;
            return array;
        }
        array[currentIndex] = element.value;
        return toArray(element.next, currentIndex + 1, array);
    }

    @Override
    public void sort() {
        if (root == null) {
            return;
        }
        root = sortInternal(root);
    }

    private Node sortInternal(Node le) {
        if (le.next == null)
            return le;
        Node min = le;
        Node beforeMin = null;
        Node ptr;
        for (ptr = le; ptr.next != null; ptr = ptr.next) {
            if (ptr.next.value < min.value) {
                min = ptr.next;
                beforeMin = ptr;
            }
        }
        if (min != le)
            le = swapNodes(le, le, min, beforeMin);
        le.next = sortInternal(le.next);
        return le;
    }

    private Node swapNodes(Node headRef, Node currNode1, Node currNode2, Node prevNode2) {
        headRef = currNode2;
        prevNode2.next = currNode1;
        Node temp = currNode2.next;
        currNode2.next = currNode1.next;
        currNode1.next = temp;
        return headRef;
    }

    @Override
    public void reverse() {
        root = reverseInternal(root);
    }

    private Node reverseInternal(Node le) {
        if (le == null) {
            return le;
        }
        if (le.next == null) {
            return le;
        }
        Node newHeadNode = reverseInternal(le.next);
        le.next.next = le;
        le.next = null;
        return newHeadNode;
    }
}
