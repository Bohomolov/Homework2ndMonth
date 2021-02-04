package ilist.impl.llist;

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
        return get(index, root, startIndex);
    }

    @Override
    public int getValue(int value) {
        return getValue(root, value);
    }

    @Override
    public boolean add(int value) {
        if (root == null) {
            root = new Node(null, null, value);
            crown = root;
            size++;
            return true;
        }
        add(value, root);
        return true;
    }

    @Override
    public boolean add(int index, int value) {
        if (index < 0 || index > size) {
            return false;
        }
        if (root == null) {
            root = new Node(null, null, value);
            crown = root;
            size++;
            return true;
        }
        size++;
        if (index == 0) {
            root.prev = new Node(root, null, value);
            root = root.prev;
            return true;
        }
        if (index == size - 1) {
            crown.next = new Node(null, crown, value);
            crown = crown.next;
            return true;
        }
        addByIndex(root, index, startIndex, value);
        return true;
    }

    @Override
    public int remove(int number) {
        if (root == null) {
            throw new IllegalArgumentException("Root is null");
        }
        if (root.value == number) {
            root = root.next;
            root.prev = null;
            size--;
            return number;
        }
        if (crown.value == number) {
            crown = crown.prev;
            crown.next = null;
            size--;
            return number;
        }

        return remove(root, number);
    }

    @Override
    public int removeByIndex(int index) {
        if (index < 0 || index > size - 1) {
            throw new IllegalArgumentException("Incorrect index " + index);
        }
        if (root == null) {
            throw new IllegalArgumentException("Root is empty");
        }
        if (index == 0) {
            int output = root.value;
            root = root.next;
            root.prev = null;
            size--;
            return output;
        }
        if (index == size - 1) {
            int output = crown.value;
            crown = crown.prev;
            crown.next = null;
            size--;
            return output;
        }
        return removeByIndex(root, index, startIndex);
    }

    @Override
    public boolean contains(int value) {
        return contains(root, value);
    }

    @Override
    public boolean set(int index, int value) {
        return set(root, index, startIndex, value);
    }

    private boolean set(Node node, int index, int startIndex, int value) {
        if (node == null) {
            return false;
        }
        if (index == startIndex) {
            node.value = value;
            return true;
        }
        return set(node.next, index, startIndex + 1, value);
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
        for (int i : ar) {
            remove(i);
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
        if (node == null) {
            throw new IllegalArgumentException("Index is absent " + index);
        }
        if (startIndex == index) {
            return node.value;
        }
        return get(index, node.next, startIndex + 1);
    }

    private void addAll(int[] ints) {
        for (int i : ints) {
            add(i);
        }
    }

    private int getValue(Node node, int value) {
        if (node == null) {
            throw new IllegalArgumentException("Value is absent " + value);
        }
        if (node.value == value) {
            return node.value;
        }
        return getValue(node.next, value);
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

    private void addByIndex(Node node, int index, int startIndex, int value) {
        if (index == startIndex) {
            Node newNode = new Node(node, node.prev, value);
            node.prev.next = newNode;
            node.prev = newNode;
            size++;
            return;
        }
        addByIndex(node.next, index, startIndex + 1, value);
    }

    private int remove(Node node, int value) {
        if (node == null) {
            throw new IllegalArgumentException("Value is absent " + value);
        }
        if (node.value == value) {
            Node prevNode = node.prev;
            Node nextNode = node.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
            size--;
            return value;
        }
        return remove(node.next, value);
    }

    private int removeByIndex(Node node, int index, int startIndex) {
        if (index == startIndex) {
            int output = node.value;
            Node prevNode = node.prev;
            Node nextNode = node.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
            size--;
            return output;
        }
        return removeByIndex(node.next, index, startIndex + 1);
    }

    private boolean contains(Node node, int value) {
        if (node == null) {
            return false;
        }
        if (node.value == value) {
            return true;
        }
        return contains(node.next, value);
    }

    private void toArray(int index, int[] out, Node node) {
        if (node.next == null) {
            out[index] = node.value;
            return;
        }
        out[index] = node.value;
        toArray(index + 1, out, node.next);
    }
}
