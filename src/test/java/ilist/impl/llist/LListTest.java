package ilist.impl.llist;

import ilist.interfaces.IList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LListTest {
    static Stream<Arguments> clearTest() {
        IList myList = new LList();
        myList.add(0);
        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList.add(4);
        myList.add(5);
        IList myList1 = new LList();

        IList myList2 = new LList();
        myList2.add(1);

        return Stream.of(
                Arguments.arguments(myList, 0, new int[]{}),
                Arguments.arguments(myList1, 0, new int[]{}),
                Arguments.arguments(myList2, 0, new int[]{})
        );
    }

    @ParameterizedTest(name = "Clear test. Inp data: {0},{1},{2}")
    @MethodSource("clearTest")
    void cleatTest(IList myList, int sizeExp, int[] expected) {
        myList.clear();
        int sizeActual = myList.size();
        int[] actual = myList.toArray();
        assertArrayEquals(expected, actual);
        assertEquals(sizeExp, sizeActual);
    }

    //============================================ Size ========================
    static Stream<Arguments> sizeTest() {
        IList myList = new LList();
        myList.add(0);
        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList.add(4);
        myList.add(5);
        IList myList1 = new LList();

        IList myList2 = new LList();
        myList2.add(1);

        IList myList3 = new LList();
        myList3.add(5);
        myList3.add(5);
        myList3.add(5);
        myList3.add(5);
        myList3.add(5);
        myList3.add(5);
        myList3.add(5);
        myList3.add(5);
        myList3.add(5);
        myList3.add(5);
        myList3.add(5);
        return Stream.of(
                Arguments.arguments(myList, 6),
                Arguments.arguments(myList1, 0),
                Arguments.arguments(myList2, 1),
                Arguments.arguments(myList3, 11)
        );
    }

    @ParameterizedTest(name = "Size test. Inp data: {0},{1}")
    @MethodSource("sizeTest")
    void sizeTest(IList myList, int expected) {
        int actual = myList.size();
        assertEquals(expected, actual);
    }

    //======================================== getValue ==========================
    static Stream<Arguments> getValueTest() {
        IList myList = new LList();
        myList.add(0);
        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList.add(4);
        myList.add(5);
        myList.add(-5);
        myList.add(-1);

        IList myList2 = new LList();
        myList2.add(1);

        return Stream.of(
                Arguments.arguments(myList, 5, 5),
                Arguments.arguments(myList, 6, -1),
                Arguments.arguments(myList, 1, 1),
                Arguments.arguments(myList, 1, 1),
                Arguments.arguments(myList, 4, 4),
                Arguments.arguments(myList, -1, -1),
                Arguments.arguments(myList, -1, -1),
                Arguments.arguments(myList2, 1, 1),
                Arguments.arguments(myList2, -1, 1),
                Arguments.arguments(myList, -5, -5)
        );
    }

    @ParameterizedTest(name = "Get value test. Inp data: {0},{1},{2}")
    @MethodSource("getValueTest")
    void getValueTest(IList myList, int value, int expected) {
        int actual = myList.getValue(value);
        assertEquals(expected, actual);
    }
}