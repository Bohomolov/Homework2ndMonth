package ilist.impl.alist;

import ilist.IList;

import java.util.Arrays;

public class AListInt implements IList {

    private int[] intArray;
    private int capacity;

    public AListInt() {
        capacity = 10;
        intArray = new int[capacity];
    }

    public AListInt(int capacity) {
        this.capacity = capacity;
        intArray = new int[capacity];
    }

    public AListInt(int[] ints) {
        this.capacity = ints.length;
        this.intArray = ints;
    }

    @Override
    public void clear() {
        Arrays.fill(intArray, 0);
        intArray = new int[capacity];
    }

    @Override
    public int size() {
        return intArray.length;
    }

    @Override
    public int get(int index) {
        return intArray[index];
    }

    @Override
    public boolean add(int value) {
        int flag = 0;
        for (int i = 0; i < intArray.length; i++) {
            if (intArray[i] == 0) {
                intArray[i] = value;
                flag++;
                return true;
            }
        }
        if (flag == 0) {
            extendArray();
            intArray[intArray.length - 1] = value;
            return true;
        }
        return false;
    }

    @Override
    public boolean add(int index, int value) {
        if (index < 0 || index > intArray.length) {
            throw new IllegalArgumentException();
        }
        if (intArray[index] == 0) {
            intArray[index] = value;
            return true;
        }
        extendArray();
        for (int i = index; i < intArray.length; i++) {

        }
        return false;
    }

    @Override
    public int remove(int number) {
        int removeElement = 0;
        for (int i = 0; i < intArray.length; i++) {
            if (intArray[i] == number) {
                removeElement = intArray[i];
                intArray[i] = 0;
                return removeElement;
            }
        }
        return removeElement;
    }

    @Override
    public int removeByIndex(int index) {
        int removeElement = intArray[index];
        intArray[index] = 0;
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
        for (int i : intArray) {
            System.out.print("[" + i + "]");
        }
    }

    @Override
    public int[] toArray() {
        return intArray;
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
        System.arraycopy(temp, 0, intArray, 0, temp.length);
        return intArray;
    }

}
