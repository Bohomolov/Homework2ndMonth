package ilist.impl.alist;

import ilist.interfaces.IList;
import ilist.constantslist.ListConstants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AListIntTest {

    static Stream<Arguments> clearTest() {

        return Stream.of(
                Arguments.arguments(0)
        );
    }

    @ParameterizedTest(name = "Clear test. Data input: {0}, {1}")
    @MethodSource("clearTest")
    public void clearTestMain(int expected) {
        IList myList = new AListInt();
        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList.add(4);
        myList.clear();
        int actual = myList.size();
        assertEquals(expected, actual);
    }

    //============================= Size ========================
    static Stream<Arguments> sizeTest() {
        IList myList1 = new AListInt();
        IList myList2 = new AListInt();
        IList myList3 = new AListInt();
        IList myList4 = new AListInt();

        myList1.add(1);
        myList1.add(2);
        myList1.add(3);
        myList1.add(4);
        myList1.add(1);
        myList1.add(5);
        myList1.add(6);

        myList3.add(1);
        myList3.add(2);
        myList3.add(3);
        myList3.add(4);
        myList3.add(5);
        myList3.add(6);
        myList3.add(7);
        myList3.add(8);

        myList4.add(1);

        return Stream.of(
                Arguments.arguments(myList1, 7),
                Arguments.arguments(myList2, 0),
                Arguments.arguments(myList3, 8),
                Arguments.arguments(myList4, 1)
        );
    }

    @ParameterizedTest(name = "Size test. Data input: {0}, {1}")
    @MethodSource("sizeTest")
    public void sizeTestMain(IList iList, int expected) {
        int actual = iList.size();
        assertEquals(expected, actual);
    }

    //================================= GeT =====================
    static Stream<Arguments> getTest() {
        IList myList1 = new AListInt();
        IList myList2 = new AListInt();
        IList myList3 = new AListInt();
        IList myList4 = new AListInt();

        myList1.add(1);
        myList1.add(2);
        myList1.add(3);
        myList1.add(4);
        myList1.add(1);
        myList1.add(5);
        myList1.add(6);

        myList3.add(1);
        myList3.add(2);
        myList3.add(3);
        myList3.add(4);
        myList3.add(5);
        myList3.add(6);
        myList3.add(7);
        myList3.add(8);

        myList4.add(1);

        return Stream.of(
                Arguments.arguments(myList1, 6, 6),
                Arguments.arguments(myList1, 4, 1),
                Arguments.arguments(myList1, 6, 6),
                Arguments.arguments(myList2, 0, 0),
                Arguments.arguments(myList3, 2, 3),
                Arguments.arguments(myList4, 0, 1)
        );
    }

    @ParameterizedTest(name = "Get test. Data input: {0}, {1}, {2}")
    @MethodSource("getTest")
    public void getTestMain(IList iList, int index, int expected) {
        int actual = iList.get(index);
        assertEquals(expected, actual);
    }

    @Test
    public void getTestExceptionsNegativeIndex() {
        IList myList = new AListInt();
        myList.add(1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            myList.get(-1);
        }, ListConstants.INCORRECT_ARGUMENT);
    }

    @Test
    public void getTestExceptionsExtraIndex() {
        IList myList = new AListInt();
        myList.add(1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            myList.get(5);
        }, ListConstants.INCORRECT_ARGUMENT);
    }

    //===========================================ADD=====================
    static Stream<Arguments> addTest() {
        IList myList1 = new AListInt();

        return Stream.of(
                Arguments.arguments(myList1, 1, true, new int[]{1}),
                Arguments.arguments(myList1, 2, true, new int[]{1, 2}),
                Arguments.arguments(myList1, 3, true, new int[]{1, 2, 3}),
                Arguments.arguments(myList1, 6, true, new int[]{1, 2, 3, 6}),
                Arguments.arguments(myList1, 0, true, new int[]{1, 2, 3, 6, 0}),
                Arguments.arguments(myList1, -8, true, new int[]{1, 2, 3, 6, 0, -8})

        );
    }

    @ParameterizedTest(name = "Add test. Data input: {0}, {1} , {2}")
    @MethodSource("addTest")
    public void addTestMain(IList iList, int value, boolean expected, int[] expectedInt) {
        boolean actual = iList.add(value);
        int[] actualInt = iList.toArray();
        assertEquals(expected, actual);
        assertArrayEquals(expectedInt, actualInt);
    }

    //============================ Add index =======================
    static Stream<Arguments> addIndexTest() {
        IList myList1 = new AListInt();
        myList1.add(1);
        myList1.add(2);
        myList1.add(3);
        myList1.add(4);

        return Stream.of(
                Arguments.arguments(myList1, 0, 10, true, new int[]{10, 1, 2, 3, 4}),
                Arguments.arguments(myList1, 2, 12, true, new int[]{10, 1, 12, 2, 3, 4}),
                Arguments.arguments(myList1, 6, 5, true, new int[]{10, 1, 12, 2, 3, 4, 5}),
                Arguments.arguments(myList1, -1, 10, false, new int[]{10, 1, 12, 2, 3, 4, 5}),
                Arguments.arguments(myList1, 100, 100, false, new int[]{10, 1, 12, 2, 3, 4, 5})

        );
    }

    @ParameterizedTest(name = "Add index test. Data input: {0}, {1}, {2}, {3}")
    @MethodSource("addIndexTest")
    public void addIndexTestMain(IList iList, int index, int value, boolean expected, int[] expectedInt) {
        boolean actual = iList.add(index, value);
        int[] actualInt = iList.toArray();
        assertEquals(expected, actual);
        assertArrayEquals(expectedInt, actualInt);
    }

    //============================ Remove =========================
    static Stream<Arguments> removeTest() {
        IList myList1 = new AListInt();
        myList1.add(1);
        myList1.add(2);
        myList1.add(3);
        myList1.add(3);
        myList1.add(4);
        myList1.add(5);
        myList1.add(-5);
        myList1.add(-15);

        return Stream.of(
                Arguments.arguments(myList1, 1, new int[]{2, 3, 3, 4, 5, -5, -15}),
                Arguments.arguments(myList1, -15, new int[]{2, 3, 3, 4, 5, -5}),
                Arguments.arguments(myList1, 3, new int[]{2, 3, 4, 5, -5}),
                Arguments.arguments(myList1, 5, new int[]{2, 3, 4, -5}),
                Arguments.arguments(myList1, -5, new int[]{2, 3, 4}),
                Arguments.arguments(myList1, 3, new int[]{2, 4}),
                Arguments.arguments(myList1, 4, new int[]{2}),
                Arguments.arguments(myList1, 2, new int[]{})

        );
    }

    @ParameterizedTest(name = "Remove test. Data input: {0}, {1}, {2}")
    @MethodSource("removeTest")
    public void removeTestMain(IList iList, int value, int[] expected) {
        iList.remove(value);
        int[] actual = iList.toArray();
        assertArrayEquals(expected, actual);
    }

    //============================= Remove by Index ======================================
    static Stream<Arguments> removeByIndexTest() {
        IList myList1 = new AListInt();
        myList1.add(1);
        myList1.add(2);
        myList1.add(3);
        myList1.add(3);
        myList1.add(4);
        myList1.add(5);
        myList1.add(-5);
        myList1.add(-90);

        return Stream.of(

                Arguments.arguments(myList1, 3, new int[]{1, 2, 3, 4, 5, -5, -90}),
                Arguments.arguments(myList1, 6, new int[]{1, 2, 3, 4, 5, -5}),
                Arguments.arguments(myList1, 3, new int[]{1, 2, 3, 5, -5}),
                Arguments.arguments(myList1, 0, new int[]{2, 3, 5, -5}),
                Arguments.arguments(myList1, 0, new int[]{3, 5, -5}),
                Arguments.arguments(myList1, 2, new int[]{3, 5}),
                Arguments.arguments(myList1, 1, new int[]{3}),
                Arguments.arguments(myList1, 0, new int[]{})
        );
    }

    @ParameterizedTest(name = "Remove index test. Data input: {0}, {1}, {2}")
    @MethodSource("removeByIndexTest")
    public void removeByIndexTestMain(IList iList, int index, int[] expected) {
        iList.removeByIndex(index);
        int[] actual = iList.toArray();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void removeTestExceptionsNegativeIndex() {
        IList myList = new AListInt();
        myList.add(1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            myList.removeByIndex(-5);
        }, ListConstants.INCORRECT_ARGUMENT);
    }

    @Test
    public void removeTestExceptionsExtraIndex() {
        IList myList = new AListInt();
        myList.add(1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            myList.removeByIndex(5);
        }, ListConstants.INCORRECT_ARGUMENT);
    }

    //================================ Contains ============================================
    static Stream<Arguments> containsTest() {
        IList myList1 = new AListInt();
        myList1.add(1);
        myList1.add(2);
        myList1.add(3);
        myList1.add(4);
        myList1.add(5);
        myList1.add(6);
        myList1.add(8);

        return Stream.of(

                Arguments.arguments(myList1, 0, false),
                Arguments.arguments(myList1, 1, true),
                Arguments.arguments(myList1, 2, true),
                Arguments.arguments(myList1, 3, true),
                Arguments.arguments(myList1, 4, true),
                Arguments.arguments(myList1, 5, true),
                Arguments.arguments(myList1, 6, true),
                Arguments.arguments(myList1, 7, false),
                Arguments.arguments(myList1, 10, false),
                Arguments.arguments(myList1, -7, false)
        );
    }

    @ParameterizedTest(name = "Contains test. Data input: {0}, {1}, {2}")
    @MethodSource("containsTest")
    public void containsTestMain(IList iList, int value, boolean expected) {
        boolean actual = iList.contains(value);
        assertEquals(expected, actual);
    }

    //==================================== Set =============================
    static Stream<Arguments> setTest() {
        IList myList1 = new AListInt();
        myList1.add(1);
        myList1.add(2);
        myList1.add(3);
        myList1.add(4);
        myList1.add(5);
        myList1.add(6);
        myList1.add(8);

        return Stream.of(
                Arguments.arguments(myList1, 1, 20, true),
                Arguments.arguments(myList1, 2, 30, true),
                Arguments.arguments(myList1, 3, 40, true),
                Arguments.arguments(myList1, 4, 50, true),
                Arguments.arguments(myList1, 5, 60, true)
        );
    }

    @ParameterizedTest(name = "Set test. Data input: {0}, {1}, {2}")
    @MethodSource("setTest")
    public void setTestMain(IList iList, int index, int value, boolean expected) {
        iList.set(index, value);
        boolean actual = iList.contains(value);
        assertEquals(expected, actual);
    }

    //================================= To array ================================================
    static Stream<Arguments> toArrayTest() {
        IList myList1 = new AListInt();
        IList myList2 = new AListInt();
        IList myList3 = new AListInt();
        IList myList4 = new AListInt();

        myList4.add(2);
        myList4.add(2);
        myList4.add(2);

        myList1.add(1);
        myList1.add(2);
        myList1.add(3);
        myList1.add(4);
        myList1.add(5);
        myList1.add(6);
        myList1.add(8);

        myList3.add(-9);
        myList3.add(0);
        myList3.add(-90);
        myList3.add(10);
        myList3.add(15);
        myList3.add(42);
        myList3.add(2);
        return Stream.of(
                Arguments.arguments(myList1, new int[]{1, 2, 3, 4, 5, 6, 8}),
                Arguments.arguments(myList2, new int[]{}),
                Arguments.arguments(myList4, new int[]{2, 2, 2}),
                Arguments.arguments(myList3, new int[]{-9, 0, -90, 10, 15, 42, 2})
        );
    }

    @ParameterizedTest(name = "To array test. Data input: {0}, {1}, {2}")
    @MethodSource("toArrayTest")
    public void toArrayTestMain(IList iList, int[] expected) {
        int[] actual = iList.toArray();
        assertArrayEquals(expected, actual);
    }

    //============================= Remove All ===========================================
    static Stream<Arguments> removeAllTest() {
        IList myList1 = new AListInt();
        myList1.add(1);
        myList1.add(2);
        myList1.add(3);
        myList1.add(4);
        myList1.add(5);
        myList1.add(6);
        myList1.add(7);
        myList1.add(8);

        IList myList2 = new AListInt();
        myList2.add(0);
        myList2.add(1);
        myList2.add(2);
        myList2.add(3);
        myList2.add(4);
        myList2.add(5);
        myList2.add(6);
        myList2.add(7);
        myList2.add(8);
        myList2.add(9);
        myList2.add(10);
        myList2.add(11);
        myList2.add(12);
        myList2.add(12);

        IList myList3 = new AListInt();
        myList3.add(0);
        myList3.add(1);
        myList3.add(2);
        myList3.add(3);

        IList myList4 = new AListInt();

        IList myList5 = new AListInt();
        myList5.add(1);
        myList5.add(-1);
        return Stream.of(
                Arguments.arguments(myList1, new int[]{1, 2, 5, 8}, new int[]{3, 4, 6, 7}, true),
                Arguments.arguments(myList2, new int[]{1, 3, 5, 8, 10, 12}, new int[]{0, 2, 4, 6, 7, 9, 11, 12}, true),
                Arguments.arguments(myList3, new int[]{1, 3, 5}, new int[]{0, 2}, true),
                Arguments.arguments(myList4, new int[]{}, new int[]{}, true),
                Arguments.arguments(myList5, null, new int[]{1,-1}, false)
        );
    }

    @ParameterizedTest(name = "Remove all test. Data input: {0}, {1}, {2}")
    @MethodSource("removeAllTest")
    public void removeAllTestMain(IList iList, int[] arr, int[] expected, boolean expectedb) {
        boolean actualb = iList.removeAll(arr);
        int[] actual = iList.toArray();
        assertEquals(expectedb, actualb);
        assertArrayEquals(expected, actual);
    }
}