package binarytree.impl;

import binarytree.ITree;
import org.junit.jupiter.api.Test;

import java.util.TreeMap;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.*;

class BSTreeTest {
    @Test
    void test() {
        ITree tree = new BSTree();
        tree.add(10);
        tree.add(9);
        tree.add(7);
        tree.add(6);
        tree.add(11);
        tree.add(12);
        tree.add(16);
        tree.add(14);
        tree.add(17);
        tree.add(8);
        tree.add(19);
        tree.add(18);
        tree.add(20);
        tree.add(21);
        tree.add(20);
        tree.add(22);

        System.out.println(tree.nodes());
        System.out.println(tree.leaves());
        tree.print();
    }

}