package secondmounthlastpractice.sorters;

import ilist.impl.llist.LList;

public class LListSorter {
    private final QuickSorter quickSorter;

    public LListSorter() {
        quickSorter = new QuickSorter();
    }

    public LList lListSort(LList iList) {
        if (iList == null || iList.size() == 0) {
            throw new IllegalArgumentException("List is empty or null");
        }

        int[] array = iList.toArray();
        quickSorter.quickSort(array);
        iList.clear();
        iList.init(array);
        return iList;
    }
}
