package secondmounthlastpractice.sorters;

import ilist.impl.llist.DoubleLList;

public class DoubleListSort {
    private final TreeSorter treeSorter;

    public DoubleListSort() {
        treeSorter = new TreeSorter();
    }

    public DoubleLList doubleListSort(DoubleLList iList) {
        if (iList == null || iList.size() == 0) {
            throw new IllegalArgumentException("List is empty or null");
        }
        int[] array = iList.toArray();
        array = treeSorter.treeSort(array);
        iList.clear();
        iList.init(array);
        return iList;
    }
}
