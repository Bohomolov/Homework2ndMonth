package ilist.impl.llist;

import ilist.constantslist.ListConstants;
import ilist.interfaces.IList;

public class LList implements IList {
    private int size = 0;
    private Node root = null;

    public LList() {

    }

    public LList(int[] ints) {
        addAll(ints);
    }

    @Override
    public void clear() {
        size = 0;
        for (Node node = root; node != null; ) {
            Node next = node.next;
            next.value = 0;
            node.next = null;
            node = next;
        }
        root = null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int get(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException(ListConstants.INCORRECT_ARGUMENT + size);
        }
        Node node = root;
        int innerIndex = 0;
        while (node != null) {
            if (index == innerIndex) {
                return node.value;
            }
            innerIndex++;
            node = node.next;
        }
        return 0;
    }

    @Override
    public boolean add(int value) {
        if (root == null) {
            root = new Node(value);
            size++;
            return true;
        }
        Node node = root;
        while (node != null) {
            Node next = node.next;
            if (next == null) {
                node.next = new Node(value);
                size++;
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public boolean add(int index, int value) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException(ListConstants.INCORRECT_ARGUMENT + size);
        }
        return false;
    }

    @Override
    public int remove(int number) {
        return 0;
    }

    @Override
    public int removeByIndex(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException(ListConstants.INCORRECT_ARGUMENT + size);
        }
        Node node = root;
        int innerIndex = 0;
        while (node != null) {
            if (index == innerIndex) {
                node.next = node.next.next;
                return node.value;
            }
            innerIndex++;
            node = node.next;
        }
        return 0;
    }

    @Override
    public boolean contains(int value) {
        Node node = root;
        while (node != null) {
            if (node.value == value) {
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public boolean set(int index, int value) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException(ListConstants.INCORRECT_ARGUMENT + size);
        }
        Node node = root;
        int innerIndex = 0;
        while (node != null) {
            if (index == innerIndex) {
                node.value = value;
                return true;
            }
            innerIndex++;
            node = node.next;
        }
        return false;
    }

    @Override
    public void print() {
        String result = "[";
        int i = 0;
        for (Node node = root; node != null; i++) {
            result += node.value;
            if (i < size - 1) {
                result += ", ";
            }
            node = node.next;
        }
        result += "]";
        System.out.println(result);
    }

    @Override
    public int[] toArray() {
        int[] output = new int[size];
        Node node = root;
        for (int i = 0; i < output.length; i++) {
            output[i] = node.value;
            node = node.next;
        }
        return output;
    }

    @Override
    public boolean removeAll(int[] ar) {
        return false;
    }

    private void addAll(int[] ints) {
        for (int i : ints) {
            add(i);
        }
    }

    private static class Node {
        private int value;
        private Node next;

        Node(int value) {
            next = null;
            this.value = value;
        }
    }
}
