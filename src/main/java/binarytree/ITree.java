package binarytree;

public interface ITree {
    void init(int[] array);
    void print();
    void clear();
    int size();
    int[] toArray();
    void add(int val);
    void delete(int val);
    int getWidth();
    int getHeight();
    int nodes();
    int leaves();
    void reverse();
}
