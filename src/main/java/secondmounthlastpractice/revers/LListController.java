package secondmounthlastpractice.revers;

import ilist.interfaces.IList;

public class LListController {
    public IList reversLList(IList lList) {
        if (lList == null || lList.size() == 0) {
            throw new IllegalArgumentException("List is empty or null");
        }
        int[] array = reversArray(lList.toArray());
        lList.clear();
        lList.init(array);
        return lList;
    }

    private int[] reversArray(int[] array) {
        int[] tmp = new int[array.length];
        int flag = array.length - 1;
        for (int i = 0; i < array.length; i++) {
            tmp[i] = array[flag];
            flag--;
        }

        for (int i = 0; i < array.length; i++) {
            array[i] = tmp[i];
        }
        return array;
    }
}
