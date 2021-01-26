package ilist.impl.alist.generics;

import ilist.constantslist.ListConstants;
import ilist.interfaces.IListGenerics;

public class AListGenerics<E> implements IListGenerics<E> {
    private static final int INITIAL_CAPACITY = 10;
    private Object[] objects;
    private int capacity = INITIAL_CAPACITY;
    private int size;

    public AListGenerics() {
        size = 0;
        objects = new Object[capacity];
    }

    public AListGenerics(int capacity) {
        size = 0;
        this.capacity = capacity;
        objects = new Object[capacity];
    }

    public AListGenerics(E[] objects) {
        size = 0;
        capacity = objects.length;
        this.objects = new Object[capacity];
        for (int i = 0; i < objects.length; i++) {
            this.objects[i] = objects[i];
        }
    }

    @Override
    public void clear() {
        size = 0;
        objects = new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException(ListConstants.INCORRECT_ARGUMENT + size);
        }
        return (E) objects[index];
    }

    @Override
    public boolean add(E value) {
        if (value == null) {
            throw new IllegalArgumentException(ListConstants.INCORRECT_ARGUMENT);
        }
        if (size < objects.length) {
            objects[size] = value;
            size++;
            return true;
        }
        extendArray();
        objects[size] = value;
        size++;
        return true;
    }

    @Override
    public boolean add(int index, E value) {
        if (index < 0 || index > size || value == null) {
            throw new IllegalArgumentException(ListConstants.INCORRECT_ARGUMENT + size);
        }
        if (size == objects.length) {
            extendArray();
        }
        for (int i = size; i > index; i--) {
            objects[i] = objects[i - 1];
        }
        objects[index] = value;
        size++;
        return true;
    }

    @Override
    public E remove(E value) {
        if (value == null) {
            throw new IllegalArgumentException(ListConstants.INCORRECT_ARGUMENT);
        }
        E returnObject = null;
        int count = 0;
        boolean flag = true;
        for (int i = 0; i < size; i++) {
            if (flag && objects[i].equals(value)) {
                returnObject = value;
                flag = false;
                continue;
            }
            objects[count] = objects[i];
            count++;
        }
        size--;
        if (flag) {
            throw new IllegalArgumentException(ListConstants.INCORRECT_ARGUMENT);
        }
        return (E) returnObject;
    }

    @Override
    public E removeByIndex(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException(ListConstants.INCORRECT_ARGUMENT + size);
        }
        E output = null;
        int count = 0;
        boolean flag = true;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                output = (E) objects[index];
                flag = false;
                continue;
            }
            objects[count] = objects[i];
            count++;
        }
        if (flag) {
            throw new IllegalArgumentException(ListConstants.INCORRECT_ARGUMENT);
        }
        size--;
        return output;
    }

    @Override
    public boolean contains(E value) {
        for (int i = 0; i < size; i++) {
            if (objects[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean set(int index, E value) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException(ListConstants.INCORRECT_ARGUMENT + size);
        }
        objects[index] = value;
        return true;
    }

    @Override
    public void print() {
        String output = "";
        for (int i = 0; i < size; i++) {
            output += objects[i];
            if (i < size - 1) {
                output += ", ";
            }
        }
        System.out.println('[' + output + ']');
    }

    @Override
    public E[] toArray() {
        Object[] output = new Object[size];
        for (int i = 0; i < size; i++) {
            output[i] = objects[i];
        }
        return (E[]) output;
    }

    @Override
    public boolean removeAll(E[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException(ListConstants.ARRAY_IS_EMPTY);
        }
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                throw new IllegalArgumentException(ListConstants.INCORRECT_ARGUMENT);
            }
            boolean flag = true;
            int count = 0;
            for (int j = 0; j < size; j++) {
                if (objects[j].equals(arr[i])) {
                    flag = false;
                    continue;
                }
                objects[count] = objects[j];
                count++;
            }

            size--;

        }

        return true;
    }

    private Object[] extendArray() {
        Object[] temp = objects;
        this.capacity += 1;
        objects = new Object[capacity];
        for (int i = 0; i < capacity - 1; i++) {
            objects[i] = temp[i];
        }
        return objects;
    }
}
