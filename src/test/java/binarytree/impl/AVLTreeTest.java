package binarytree.impl;

import binarytree.ITree;
import oophomework.utils.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class AVLTreeTest {
    @Test
    public void clearTest() {
        ITree myTree = new AVLTree();
        myTree.add(1);
        myTree.add(2);
        myTree.add(3);
        myTree.add(4);
        myTree.add(5);
        myTree.clear();
        int[] expected = new int[]{};
        int[] actual = myTree.toArray();
        assertArrayEquals(expected, actual);
    }

    //============================================ size =====================================
    static Stream<Arguments> sizeTest() {
        ITree myTree1 = new AVLTree();
        myTree1.init(new int[]{5, 9, 8, 7, 3, 4});

        ITree myTree2 = new AVLTree();
        myTree2.init(new int[]{-1});

        ITree myTree3 = new AVLTree();
        myTree3.init(new int[]{0, 0, 0, 0, 0});

        ITree myTree4 = new AVLTree();

        return Stream.of(
                Arguments.arguments(myTree1, 6),
                Arguments.arguments(myTree2, 1),
                Arguments.arguments(myTree3, 1),
                Arguments.arguments(myTree4, 0)
        );
    }

    @ParameterizedTest(name = "Size test. {1}")
    @MethodSource("sizeTest")
    public void sizeTestMain(ITree myTree, int expected) {
        int actual = myTree.size();
        assertEquals(expected, actual);
    }

    //=================================== to array ======================================
    static Stream<Arguments> toArrayTest() {
        ITree myTree1 = new AVLTree();
        myTree1.init(new int[]{5, 9, 8, 7, 3, 4, 5});

        ITree myTree2 = new AVLTree();
        myTree2.init(new int[]{-1});

        ITree myTree3 = new AVLTree();
        myTree3.init(new int[]{0, 0, 0, 0, 0});

        ITree myTree4 = new AVLTree();

        return Stream.of(
                Arguments.arguments(myTree1, new int[]{3, 4, 5, 5, 7, 8, 9}),
                Arguments.arguments(myTree2, new int[]{-1}),
                Arguments.arguments(myTree3, new int[]{0, 0, 0, 0, 0}),
                Arguments.arguments(myTree4, new int[]{})
        );
    }

    @ParameterizedTest(name = "to Array Test.")
    @MethodSource("toArrayTest")
    public void toArrayTestMain(ITree myTree, int[] expected) {
        int[] actual = myTree.toArray();
        assertArrayEquals(expected, actual);
    }

    //=============================================== add ==================================
    static Stream<Arguments> addTest() {
        ITree myTree = new AVLTree();
//        myTree.init(new int[]{300, 100, 200, 700, 750, 725, 150, 350, 400, 500, 900, 50, 175, 225, 650, 600, 375, 75, 25});
//        myTree.init(new int[]{300, 400, 100, 150, 200, 250, 350, 325, 375, 425});
        myTree.add(300);
        myTree.add(100);
        myTree.add(200);
        myTree.add(700);
        myTree.add(750);
        myTree.add(725);
        myTree.add(150);
        myTree.add(350);
        myTree.add(400);
        myTree.add(500);
        myTree.add(900);
        myTree.add(50);
        myTree.add(175);
        myTree.add(225);
        myTree.add(650);
        myTree.print();


        return Stream.of(
                Arguments.arguments(myTree,  new int[]{ 50,  100, 150, 175, 200, 225, 300, 350, 375, 400, 500,  650, 700, 725, 750, 900})
//                Arguments.arguments(myTree, 0, new int[]{ 25, 50, 75, 100, 150, 175, 200, 225, 300, 350, 375, 400, 500, 600, 650, 700, 725, 750, 900}),
//                Arguments.arguments(myTree, -10, new int[]{-10, 0, 25, 50, 75, 100, 150, 175, 200, 225, 300, 350, 375, 400, 500, 600, 650, 700, 725, 750, 900}),
//                Arguments.arguments(myTree, -100, new int[]{-100, -10, 0, 25, 50, 75, 100, 150, 175, 200, 225, 300, 350, 375, 400, 500, 600, 650, 700, 725, 750, 900}),
//                Arguments.arguments(myTree, -1000, new int[]{-1000, -100, -10, 0, 25, 50, 75, 100, 150, 175, 200, 225, 300, 350, 375, 400, 500, 600, 650, 700, 725, 750, 900}),
//                Arguments.arguments(myTree, -10000, new int[]{-10000, -1000, -100, -10, 0, 25, 50, 75, 100, 150, 175, 200, 225, 300, 350, 375, 400, 500, 600, 650, 700, 725, 750, 900})
        );
    }

    @ParameterizedTest(name = "Add test.")
    @MethodSource("addTest")
    void addTestMain(ITree myTree, int[] expected) {
//        myTree.add(value);
        int[] actual = myTree.toArray();
        assertArrayEquals(expected, actual);
    }

    //======================================================== DELETE ======================================================
    static Stream<Arguments> deleteTest() {
        ITree myTree = new AVLTree();
        myTree.init(new int[]{300, 100, 200, 700, 750, 725, 150, 350, 400, 500, 900, 50, 175, 225, 650, 600, 375, 75, 25});
        return Stream.of(
                Arguments.arguments(myTree, 725, new int[]{25, 50, 75, 100, 150, 175, 200, 225, 300, 350, 375, 400, 500, 600, 650, 700, 750, 900}),
                Arguments.arguments(myTree, 50, new int[]{25, 75, 100, 150, 175, 200, 225, 300, 350, 375, 400, 500, 600, 650, 700, 750, 900}),
                Arguments.arguments(myTree, 25, new int[]{75, 100, 150, 175, 200, 225, 300, 350, 375, 400, 500, 600, 650, 700, 750, 900}),
                Arguments.arguments(myTree, 200, new int[]{75, 100, 150, 175, 225, 300, 350, 375, 400, 500, 600, 650, 700, 750, 900}),
                Arguments.arguments(myTree, 300, new int[]{75, 100, 150, 175, 225, 350, 375, 400, 500, 600, 650, 700, 750, 900}),
                Arguments.arguments(myTree, 900, new int[]{75, 100, 150, 175, 225, 350, 375, 400, 500, 600, 650, 700, 750}),
                Arguments.arguments(myTree, 225, new int[]{75, 100, 150, 175, 350, 375, 400, 500, 600, 650, 700, 750}),
                Arguments.arguments(myTree, 400, new int[]{75, 100, 150, 175, 350, 375, 500, 600, 650, 700, 750}),
                Arguments.arguments(myTree, 650, new int[]{75, 100, 150, 175, 350, 375, 500, 600, 700, 750}),
                Arguments.arguments(myTree, 600, new int[]{75, 100, 150, 175, 350, 375, 500, 700, 750}),
                Arguments.arguments(myTree, 700, new int[]{75, 100, 150, 175, 350, 375, 500, 750}),
                Arguments.arguments(myTree, 75, new int[]{100, 150, 175, 350, 375, 500, 750}),
                Arguments.arguments(myTree, 175, new int[]{100, 150, 350, 375, 500, 750}),
                Arguments.arguments(myTree, 375, new int[]{100, 150, 350, 500, 750}),
                Arguments.arguments(myTree, 100, new int[]{150, 350, 500, 750}),
                Arguments.arguments(myTree, 750, new int[]{150, 350, 500}),
                Arguments.arguments(myTree, 350, new int[]{150, 500}),
                Arguments.arguments(myTree, 150, new int[]{500}),
                Arguments.arguments(myTree, 500, new int[]{})
        );
    }

    @ParameterizedTest(name = "Delete test. {1},{2}")
    @MethodSource("deleteTest")
    void delete(ITree myTree, int value, int[] expected) {
        myTree.delete(value);
        int[] actual = myTree.toArray();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void deleteExceptionTestRootIsNull() {
        ITree myTree = new AVLTree();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            myTree.delete(1);
        }, Constants.ARRAY_IS_NULL);
    }

    @Test
    public void deleteExceptionTestValueNotFound() {
        ITree myTree = new AVLTree();
        myTree.init(new int[]{1, 2, 3, 5, 6, 8});
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            myTree.delete(4);
        }, Constants.ARRAY_IS_NULL);
    }

    //============================== nodex ===========================================
    static Stream<Arguments> getHeightTest() {
        ITree myTree1 = new AVLTree();
        myTree1.init(new int[]{5, 9, 8, 7, 3, 4});

        ITree myTree2 = new AVLTree();
        myTree2.init(new int[]{-1});

        ITree myTree3 = new AVLTree();
        myTree3.init(new int[]{0, 0, 0, 0, 0});

        ITree myTree4 = new AVLTree();

        ITree myTree5 = new AVLTree();
        myTree5.init(new int[]{15, 33, 7, 10, 11, 8, 5, 4, 6, 25, 22, 27, 38, 35, 40});

        return Stream.of(
                Arguments.arguments(myTree1, 4),
                Arguments.arguments(myTree2, 1),
                Arguments.arguments(myTree3, 5),
                Arguments.arguments(myTree4, 0),
                Arguments.arguments(myTree5, 4)
        );
    }

    @ParameterizedTest(name = "get Height Test. {0} ,{1}")
    @MethodSource("getHeightTest")
    void getHeightTestMain(ITree myTree, int expected) {
        int actual = myTree.getHeight();
        assertEquals(expected, actual);
    }

    //============================== nodex ===========================================
    static Stream<Arguments> nodesTest() {
        ITree myTree1 = new AVLTree();
        myTree1.init(new int[]{5, 9, 8, 7, 3, 4, 5});

        ITree myTree2 = new AVLTree();
        myTree2.init(new int[]{-1});

        ITree myTree3 = new AVLTree();
        myTree3.init(new int[]{0, 0, 0, 0, 0});

        ITree myTree4 = new AVLTree();

        ITree myTree5 = new AVLTree();
        myTree5.init(new int[]{15, 33, 7, 10, 11, 8, 5, 4, 6, 25, 22, 27, 38, 35, 40});

        return Stream.of(
                Arguments.arguments(myTree1, 5),
                Arguments.arguments(myTree2, 0),
                Arguments.arguments(myTree3, 4),
                Arguments.arguments(myTree4, 0),
                Arguments.arguments(myTree5, 7)
        );
    }

    @ParameterizedTest(name = "Nodes test")
    @MethodSource("nodesTest")
    void nodesTestMain(ITree myTree, int expected) {
        int actual = myTree.nodes();
        assertEquals(expected, actual);
    }

    //============================== leaves ===========================================
    static Stream<Arguments> leavesTest() {
        ITree myTree1 = new AVLTree();
        myTree1.init(new int[]{5, 9, 8, 7, 3, 4, 5});

        ITree myTree2 = new AVLTree();
        myTree2.init(new int[]{-1});

        ITree myTree3 = new AVLTree();
        myTree3.init(new int[]{0, 0, 0, 0, 0});

        ITree myTree4 = new AVLTree();

        ITree myTree5 = new AVLTree();
        myTree5.init(new int[]{15, 33, 7, 10, 11, 8, 5, 4, 6, 25, 22, 27, 38, 35, 40});

        return Stream.of(
                Arguments.arguments(myTree1, 2),
                Arguments.arguments(myTree2, 1),
                Arguments.arguments(myTree3, 1),
                Arguments.arguments(myTree4, 0),
                Arguments.arguments(myTree5, 8)
        );
    }

    @ParameterizedTest(name = "Leaves test")
    @MethodSource("leavesTest")
    void leavesTestMain(ITree myTree, int expected) {
        int actual = myTree.leaves();
        assertEquals(expected, actual);
    }
}