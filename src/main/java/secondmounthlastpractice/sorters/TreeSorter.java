package secondmounthlastpractice.sorters;

import binarytree.ITree;
import binarytree.impl.BSTree;

public class TreeSorter {
    private final ITree myTree;

    public TreeSorter() {
        myTree = new BSTree();
    }

    public int[] treeSort(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("Array is empty or null.");
        }
        myTree.init(array);
        return myTree.toArray();
    }
}
