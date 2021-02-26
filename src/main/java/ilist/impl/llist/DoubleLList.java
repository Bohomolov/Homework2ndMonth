package ilist.impl.llist;


import ilist.interfaces.IList;

public class DoubleLList implements IList {
    private Node root;
    private Node crown;
    private int size;
    private final int startIndex = 0;


    public DoubleLList() {
        crown = null;
        root = null;
        size = 0;
    }

    public DoubleLList(int[] ints) {
        addAll(ints);
    }

    @Override
    public void clear() {
        root = null;
        crown = null;
        size = 0;
    }

    @Override
    public void init(int[] array) {
        for (int i : array
        ) {
            add(i);
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int get(int index) {
        indexValidation(index);
        Node returnNode = getNodeByIndex(index, whereIsStart(index) ? crown : root, whereIsStart(index) ? size - 1 : startIndex);
        returnNodeValidationByIndex(returnNode, index);
        return returnNode.value;
    }

    @Override
    public int getValue(int value) {
        Node returnNode = getNodeByValue(root, crown, value);
        returnNodeValidationByValue(returnNode, value);
        return returnNode.value;
    }

    @Override
    public boolean add(int value) {
        if (root == null) {
            root = new Node(null, null, value);
            crown = root;
        } else {
            crown.next = new Node(null, crown, value);
            crown = crown.next;
        }
        size++;
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
        } else if (index == 0) {
            root.prev = new Node(root, null, value);
            root = root.prev;
        } else if (index == size) {
            crown.next = new Node(null, crown, value);
            crown = crown.next;
        } else {
            addByIndex(whereIsStart(index) ? crown.prev : root.next, index, whereIsStart(index) ? size - 2 : startIndex + 1, value);
        }
        size++;
        return true;
    }

    @Override
    public int remove(int number) {
        if (root == null) {
            throw new IllegalArgumentException("Root is null");
        }
        if (root.value == number) {
            deleteRoot();
        } else if (crown.value == number) {
            crown = crown.prev;
            crown.next = null;
        } else {
            Node removeNode = getNodeByValue(root, crown, number);
            returnNodeValidationByValue(removeNode, number);
            changeLinks(removeNode);
        }
        size--;
        return number;
    }

    @Override
    public int removeByIndex(int index) {
        if (index < 0 || index > size - 1) {
            throw new IllegalArgumentException("Incorrect index " + index);
        }
        int output;
        if (index == 0) {
            output = root.value;
            deleteRoot();
        } else if (index == size - 1) {
            output = crown.value;
            crown = crown.prev;
            crown.next = null;
        } else {
            Node removeNode = getNodeByIndex(index, whereIsStart(index) ? crown.prev : root.next, whereIsStart(index) ? size - 2 : startIndex + 1);
            returnNodeValidationByIndex(removeNode, index);
            output = removeNode.value;
            changeLinks(removeNode);
        }
        size--;
        return output;
    }


    @Override
    public boolean contains(int value) {
        return getNodeByValue(root, crown, value) != null;
    }

    @Override
    public boolean set(int index, int value) {
        Node node = getNodeByIndex(index, whereIsStart(index) ? crown : root, whereIsStart(index) ? size - 1 : startIndex);
        if (node == null) {
            return false;
        }
        node.value = value;
        return true;
    }

    @Override
    public void print() {
        System.out.println(toString());
    }

    @Override
    public int[] toArray() {
        int[] output = new int[size];
        toArray(startIndex, size - 1, output, root, crown);
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

    private Node getNodeByIndex(int index, Node node, int startIndex) {
        if (node == null) {
            return null;
        }
        if (startIndex == index) {
            return node;
        }
        return getNodeByIndex(index, whereIsStart(index) ? node.prev : node.next, whereIsStart(index) ? startIndex - 1 : startIndex + 1);
    }

    private void addAll(int[] ints) {
        for (int i : ints) {
            add(i);
        }
    }

    private Node getNodeByValue(Node nodeStart, Node nodeEnd, int value) {
        if (nodeStart == null || nodeEnd == null) {
            return null;
        }
        if (nodeStart.value == value) {
            return nodeStart;
        }
        if (nodeEnd.value == value) {
            return nodeEnd;
        }
        return getNodeByValue(nodeStart.next, nodeEnd.prev, value);
    }

    private void addByIndex(Node node, int index, int startIndex, int value) {
        if (index == startIndex) {
            Node newNode = new Node(node, node.prev, value);
            node.prev.next = newNode;
            node.prev = newNode;
            return;
        }
        addByIndex(whereIsStart(index) ? node.prev : node.next, index, whereIsStart(index) ? startIndex - 1 : startIndex + 1, value);
    }

    private void toArray(int index, int endIndex, int[] out, Node nodeStart, Node nodeEnd) {
        if (whereIsStart(index) || nodeEnd == null || nodeStart == null) {
            return;
        }
        out[index] = nodeStart.value;
        out[endIndex] = nodeEnd.value;
        toArray(index + 1, endIndex - 1, out, nodeStart.next, nodeEnd.prev);
    }

    private void indexValidation(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("Incorrect index size = " + size);
        }
    }

    private boolean whereIsStart(int index) {
        return index > size / 2 + size % 2;
    }

    private void returnNodeValidationByValue(Node returnNode, int value) {
        if (returnNode == null) {
            throw new IllegalArgumentException(value + " is not found");
        }
    }

    private void returnNodeValidationByIndex(Node returnNode, int index) {
        if (returnNode == null) {
            throw new IllegalArgumentException(index + " index is not found");
        }
    }

    private void deleteRoot() {
        if (size == 1) {
            root = null;
            crown = null;
        } else {
            root = root.next;
            root.prev = null;
        }
    }

    private void changeLinks(Node node) {
        Node prevNode = node.prev;
        Node nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }
}
