package secondmounthlastpractice.lists.impl;


import secondmounthlastpractice.lists.IList;

public class LinkedList2 implements IList {
    private Node root = null;
    private Node tail = null;
    private int size = 0;

    static class Node {
        int value;
        Node next;
        Node prev;

        public Node(Node prev, int value, Node next) {
            this.value = value;
            this.next = next;
            this.prev = prev;
        }
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(int value) {
        Node newElement = new Node(null, value, null);
        size++;
        if (root == null) {
            root = newElement;
        } else {
            newElement.prev = tail;
            tail.next = newElement;
        }
        tail = newElement;
        return true;
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
        if (currentIndex == size - 1) {
            array[currentIndex] = element.value;
            return array;
        }
        array[currentIndex] = element.value;
        return toArray(element.next, currentIndex + 1, array);
    }

    @Override
    public void sort() {
        Node current, index;
        int temp;
        if (root != null) {
            for (current = root; current.next != null; current = current.next) {
                for (index = current.next; index != null; index = index.next) {
                    if (current.value > index.value) {
                        temp = current.value;
                        current.value = index.value;
                        index.value = temp;
                    }
                }
            }
        }
    }

    @Override
    public void reverse() {
        Node temp = null;
        Node current = root;

        while (current != null) {
            temp = current.prev;
            current.prev = current.next;
            current.next = temp;
            current = current.prev;
        }
        if (temp != null) {
            root = temp.prev;
        }
    }
}
