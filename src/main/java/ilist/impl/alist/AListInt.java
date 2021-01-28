package ilist.impl.alist;

import ilist.interfaces.IList;
import ilist.constantslist.ListConstants;


public class AListInt implements IList {
    private static final int INITIAL_CAPACITY = 10;
    private int[] intArray;
    private int capacity = INITIAL_CAPACITY;
    private final double COEFICIENT = 1.5;
    private int size;

    public AListInt() {
        size = 0;
        intArray = new int[capacity];
    }

    public AListInt(int capacity) {
        size = 0;
        this.capacity = capacity;
        intArray = new int[capacity];
    }

    public AListInt(int[] ints) {
        size = 0;
        capacity = ints.length;
        intArray = new int[capacity];
        for (int i = 0; i < ints.length; i++) {
            intArray[i] = ints[i];
        }
    }

    @Override
    public void clear() {
        size = 0;
        intArray = new int[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int get(int index) {
        isCorrectIndex(index);
        return intArray[index];
    }

    @Override
    public boolean add(int value) {
        extendArray();
        intArray[size] = value;
        size++;
        return true;
    }

    @Override
    public boolean add(int index, int value) {
        if (index < 0 || index > size) {
            return false;
        }
        extendArray();
        for (int i = size; i > index; i--) {
            intArray[i] = intArray[i - 1];
        }
        intArray[index] = value;
        size++;
        return true;
    }

    @Override
    public int remove(int value) {
        return removeByIndex(findIndex(value));
    }

    @Override
    public int removeByIndex(int index) {
        isCorrectIndex(index);
        size--;
        for (int i = index; i < size; i++) {
            intArray[i] = intArray[i + 1];
        }
        return intArray[index];
    }

    @Override
    public boolean contains(int value) {
        return findIndex(value) >= 0;
    }

    @Override
    public boolean set(int index, int value) {
        if (index < 0 || index > size) {
            return false;
        }
        intArray[index] = value;
        return true;
    }

    @Override
    public void print() {
        System.out.println(toString());
    }

    @Override
    public boolean removeAll(int[] arr) {
        if (arr == null) {
            return false;
        }

        for (int i = 0; i < arr.length; i++) {
            int index = findIndex(arr[i]);
            if (index < 0) {
                continue;
            }
            removeByIndex(index);
        }
        return true;
    }

    @Override
    public int[] toArray() {
        int[] output = new int[size];
        for (int i = 0; i < size; i++) {
            output[i] = intArray[i];
        }
        return output;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder("[");
        for (int i = 0; i < size - 1; i++) {
            output.append(intArray[i]).append(", ");
        }
        output.append(intArray[size - 1]);
        output.append(']');
        return output.toString();
    }

    private void extendArray() {
        if (size == intArray.length - 1) {
            int[] temp = intArray;
            capacity *= COEFICIENT;
            intArray = new int[capacity];
            for (int i = 0; i < size; i++) {
                intArray[i] = temp[i];
            }
        }
    }

    private int findIndex(int value) {
        for (int i = 0; i < size; i++) {
            if (intArray[i] == value) {
                return i;
            }
        }
        return -1;
    }

    private void isCorrectIndex(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException(ListConstants.INCORRECT_ARGUMENT + size);
        }
    }
}
