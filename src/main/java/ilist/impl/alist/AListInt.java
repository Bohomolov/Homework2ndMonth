package ilist.impl.alist;

import ilist.IList;
import ilist.constantslist.ListConstants;


public class AListInt implements IList {
    private static final int INITIAL_CAPACITY = 10;
    private int[] intArray;
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
        size = 0;
        intArray = new int[capacity];
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
        if (index < 0 || index > size) {
            throw new IllegalArgumentException(ListConstants.INCORRECT_ARGUMENT + size);
        }
        if (size == intArray.length) {
            extendArray();
        }
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
        boolean flag = true;
        for (int i = 0; i < size; i++) {
            if (flag && intArray[i] == number) {
                returnNumber = number;
                flag = false;
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
        if (index < 0 || index > size) {
            throw new IllegalArgumentException(ListConstants.INCORRECT_ARGUMENT + size);
        }
        int output = 0;
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                output = intArray[index];
                continue;
            }
            intArray[count] = intArray[i];
            count++;
        }
        size--;
        return output;
    }

    @Override
    public boolean contains(int value) {
        for (int i = 0; i < size; i++) {
            if (intArray[i] == value) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean set(int index, int value) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException(ListConstants.INCORRECT_ARGUMENT + size);
        }
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
        if (arr == null) {
            throw new IllegalArgumentException(ListConstants.ARRAY_IS_EMPTY);
        }
        for (int i = 0; i < arr.length; i++) {
            int count = 0;
            for (int j = 0; j < size; j++) {
                if (intArray[j] == arr[i]) {
                    continue;
                }
                intArray[count] = intArray[j];
                count++;
            }
            size--;
        }
        return true;
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
