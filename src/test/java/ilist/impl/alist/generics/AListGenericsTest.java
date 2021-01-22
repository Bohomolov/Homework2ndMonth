package ilist.impl.alist.generics;

import ilist.constantslist.ListConstants;
import ilist.impl.alist.AListInt;
import ilist.interfaces.IList;
import ilist.interfaces.IListGenerics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class AListGenericsTest {
    private static class Person {
        private final int id;
        private final String name;

        public Person(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Person{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return id == person.id &&
                    Objects.equals(name, person.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, name);
        }
    }

    @Test
    public void test() {
        Person person0 = new Person(0, "Xz000");
        Person person1 = new Person(1, "Xz");
        Person person2 = new Person(2, "XX");
        Person person3 = new Person(3, "XXx");
        Person person4 = new Person(4, "XXxEE");
        Person person5 = new Person(5, "XXxEE");
        Person person6 = new Person(6, "XXxEE");
    }

    static Stream<Arguments> clearTest() {

        return Stream.of(
                Arguments.arguments(0)
        );
    }

    @ParameterizedTest(name = "Clear test. Data input: {0}, {1}")
    @MethodSource("clearTest")
    public void clearTestMain(int expected) {
        Person person0 = new Person(0, "Xz000");
        Person person1 = new Person(1, "Xz");
        Person person2 = new Person(2, "XX");
        Person person3 = new Person(3, "XXx");
        Person person4 = new Person(4, "XXxEE");
        Person person5 = new Person(5, "XXxEE");
        Person person6 = new Person(6, "XXxEE");
        IListGenerics<Person> myList = new AListGenerics<>();
        myList.add(person0);
        myList.add(person1);
        myList.add(person2);
        myList.add(person3);
        myList.add(person4);
        myList.add(person5);
        myList.add(person6);
        myList.clear();
        int actual = myList.size();
        assertEquals(expected, actual);
    }

    //============================= Size ========================
    static Stream<Arguments> sizeTest() {
        Person person0 = new Person(0, "Xz000");
        Person person1 = new Person(1, "Xz");
        Person person2 = new Person(2, "XX");
        Person person3 = new Person(3, "XXx");
        Person person4 = new Person(4, "XXxEE");
        Person person5 = new Person(5, "XXxEE");
        Person person6 = new Person(6, "XXxEE");
        IListGenerics<Person> myList = new AListGenerics<>();

        myList.add(person0);
        myList.add(person1);
        myList.add(person2);
        myList.add(person3);
        myList.add(person4);
        myList.add(person5);
        myList.add(person6);

        IListGenerics<Person> myList2 = new AListGenerics<>();

        myList2.add(person0);
        myList2.add(person0);
        myList2.add(person0);
        myList2.add(person0);
        myList2.add(person0);

        IListGenerics<Person> myList3 = new AListGenerics<>();

        IListGenerics<Person> myList4 = new AListGenerics<>();
        myList4.add(person0);


        return Stream.of(
                Arguments.arguments(myList, 7),
                Arguments.arguments(myList2, 5),
                Arguments.arguments(myList3, 0),
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
        Person person0 = new Person(0, "Xz000");
        Person person1 = new Person(1, "Xz");
        Person person2 = new Person(2, "XX");
        Person person3 = new Person(3, "XXx");
        Person person4 = new Person(4, "XXxEE");
        Person person5 = new Person(5, "XXxEE");
        Person person6 = new Person(6, "XXxEE");
        IListGenerics<Person> myList = new AListGenerics<>();

        myList.add(person0);
        myList.add(person1);
        myList.add(person2);
        myList.add(person3);
        myList.add(person4);
        myList.add(person4);
        myList.add(person5);

        IListGenerics<Person> myList2 = new AListGenerics<>();

        IListGenerics<Person> myList3 = new AListGenerics<>();
        myList3.add(person0);
        myList3.add(person0);
        myList3.add(person0);

        IListGenerics<Person> myList4 = new AListGenerics<>();
        myList4.add(person5);
        return Stream.of(
                Arguments.arguments(myList, 6, person5),
                Arguments.arguments(myList, 4, person4),
                Arguments.arguments(myList, 5, person4),
                Arguments.arguments(myList2, 0, 0),
                Arguments.arguments(myList3, 2, person0),
                Arguments.arguments(myList4, 0, person5)
        );
    }

    @ParameterizedTest(name = "Get test. Data input: {0}, {1}")
    @MethodSource("getTest")
    public void getTestMain(IList iList, int index, Object expected) {
        Object actual = iList.get(index);
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
        IList myList2 = new AListInt();
        IList myList3 = new AListInt();
        IList myList4 = new AListInt();

        return Stream.of(
                Arguments.arguments(myList1, 1, true),
                Arguments.arguments(myList2, 2, true),
                Arguments.arguments(myList3, 3, true),
                Arguments.arguments(myList4, 4, true)
        );
    }

    @ParameterizedTest(name = "Add test. Data input: {0}, {1} , {2}")
    @MethodSource("addTest")
    public void addTestMain(IList iList, int value, boolean expected) {
        boolean actual = iList.add(value);
        int actualInt = iList.remove(value);
        int expectedInt = value;
        assertEquals(expected, actual);
        assertEquals(expectedInt, actualInt);
    }

    //============================ Add index =======================
    static Stream<Arguments> addIndexTest() {
        IList myList1 = new AListInt();
        myList1.add(1);
        IList myList2 = new AListInt();
        myList2.add(0);
        myList2.add(1);
        myList2.add(2);
        myList2.add(3);
        myList2.add(4);
        myList2.add(5);

        return Stream.of(
                Arguments.arguments(myList1, 0, 1, true),
                Arguments.arguments(myList1, 1, 2, true),
                Arguments.arguments(myList2, 5, 3, true),
                Arguments.arguments(myList2, 3, 4, true)
        );
    }

    @ParameterizedTest(name = "Add index test. Data input: {0}, {1}, {2}, {3}")
    @MethodSource("addIndexTest")
    public void addIndexTestMain(IList iList, int index, int value, boolean expected) {
        boolean actual = iList.add(index, value);
        int actualInt = iList.get(index);
        int expectedInt = value;
        assertEquals(expected, actual);
        assertEquals(expectedInt, actualInt);
    }

    @Test
    public void addByIndexExceptionsNegativeIndex() {
        IList myList = new AListInt();
        myList.add(1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            myList.add(-1, 6);
        }, ListConstants.INCORRECT_ARGUMENT);
    }

    @Test
    public void addTestExceptionsExtraIndex() {
        IList myList = new AListInt();
        myList.add(1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            myList.add(5, 6);
        }, ListConstants.INCORRECT_ARGUMENT);
    }

    //============================ Remove =========================
    static Stream<Arguments> removeTest() {
        IList myList1 = new AListInt();
        myList1.add(1);
        myList1.add(2);
        myList1.add(3);
        myList1.add(3);
        IList myList2 = new AListInt();
        myList2.add(0);
        myList2.add(1);
        myList2.add(2);
        myList2.add(3);
        myList2.add(4);
        myList2.add(5);

        return Stream.of(
                Arguments.arguments(myList1, 1, 1),
                Arguments.arguments(myList1, 3, 3),
                Arguments.arguments(myList1, 3, 3),
                Arguments.arguments(myList1, 3, 0),
                Arguments.arguments(myList2, 5, 5),
                Arguments.arguments(myList2, 3, 3),
                Arguments.arguments(myList2, 0, 0),
                Arguments.arguments(myList2, 1, 1),
                Arguments.arguments(myList2, 1, 0)
        );
    }

    @ParameterizedTest(name = "Remove index test. Data input: {0}, {1}, {2}, {3}")
    @MethodSource("removeTest")
    public void removeTestMain(IList iList, int value, int expected) {
        int actual = iList.remove(value);
        assertEquals(expected, actual);
    }

    //============================= Remove by Index ======================================
    static Stream<Arguments> removeByIndexTest() {
        IList myList1 = new AListInt();
        myList1.add(1);
        myList1.add(2);
        myList1.add(3);
        myList1.add(3);
        IList myList2 = new AListInt();
        myList2.add(0);
        myList2.add(1);
        myList2.add(2);
        myList2.add(3);
        myList2.add(4);
        myList2.add(5);
        IList myList3 = new AListInt();
        myList3.add(0);
        myList3.add(10);
        myList3.add(20);
        myList3.add(30);
        myList3.add(40);
        myList3.add(50);

        return Stream.of(

                Arguments.arguments(myList1, 3, 3),
                Arguments.arguments(myList1, 2, 3),
                Arguments.arguments(myList1, 1, 2),
                Arguments.arguments(myList1, 0, 1),
                Arguments.arguments(myList2, 0, 0),
                Arguments.arguments(myList2, 0, 1),
                Arguments.arguments(myList2, 0, 2),
                Arguments.arguments(myList2, 0, 3),
                Arguments.arguments(myList2, 0, 4),
                Arguments.arguments(myList3, 3, 30),
                Arguments.arguments(myList3, 1, 10),
                Arguments.arguments(myList3, 3, 50)

        );
    }

    @ParameterizedTest(name = "Remove index test. Data input: {0}, {1}, {2}")
    @MethodSource("removeByIndexTest")
    public void removeByIndexTestMain(IList iList, int index, int expected) {
        int actual = iList.removeByIndex(index);
        assertEquals(expected, actual);
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

    @Test
    public void setTestExceptionsNegativeIndex() {
        IList myList = new AListInt();
        myList.add(1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            myList.set(-5, 5);
        }, ListConstants.INCORRECT_ARGUMENT);
    }

    @Test
    public void setTestExceptionsExtraIndex() {
        IList myList = new AListInt();
        myList.add(1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            myList.set(5, 5);
        }, ListConstants.INCORRECT_ARGUMENT);
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
        myList3.add(0);
        return Stream.of(
                Arguments.arguments(myList1, new int[]{1, 2, 5, 8}, new int[]{3, 4, 6}),
                Arguments.arguments(myList2, new int[]{}, new int[]{}),
                Arguments.arguments(myList4, new int[]{2, 2, 2}, new int[]{}),
                Arguments.arguments(myList3, new int[]{0, 0, 10, -90}, new int[]{-9, 15, 42})
        );
    }

    @ParameterizedTest(name = "Remove all test. Data input: {0}, {1}, {2}")
    @MethodSource("removeAllTest")
    public void removeAllTestMain(IList iList, int[] arr, int[] expected) {
        iList.removeAll(arr);
        int[] actual = iList.toArray();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void removeAllTestExceptionsNull() {
        IList myList = new AListInt();
        myList.add(1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            myList.removeAll(null);
        }, ListConstants.INCORRECT_ARGUMENT);
    }

}