package secondmounthlastpractice.sorters;

import binarytree.ITree;
import binarytree.impl.BSTree;
import ilist.impl.llist.LList;
import ilist.interfaces.IList;

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

//    private Node root;
//
//    public int[] treeSort(int[] array) {
//        if (array == null || array.length == 0){
//            throw new IllegalArgumentException("Array is empty or null.");
//        }
//        init(array);
//        IList iList = new LList();
//        round(iList, root);
//        return iList.toArray();
//    }
//
//    private void round(IList iList, Node node) {
//        if (node != null) {
//            round(iList, node.left);
//            iList.add(node.value);
//            round(iList, node.right);
//        }
//    }
//
//    private void init(int[] array) {
//        for (int i : array) {
//            add(i);
//        }
//    }
//
//    private void add(int value) {
//        if (root == null) {
//            root = new Node(value, null, null);
//        } else {
//            add(value, root);
//        }
//    }
//
//    private void add(int value, Node node) {
//        if (value < node.value) {
//            if (node.left == null) {
//                node.left = new Node(value, null, null);
//            } else {
//                add(value, node.left);
//            }
//        } else {
//            if (node.right == null) {
//                node.right = new Node(value, null, null);
//            } else {
//                add(value, node.right);
//            }
//        }
//    }
//
//    private static class Node {
//        int value;
//        Node left;
//        Node right;
//
//        private Node(int value, Node left, Node right) {
//            this.value = value;
//            this.left = left;
//            this.right = right;
//        }
//    }
}
