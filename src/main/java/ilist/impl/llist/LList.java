package ilist.impl.llist;

import ilist.constantslist.ListConstants;
import ilist.interfaces.IList;

public class LList implements IList {
    private int size = 0;
    private Node root = null;
    private final int startIndex = 0;

    public LList() {

    }

    public LList(int[] ints) {
        addAll(ints);
    }

    @Override
    public void clear() {
        size = 0;
        root = null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int getValue(int value) {
        isRootEmpty();
        return getValue(value, root);
    }

    @Override
    public int get(int index) {
        isCorrectIndex(index);
        return getNodeByIndex(index, startIndex, root).value;
    }

    @Override
    public boolean add(int value) {
        if (root == null) {
            root = new Node(value);
            size++;
        } else {
            add(value, root);
        }
        return true;
    }
    @Override
    public void init(int[] array) {
        for (int i:array
        ) {
            add(i);
        }
    }

    @Override
    public boolean add(int index, int value) {
        if (index < 0 || index > size) {
            return false;
        }
        if (root == null) {
            root = new Node(value);
            size++;
            return true;
        }
        if (index == 0) {
            Node node = new Node(value);
            node.next = root;
            root = node;
            size++;
            return true;
        }

        Node prevNode = getNodeByIndex(index - 1, startIndex, root);
        Node node = new Node(value);

        node.next = prevNode.next;
        prevNode.next = node;
        size++;
        return true;
    }

    @Override
    public int remove(int value) {
        isRootEmpty();
        if (root.value == value) {
            root = root.next;
            size--;
            return value;
        }
        int prevIndex = getIndex(value, startIndex, root) - 1;
        if (prevIndex < 0) {
            throw new IllegalArgumentException("value is absent");
        }
        Node prevNode = getNodeByIndex(prevIndex, startIndex, root);
        prevNode.next = prevNode.next.next;
        size--;
        return value;
    }

    @Override
    public int removeByIndex(int index) {
        isCorrectIndex(index);
        isRootEmpty();
        int out;
        if (index == 0) {
            out = root.value;
            root = root.next;
            size--;
            return out;
        }
        out = get(index);
        Node prevNode = getNodeByIndex(index - 1, startIndex, root);
        prevNode.next = prevNode.next.next;
        size--;
        return out;
    }

    @Override
    public boolean contains(int value) {
        if (root == null) {
            return false;
        }
        return contains(value, startIndex, root);
    }

    @Override
    public boolean set(int index, int value) {
        if (index < 0 || index > size) {
            return false;
        }
        return set(index, value, startIndex, root);
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
        for (int i = 0; i < ar.length; i++) {
            remove(ar[i]);
        }
        return true;
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

        private int value;
        private Node next;

        Node(int value) {
            next = null;
            this.value = value;
        }
    }

    private void addAll(int[] ints) {
        for (int i : ints) {
            add(i);
        }
    }

    private void add(int value, Node node) {
        if (node.next == null) {
            node.next = new Node(value);
            size++;
            return;
        }
        add(value, node.next);
    }

    private boolean contains(int value, int startIndex, Node node) {
        if (node.value == value) {
            return true;
        } else if (node.next == null) {
            return false;
        }
        return contains(value, startIndex + 1, node.next);
    }

    private int getValue(int value, Node node) {
        if (node.next == null) {
            return node.value;
        }
        if (node.value == value) {
            return node.value;
        }
        return getValue(value, node.next);
    }

    private Node getNodeByIndex(int index, int startIndex, Node node) {
        if (startIndex == index) {
            return node;
        }
        if (node.next == null) {
            return node;
        }
        return getNodeByIndex(index, startIndex + 1, node.next);
    }

    private int getIndex(int value, int startIndex, Node node) {
        if (node.value == value) {
            return startIndex;
        }
        if (node.next == null) {
            return -2;
        }
        return getIndex(value, startIndex + 1, node.next);
    }


    private boolean set(int index, int value, int startIndex, Node node) {
        if (index == startIndex) {
            node.value = value;
            return true;
        } else if (node.next == null) {
            return false;
        }
        return set(index, value, startIndex + 1, node.next);
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

    private void isCorrectIndex(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException(ListConstants.INCORRECT_ARGUMENT + size);
        }
    }

    private void isRootEmpty() {
        if (root == null) {
            throw new IllegalArgumentException("root is empty");
        }
    }
}
