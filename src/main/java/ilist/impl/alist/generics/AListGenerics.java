package ilist.impl.alist.generics;

import ilist.constantslist.ListConstants;
import ilist.interfaces.IListGenerics;

public class AListGenerics<E> implements IListGenerics {
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
    public Object get(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException(ListConstants.INCORRECT_ARGUMENT + size);
        }
        return objects[index];
    }

    @Override
    public boolean add(Object value) {
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
    public boolean add(int index, Object value) {
        if (index < 0 || index > size) {
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
    public Object remove(Object value) {
        Object returnObject = 0;
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
        return returnObject;
    }

    @Override
    public Object removeByIndex(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException(ListConstants.INCORRECT_ARGUMENT + size);
        }
        Object output = null;
        int count = 0;
        for (int i = 0; i < size; i++) {
            if (i == index) {
                output = objects[index];
                continue;
            }
            objects[count] = objects[i];
            count++;
        }
        size--;
        return output;
    }

    @Override
    public boolean contains(Object value) {
        for (int i = 0; i < size; i++) {
            if (objects[i].equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean set(int index, Object value) {
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
    public Object[] toArray() {
        Object[] output = new Object[size];
        for (int i = 0; i < size; i++) {
            output[i] = objects[i];
        }
        return output;
    }

    @Override
    public boolean removeAll(Object[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException(ListConstants.ARRAY_IS_EMPTY);
        }
        for (int i = 0; i < arr.length; i++) {
            int count = 0;
            for (int j = 0; j < size; j++) {
                if (objects[j].equals(arr[i])) {
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
