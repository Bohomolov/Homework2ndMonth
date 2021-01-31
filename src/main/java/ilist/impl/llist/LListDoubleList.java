package ilist.impl.llist;

import ilist.constantslist.ListConstants;
import ilist.interfaces.IList;

public class LListDoubleList implements IList {
    private Node root;
    private Node crown;
    private int size;
    private final int startIndex = 0;


    public LListDoubleList() {
        crown = null;
        root = null;
        size = 0;
    }

    public LListDoubleList(int[] ints) {
        addAll(ints);
    }

    public int getCrownValue() {
        return crown.value;
    }

    public int getRootValue() {
        return root.value;
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
    public int get(int index) {
        isCorrectIndex(index);
        return get(index, root, startIndex);
    }

    @Override
    public int getValue(int value) {
        return 0;
    }

    @Override
    public boolean add(int value) {
        if (root == null) {
            root = new Node(null, null, value);
            size++;
            return true;
        }
        add(value, root);
        return false;
    }

    @Override
    public boolean add(int index, int value) {
        if (index < 0 || index > size) {
            return false;
        }
        if (root == null) {
            root = new Node(null, null, value);
            size++;
            return true;
        }
        if (index == 0) {
            root.prev = new Node(root, null, value);
            root = root.prev;
            size++;
            return true;
        }
        return addByIndex(root, index, startIndex, value);
    }

    private boolean addByIndex(Node node, int index, int startIndex, int value) {
        if (index == startIndex) {
            Node newNode = new Node(node, node.prev, value);
            size++;
            return true;
        }
        return addByIndex(node.next, index, startIndex + 1, value);
    }

    @Override
    public int remove(int number) {
        return 0;
    }

    @Override
    public int removeByIndex(int index) {
        return 0;
    }

    @Override
    public boolean contains(int value) {
        return false;
    }

    @Override
    public boolean set(int index, int value) {
        return false;
    }

    @Override
    public void print() {
        System.out.println(toString());
    }

    @Override
    public int[] toArray() {
        int[] output = new int[size];
        toArray(startIndex, output, root);
        return output;
    }

    @Override
    public boolean removeAll(int[] ar) {
        return false;
    }

    @Override
    public String toString() {
        int[] temp = toArray();
        StringBuilder output = new StringBuilder("[");
        if (temp.length != 0) {
            for (int i = 0; i < size - 1; i++) {
                output.append(temp[i]).append(", ");
            }
            output.append(temp[size - 1]);
        }
        output.append(']');
        return output.toString();
    }

    private static class Node {
        Node next;
        Node prev;
        int value;

        public Node(Node next, Node prev, int value) {
            this.next = next;
            this.prev = prev;
            this.value = value;
        }

    }

    private int get(int index, Node node, int startIndex) {
        if (startIndex == index) {
            return node.value;
        }
        return get(index, node.next, startIndex + 1);
    }

    private void toArray(int index, int[] out, Node node) {
        if (node == null) {
            return;
        }
        if (node.next == null) {
            out[index] = node.value;
            return;
        }
        out[index] = node.value;
        toArray(index + 1, out, node.next);
    }

    private void addAll(int[] ints) {
        for (int i : ints) {
            add(i);
        }
    }

    private void add(int value, Node node) {
        if (node.next == null) {
            node.next = new Node(null, node, value);
            crown = node.next;
            size++;
            return;
        }
        add(value, node.next);
    }

    private void isCorrectIndex(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException(ListConstants.INCORRECT_ARGUMENT + size);
        }
    }
}
