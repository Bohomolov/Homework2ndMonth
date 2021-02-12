package binarytree.impl;

import binarytree.ITree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AVLTreeTest {

    @Test
    void clear() {
    }

    @Test
    void size() {
    }

    @Test
    void add() {
        AVLTree myTree = new  AVLTree();
        myTree.add(15);
        myTree.add(8);
        myTree.add(10);
        myTree.add(50);
        myTree.add(55);
        myTree.add(45);
        myTree.add(1);
        myTree.add(0);
        myTree.add(3);
        myTree.add(2);


        myTree.clear();

        System.out.println(myTree.size() + " size ");
        System.out.println(myTree.getHeight() + " height");
        System.out.println(myTree.rootValue() + " root");
        myTree.print();
    }
}