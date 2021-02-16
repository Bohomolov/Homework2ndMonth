package ilist.impl.alist.generics;

import ilist.constantslist.ListConstants;
import ilist.interfaces.IListGenerics;

public class AListGenerics<E> implements IListGenerics<E> {
    private static final int INITIAL_CAPACITY = 10;
    private Object[] objects;
    private int capacity = INITIAL_CAPACITY;
    private final double COEFICIENT = 1.5;
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
        isCorrectIndex(index);
        return (E) objects[index];
    }

    @Override
    public boolean add(E value) {
        extendArray();
        objects[size] = value;
        size++;
        return true;
    }

    @Override
    public boolean add(int index, E value) {
        if (index < 0 || index > size) {
            return false;
        }
        extendArray();
        for (int i = size; i > index; i--) {
            objects[i] = objects[i - 1];
        }
        objects[index] = value;
        size++;
        return true;
    }

    @Override
    public E remove(E value) {
        return removeByIndex(findIndex(value));
    }

    @Override
    public E removeByIndex(int index) {
        isCorrectIndex(index);
        size--;
        for (int i = index; i < size; i++) {
            objects[i] = objects[i + 1];
        }
        return (E) objects[index];
    }

    @Override
    public boolean contains(E value) {
        return findIndex(value) >= 0;
    }

    @Override
    public boolean set(int index, E value) {
        if (index < 0 || index > size) {
            return false;
        }
        objects[index] = value;
        return true;
    }

    @Override
    public void print() {
        System.out.println(toString());
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


    private void extendArray() {
        if (size == objects.length - 1) {
            Object[] temp = objects;
            capacity *= COEFICIENT;
            objects = new Object[capacity];
            for (int i = 0; i < size; i++) {
                objects[i] = temp[i];
            }
        }
    }

    private int findIndex(E value) {
        for (int i = 0; i < size; i++) {
            if (objects[i].equals(value)) {
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
