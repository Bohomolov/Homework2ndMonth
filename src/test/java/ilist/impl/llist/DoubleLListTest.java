package ilist.impl.llist;

import ilist.interfaces.IList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


class DoubleLListTest {
    static Stream<Arguments> clearTest() {
        IList myList = new DoubleLList();
        myList.add(0);
        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList.add(4);
        myList.add(5);
        IList myList1 = new DoubleLList();

        IList myList2 = new DoubleLList();
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
        IList myList = new DoubleLList();
        myList.add(0);
        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList.add(4);
        myList.add(5);
        IList myList1 = new DoubleLList();

        IList myList2 = new DoubleLList();
        myList2.add(1);

        IList myList3 = new DoubleLList();
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
        IList myList = new DoubleLList();
        myList.add(0);
        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList.add(4);
        myList.add(5);
        myList.add(-5);
        myList.add(-1);

        IList myList2 = new DoubleLList();

        myList2.add(1);

        return Stream.of(
                Arguments.arguments(myList, 5, 5),
                Arguments.arguments(myList, 1, 1),
                Arguments.arguments(myList, 1, 1),
                Arguments.arguments(myList, 0, 0),
                Arguments.arguments(myList, 4, 4),
                Arguments.arguments(myList, -1, -1),
                Arguments.arguments(myList, -1, -1),
                Arguments.arguments(myList2, 1, 1),
                Arguments.arguments(myList, -5, -5)
        );
    }

    @ParameterizedTest(name = "Get value test. Inp data: {0},{1},{2}")
    @MethodSource("getValueTest")
    void getValueTest(IList myList, int value, int expected) {
        int actual = myList.getValue(value);
        assertEquals(expected, actual);
    }

    @Test
    void valueNotFoundTest() {
        assertThrows(IllegalArgumentException.class, () -> {
            IList myList = new DoubleLList();
            myList.add(9);
            myList.add(7);
            myList.getValue(5);
        }, "Value not found");
    }

    //====================================== get ===============================
    static Stream<Arguments> getTest() {
        IList myList = new DoubleLList();
        myList.add(0);
        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList.add(4);
        myList.add(5);
        myList.add(-6);
        myList.add(-7);
        myList.add(8);
        myList.add(15);

        return Stream.of(
                Arguments.arguments(myList, 0, 0),
                Arguments.arguments(myList, 2, 2),
                Arguments.arguments(myList, 0, 0),
                Arguments.arguments(myList, 3, 3),
                Arguments.arguments(myList, 5, 5),
                Arguments.arguments(myList, 6, -6),
                Arguments.arguments(myList, 7, -7),
                Arguments.arguments(myList, 9, 15),
                Arguments.arguments(myList, 8, 8)
        );
    }

    @ParameterizedTest(name = "Get test. Inp data: {0},{1},{2}")
    @MethodSource("getTest")
    void getTest(IList myList, int index, int expected) {
        int actual = myList.get(index);
        assertEquals(expected, actual);
    }

    @Test
    void incorrectIndexAbsentValue() {
        IList myList = new DoubleLList();
        myList.add(7);
        myList.add(9);
        myList.add(0);
        myList.add(-9);
        myList.add(1);
        assertThrows(IllegalArgumentException.class, () -> {
            myList.get(5);
        }, "");
    }

    @Test
    void incorrectIndexNegative() {
        IList myList = new LList();
        assertThrows(IllegalArgumentException.class, () -> {
            myList.get(-5);
        }, "");
    }


    //====================================== add ===============================
    static Stream<Arguments> addTest() {
        IList myList = new DoubleLList();
        return Stream.of(
                Arguments.arguments(myList, 5, new int[]{5}, true),
                Arguments.arguments(myList, 1, new int[]{5, 1}, true),
                Arguments.arguments(myList, 2, new int[]{5, 1, 2}, true),
                Arguments.arguments(myList, 0, new int[]{5, 1, 2, 0}, true),
                Arguments.arguments(myList, 5, new int[]{5, 1, 2, 0, 5}, true),
                Arguments.arguments(myList, -1, new int[]{5, 1, 2, 0, 5, -1}, true),
                Arguments.arguments(myList, -9, new int[]{5, 1, 2, 0, 5, -1, -9}, true)
        );
    }

    @ParameterizedTest(name = "Add test. Inp data: {0},{1},{2}")
    @MethodSource("addTest")
    void addTest(IList myList, int value, int[] expected, boolean expBoolean) {
        boolean actBool = myList.add(value);
        int[] actual = myList.toArray();
        assertArrayEquals(expected, actual);
        assertEquals(expBoolean, actBool);
    }

    //========================== add at index ==============================
    static Stream<Arguments> addAtIndexTest() {
        IList myList = new DoubleLList();

        return Stream.of(
                Arguments.arguments(myList, 0, 5, new int[]{5}, true),
                Arguments.arguments(myList, 1, 1, new int[]{5, 1}, true),
                Arguments.arguments(myList, 2, 2, new int[]{5, 1, 2}, true),
                Arguments.arguments(myList, 3, 0, new int[]{5, 1, 2, 0}, true),
                Arguments.arguments(myList, 0, 5, new int[]{5, 5, 1, 2, 0}, true),
                Arguments.arguments(myList, 5, -1, new int[]{5, 5, 1, 2, 0, -1}, true),
                Arguments.arguments(myList, 6, -9, new int[]{5, 5, 1, 2, 0, -1, -9}, true),
                Arguments.arguments(myList, 8, -9, new int[]{5, 5, 1, 2, 0, -1, -9}, false),
                Arguments.arguments(myList, -8, -9, new int[]{5, 5, 1, 2, 0, -1, -9}, false),
                Arguments.arguments(myList, 7, 10, new int[]{5, 5, 1, 2, 0, -1, -9, 10}, true),
                Arguments.arguments(myList, 4, 3, new int[]{5, 5, 1, 2, 3, 0, -1, -9, 10}, true),
                Arguments.arguments(myList, 7, 7, new int[]{5, 5, 1, 2, 3, 0, -1, 7, -9, 10}, true),
                Arguments.arguments(myList, 8, 8, new int[]{5, 5, 1, 2, 3, 0, -1, 7, 8, -9, 10}, true),
                Arguments.arguments(myList, 2, 2, new int[]{5, 5, 2, 1, 2, 3, 0, -1, 7, 8, -9, 10}, true)
        );
    }

    @ParameterizedTest(name = "Add at index test. Inp data: {0},{1},{2}")
    @MethodSource("addAtIndexTest")
    void addAtIndexTest(IList myList, int index, int value, int[] expected) {
        myList.add(index, value);
        int[] actual = myList.toArray();
        assertArrayEquals(expected, actual);
    }

    // ===================================== remove =============================
    static Stream<Arguments> removeTest() {
        IList myList = new DoubleLList();
        myList.add(0);
        myList.add(-1);
        myList.add(-18);
        myList.add(18);
        myList.add(99);
        myList.add(5);
        myList.add(525);
        return Stream.of(
                Arguments.arguments(myList, 5, new int[]{0, -1, -18, 18, 99, 525}),
                Arguments.arguments(myList, 525, new int[]{0, -1, -18, 18, 99}),
                Arguments.arguments(myList, 0, new int[]{-1, -18, 18, 99}),
                Arguments.arguments(myList, 18, new int[]{-1, -18, 99}),
                Arguments.arguments(myList, -18, new int[]{-1, 99}),
                Arguments.arguments(myList, -1, new int[]{99}),
                Arguments.arguments(myList, 99, new int[]{})
        );
    }

    @ParameterizedTest(name = "Remove test. Inp data: {1},{2}")
    @MethodSource("removeTest")
    void removeTest(IList myList, int value, int[] expected) {
        int actInt = myList.remove(value);
        int[] actual = myList.toArray();
        assertArrayEquals(expected, actual);
        assertEquals(value, actInt);
    }

    @Test
    void isRootEmptyTestRemove() {
        assertThrows(IllegalArgumentException.class, () -> {
            IList myList = new DoubleLList();
            myList.remove(5);
        }, "root is null");
    }

    @Test
    void incorrectIndexRemoveNegative() {
        IList myList = new DoubleLList();
        assertThrows(IllegalArgumentException.class, () -> {
            myList.remove(-1);
        }, "value is absent");
    }

    //================================== remove by index =================================
    static Stream<Arguments> removeByIndexTest() {
        IList myList = new DoubleLList();
        myList.add(0);
        myList.add(-1);
        myList.add(-18);
        myList.add(18);
        myList.add(99);
        myList.add(5);
        myList.add(525);
        return Stream.of(
                Arguments.arguments(myList, 5, 5, new int[]{0, -1, -18, 18, 99, 525}),
                Arguments.arguments(myList, 5, 525, new int[]{0, -1, -18, 18, 99}),
                Arguments.arguments(myList, 0, 0, new int[]{-1, -18, 18, 99}),
                Arguments.arguments(myList, 2, 18, new int[]{-1, -18, 99}),
                Arguments.arguments(myList, 1, -18, new int[]{-1, 99}),
                Arguments.arguments(myList, 0, -1, new int[]{99}),
                Arguments.arguments(myList, 0, 99, new int[]{})
        );
    }

    @ParameterizedTest(name = "Remove by index test. Inp data: {1},{2}")
    @MethodSource("removeByIndexTest")
    void removeByIndexTest(IList myList, int index, int expectedInt, int[] expected) {
        int actInt = myList.removeByIndex(index);
        int[] actual = myList.toArray();
        assertArrayEquals(expected, actual);
        assertEquals(expectedInt, actInt);
    }

    @Test
    void incorrectIndexRemoveByIndNegative() {
        IList myList = new DoubleLList();
        assertThrows(IllegalArgumentException.class, () -> {
            myList.removeByIndex(-1);
        }, "");
    }

    @Test
    void incorrectIndexRemoveByIndExtra() {
        IList myList = new DoubleLList();
        assertThrows(IllegalArgumentException.class, () -> {
            myList.removeByIndex(5);
        }, "");
    }

    //=========================== contains =========================
    static Stream<Arguments> containsTest() {
        IList myList = new DoubleLList();
        myList.add(0);
        myList.add(5);
        myList.add(6);
        myList.add(8);
        myList.add(7);
        myList.add(10);
        myList.add(-8);
        myList.add(-20);
        myList.add(-2200);

        IList l2 = new DoubleLList();
        return Stream.of(
                Arguments.arguments(myList, 0, true),
                Arguments.arguments(myList, -2200, true),
                Arguments.arguments(myList, 2200, false),
                Arguments.arguments(myList, 2200, false),
                Arguments.arguments(myList, -8, true),
                Arguments.arguments(myList, 8, true),
                Arguments.arguments(myList, 8, true),
                Arguments.arguments(myList, 80, false),
                Arguments.arguments(l2, 0, false),
                Arguments.arguments(l2, 99, false),
                Arguments.arguments(l2, -9, false)

        );
    }

    @ParameterizedTest(name = "Contains. Data input: {1},{2}")
    @MethodSource("containsTest")
    void containsTest(IList myList, int value, boolean expected) {
        boolean actual = myList.contains(value);
        assertEquals(expected, actual);
    }

    //=================================== set ========================================
    static Stream<Arguments> setTest() {
        IList myList = new DoubleLList();
        myList.add(0);
        myList.add(5);
        myList.add(6);
        myList.add(8);
        myList.add(7);
        myList.add(10);
        myList.add(-8);
        myList.add(-20);
        myList.add(-2200);

        return Stream.of(
                Arguments.arguments(myList, 0, 0, true, new int[]{0, 5, 6, 8, 7, 10, -8, -20, -2200}),
                Arguments.arguments(myList, 1, 1, true, new int[]{0, 1, 6, 8, 7, 10, -8, -20, -2200}),
                Arguments.arguments(myList, 2, 2, true, new int[]{0, 1, 2, 8, 7, 10, -8, -20, -2200}),
                Arguments.arguments(myList, 3, 3, true, new int[]{0, 1, 2, 3, 7, 10, -8, -20, -2200}),
                Arguments.arguments(myList, 4, 4, true, new int[]{0, 1, 2, 3, 4, 10, -8, -20, -2200}),
                Arguments.arguments(myList, 5, 5, true, new int[]{0, 1, 2, 3, 4, 5, -8, -20, -2200}),
                Arguments.arguments(myList, 6, 6, true, new int[]{0, 1, 2, 3, 4, 5, 6, -20, -2200}),
                Arguments.arguments(myList, 6, -6, true, new int[]{0, 1, 2, 3, 4, 5, -6, -20, -2200}),
                Arguments.arguments(myList, 7, -7, true, new int[]{0, 1, 2, 3, 4, 5, -6, -7, -2200}),
                Arguments.arguments(myList, 8, -8, true, new int[]{0, 1, 2, 3, 4, 5, -6, -7, -8}),
                Arguments.arguments(myList, 9, -9, false, new int[]{0, 1, 2, 3, 4, 5, -6, -7, -8}),
                Arguments.arguments(myList, 9, -9, false, new int[]{0, 1, 2, 3, 4, 5, -6, -7, -8}),
                Arguments.arguments(myList, 10, -9, false, new int[]{0, 1, 2, 3, 4, 5, -6, -7, -8}),
                Arguments.arguments(myList, 11, -9, false, new int[]{0, 1, 2, 3, 4, 5, -6, -7, -8}),
                Arguments.arguments(myList, -1, -9, false, new int[]{0, 1, 2, 3, 4, 5, -6, -7, -8}),
                Arguments.arguments(myList, -8, -9, false, new int[]{0, 1, 2, 3, 4, 5, -6, -7, -8})
        );
    }

    @ParameterizedTest(name = "Set. Data input: {1},{2}")
    @MethodSource("setTest")
    void setTest(IList myList, int index, int value, boolean expected, int[] expectedArr) {
        boolean actual = myList.set(index, value);
        int[] actualArr = myList.toArray();
        assertEquals(expected, actual);
        assertArrayEquals(expectedArr, actualArr);
    }

    //========================= to string ================================
    static Stream<Arguments> toStringTest() {
        IList myList = new DoubleLList();
        IList myList2 = new DoubleLList();
        myList.add(0);
        myList.add(1);
        myList.add(2);
        myList.add(3);
        return Stream.of(
                Arguments.arguments(myList, "[0, 1, 2, 3]"),
                Arguments.arguments(myList2, "[]")
        );
    }

    @ParameterizedTest(name = "To string. Data input: {1}")
    @MethodSource("toStringTest")
    void toStringTest(IList myList, String expected) {
        String actual = myList.toString();
        assertEquals(expected, actual);
    }

    //=================================== to Array=======================================
    static Stream<Arguments> toArrayTest() {
        IList myList = new DoubleLList();
        IList myList2 = new DoubleLList();
        myList.add(0);
        myList.add(1);
        myList.add(2);
        myList.add(3);

        IList myList3 = new DoubleLList();
        myList3.add(0);
        myList3.add(1);
        myList3.add(2);
        myList3.add(3);
        myList3.add(4);
        myList3.add(5);
        myList3.add(6);
        myList3.add(7);
        myList3.add(8);
        myList3.add(9);
        myList3.add(10);
        myList3.add(-9);
        myList3.add(-8);
        myList3.add(-7);
        myList3.add(-6);
        myList3.add(-5);
        myList3.add(-4);
        myList3.add(-3);
        myList3.add(-2);
        myList3.add(-1);
        myList3.add(0);
        return Stream.of(
                Arguments.arguments(myList, new int[]{0, 1, 2, 3}),
                Arguments.arguments(myList2, new int[]{}),
                Arguments.arguments(myList3, new int[]{0, 1, 2, 3,4,5,6,7,8,9,10,-9,-8,-7,-6,-5,-4,-3,-2,-1,0})
        );
    }

    @ParameterizedTest(name = "To array. Data input: {1}")
    @MethodSource("toArrayTest")
    void toArrayTest(IList myList, int[] expected) {
        int[] actual = myList.toArray();
        assertArrayEquals(expected, actual);
    }

    //============================== remove all ===================================
    static Stream<Arguments> removeAllTest() {
        IList myList = new DoubleLList();
        myList.add(0);
        myList.add(1);
        myList.add(2);
        myList.add(3);
        return Stream.of(
                Arguments.arguments(myList, new int[]{0, 3}, new int[]{1, 2}, true),
                Arguments.arguments(myList, new int[]{2, 1}, new int[]{}, true)
        );
    }

    @ParameterizedTest(name = "Remove all. Data input: {1},{2}")
    @MethodSource("removeAllTest")
    void removeAllTest(IList myList, int[] arr, int[] expected, boolean expectedBool) {
        boolean bolActual = myList.removeAll(arr);
        int[] actual = myList.toArray();
        assertArrayEquals(expected, actual);
        assertEquals(expectedBool, bolActual);
    }

    @Test
    void isRootEmptyTestRemoveAll() {
        assertThrows(IllegalArgumentException.class, () -> {
            IList myList = new DoubleLList();
            int[] ints = {1, 5, 6};
            myList.removeAll(ints);
        }, "root is empty");
    }

    @Test
    void isValueAbsentTestRemoveAll() {
        assertThrows(IllegalArgumentException.class, () -> {
            IList myList = new DoubleLList();
            myList.add(10);
            int[] ints = {1, 5, 6};
            myList.removeAll(ints);
        }, "value is absent");
    }
}