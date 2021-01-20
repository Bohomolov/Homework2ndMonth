package ilist.impl.alist.iterator;

import java.util.Iterator;

public class AListIterator<Integer> implements Iterator<Integer> {
    private int index = 0;
    private final Integer[] values;
    public AListIterator(Integer[] values){
        this.values = values;
    }
    @Override
    public boolean hasNext() {
        return index < values.length;
    }

    @Override
    public Integer next() {
        return values[index++];
    }
}
