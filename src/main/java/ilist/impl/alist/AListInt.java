package ilist.impl.alist;

import ilist.IList;

import java.util.Arrays;

public class AListInt implements IList {

    private int[] intArray;
    private static final int INITIAL_CAPACITY = 10;
    private int capacity = INITIAL_CAPACITY;
    private int size;

    public AListInt() {
        size = 0;
        intArray = new int[capacity];
    }

    public AListInt(int capacity) {
        this.capacity = capacity;
        intArray = new int[capacity];
    }

    public AListInt(int[] ints) {
        capacity = ints.length;
        intArray = new int[capacity];
        for (int i = 0; i < ints.length; i++) {
            intArray[i] = ints[i];
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < intArray.length; i++) {
            intArray[i] = 0;
        }
        size = 0;
        intArray = new int[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int get(int index) {
        return intArray[index];
    }

    @Override
    public boolean add(int value) {
        if (size < intArray.length) {
            intArray[size] = value;
            size++;
            return true;
        }
        extendArray();
        intArray[size] = value;
        size++;
        return true;
    }

    @Override
    public boolean add(int index, int value) {
        if (index < 0 || index > intArray.length) {
            throw new IllegalArgumentException();
        }
        if (intArray[index] == 0) {
            intArray[index] = value;
            size++;
            return true;
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
    public int remove(int number) {
        int returnNumber = 0;
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (intArray[i] == number) {
                returnNumber = number;
                intArray[i] = 0;
                continue;
            }
            intArray[count] = intArray[i];
            count++;
        }

        size--;
        return returnNumber;
    }

    @Override
    public int removeByIndex(int index) {
        int removeElement = intArray[index];
        intArray[index] = 0;
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                continue;
            }
            intArray[count] = intArray[i];
            count++;
        }
        size--;
        return removeElement;
    }

    @Override
    public boolean contains(int value) {
        for (int i : intArray) {
            if (i == value) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean set(int index, int value) {
        intArray[index] = value;
        return true;
    }

    @Override
    public void print() {
        String output = "";
        for (int i = 0; i < size; i++) {
            output += intArray[i];
            if (i < size - 1) {
                output += ", ";
            }
        }
        System.out.println('[' + output + ']');
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
    public boolean removeAll(int[] arr) {
        for (int i = 0; i < intArray.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (intArray[i] == arr[j]) {
                    intArray[i] = 0;
                    if (j == arr.length - 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private int[] extendArray() {
        int[] temp = intArray;
        this.capacity += 1;
        intArray = new int[capacity];
        for (int i = 0; i < capacity - 1; i++) {
            intArray[i] = temp[i];
        }
        return intArray;
    }

}
