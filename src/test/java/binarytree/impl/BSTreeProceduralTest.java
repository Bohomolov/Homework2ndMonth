package binarytree.impl;


import binarytree.ITree;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class BSTreeProceduralTest {

    static Stream<Arguments> addTest() {
        ITree myTree = new BSTreeProcedural();
        myTree.init(new int[]{300, 100, 200, 700, 750, 725, 150, 350, 400, 500, 900, 50, 175, 225, 650, 600, 375, 75, 25});
        return Stream.of(
                Arguments.arguments(myTree, 0, new int[]{0, 25, 50, 75, 100, 150, 175, 200, 225, 300, 350, 375, 400, 500, 600, 650, 700, 725, 750, 900}),
                Arguments.arguments(myTree, -10, new int[]{-10, 0, 25, 50, 75, 100, 150, 175, 200, 225, 300, 350, 375, 400, 500, 600, 650, 700, 725, 750, 900}),
                Arguments.arguments(myTree, -100, new int[]{-100, -10, 0, 25, 50, 75, 100, 150, 175, 200, 225, 300, 350, 375, 400, 500, 600, 650, 700, 725, 750, 900}),
                Arguments.arguments(myTree, -1000, new int[]{-1000, -100, -10, 0, 25, 50, 75, 100, 150, 175, 200, 225, 300, 350, 375, 400, 500, 600, 650, 700, 725, 750, 900}),
                Arguments.arguments(myTree, -10000, new int[]{-10000, -1000, -100, -10, 0, 25, 50, 75, 100, 150, 175, 200, 225, 300, 350, 375, 400, 500, 600, 650, 700, 725, 750, 900})
        );
    }

    @ParameterizedTest(name = "Add test. {1}, {2}")
    @MethodSource("addTest")
    void addTestMain(ITree myTree, int value, int[] expected) {
        myTree.add(value);
        int[] actual = myTree.toArray();
        assertArrayEquals(expected, actual);
    }

    //======================================================== DELETE ======================================================
    static Stream<Arguments> deleteTest() {
        ITree myTree = new BSTreeProcedural();
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
}