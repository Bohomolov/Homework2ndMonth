package ilist.impl.alist.generics;

import ilist.constantslist.ListConstants;
import ilist.interfaces.IListGenerics;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

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
        AListGenerics<Person> myList = new AListGenerics<Person>();

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
    public void sizeTestMain(IListGenerics<Person> iList, int expected) {
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
        myList.add(person6);

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
                Arguments.arguments(myList2, 0, null),
                Arguments.arguments(myList3, 2, person0),
                Arguments.arguments(myList4, 0, person5),
                Arguments.arguments(myList4, 0, person5)
        );
    }

    @ParameterizedTest(name = "Get test. Data input: {0}, {1}")
    @MethodSource("getTest")
    public void getTestMain(IListGenerics<Person> iList, int index, Object expected) {
        Object actual = iList.get(index);
        assertEquals(expected, actual);
    }

    @Test
    public void getTestExceptionsNegativeIndex() {
        IListGenerics<Person> myList = new AListGenerics<>();
        myList.add(new Person(1, "sssw"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            myList.get(-1);
        }, ListConstants.INCORRECT_ARGUMENT);
    }

    @Test
    public void getTestExceptionsExtraIndex() {
        IListGenerics<Person> myList = new AListGenerics<>();
        myList.add(new Person(1, "sssw"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            myList.get(5);
        }, ListConstants.INCORRECT_ARGUMENT);
    }

    //===========================================ADD=====================
    static Stream<Arguments> addTest() {
        Person person0 = new Person(0, "Xz000");
        Person person1 = new Person(1, "Xz");
        Person person2 = new Person(2, "XX");
        Person person3 = new Person(3, "XXx");
        Person person4 = new Person(4, "XXxEE");
        Person person5 = new Person(5, "XXxEE");
        Person person6 = new Person(6, "XXxEE");
        IListGenerics<Person> myList1 = new AListGenerics<>();

        myList1.add(person0);
        myList1.add(person1);


        return Stream.of(
                Arguments.arguments(myList1, person2, true, new Person[]{person0, person1, person2}),
                Arguments.arguments(myList1, person3, true, new Person[]{person0, person1, person2, person3}),
                Arguments.arguments(myList1, person4, true, new Person[]{person0, person1, person2, person3, person4}),
                Arguments.arguments(myList1, person5, true, new Person[]{person0, person1, person2, person3, person4, person5}),
                Arguments.arguments(myList1, person6, true, new Person[]{person0, person1, person2, person3, person4, person5, person6})

        );
    }

    @ParameterizedTest(name = "Add test. Data input: {0}, {1} , {2}")
    @MethodSource("addTest")
    public void addTestMain(IListGenerics<Person> iList, Person value, boolean expected, Person[] expectedArray) {
        boolean actual = iList.add(value);
        assertEquals(expected, actual);
        Object[] actualArray = iList.toArray();
        assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    public void addNullExceptions() {
        IListGenerics<Person> myList = new AListGenerics<>();
        myList.add(new Person(2, "Petya"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            myList.add(null);
        }, ListConstants.INCORRECT_ARGUMENT);
    }

    //============================ Add index =======================
    static Stream<Arguments> addIndexTest() {
        Person person0 = new Person(0, "Xz000");
        Person person1 = new Person(1, "Xz");
        Person person2 = new Person(2, "XX");
        Person person3 = new Person(3, "XXx");
        Person person4 = new Person(4, "XXxEE");
        Person person5 = new Person(5, "XXxEE");
        Person person6 = new Person(6, "XXxEE");
        IListGenerics<Person> myList1 = new AListGenerics<>();

        myList1.add(person0);
        myList1.add(person1);

        return Stream.of(
                Arguments.arguments(myList1, 1, person2, true, new Person[]{person0, person2, person1}),
                Arguments.arguments(myList1, 0, person3, true, new Person[]{person3, person0, person2, person1}),
                Arguments.arguments(myList1, 4, person4, true, new Person[]{person3, person0, person2, person1, person4}),
                Arguments.arguments(myList1, 4, person5, true, new Person[]{person3, person0, person2, person1, person5, person4}),
                Arguments.arguments(myList1, 2, person6, true, new Person[]{person3, person0, person6, person2, person1, person5, person4})
        );
    }

    @ParameterizedTest(name = "Add index test. Data input: {0}, {1}, {2}, {3}")
    @MethodSource("addIndexTest")
    public void addIndexTestMain(IListGenerics<Person> iList, int index, Person value, boolean expected, Person[] expectedArray) {
        boolean actual = iList.add(index, value);
        assertEquals(expected, actual);
        Object[] actualArray = iList.toArray();
        assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    public void addByIndexNullExceptions() {
        IListGenerics<Person> myList = new AListGenerics<>();
        myList.add(new Person(2, "Petya"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            myList.add(1, null);
        }, ListConstants.INCORRECT_ARGUMENT);
    }

    @Test
    public void addByIndexExceptionsNegativeIndex() {
        IListGenerics<Person> myList = new AListGenerics<>();
        myList.add(new Person(2, "Petya"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            myList.add(-1, new Person(2, "Vasya"));
        }, ListConstants.INCORRECT_ARGUMENT);
    }

    @Test
    public void addTestExceptionsExtraIndex() {
        IListGenerics<Person> myList = new AListGenerics<>();
        myList.add(new Person(2, "Petya"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            myList.add(5, new Person(2, "Vasya"));
        }, ListConstants.INCORRECT_ARGUMENT);
    }

    //============================ Remove =========================
    static Stream<Arguments> removeTest() {
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

        return Stream.of(
                Arguments.arguments(myList, person0, new Person[]{person1, person2, person3, person4, person5, person6}),
                Arguments.arguments(myList, person3, new Person[]{person1, person2, person4, person5, person6}),
                Arguments.arguments(myList, person6, new Person[]{person1, person2, person4, person5}),
                Arguments.arguments(myList, person5, new Person[]{person1, person2, person4}),
                Arguments.arguments(myList, person2, new Person[]{person1, person4}),
                Arguments.arguments(myList, person4, new Person[]{person1}),
                Arguments.arguments(myList, person1, new Person[]{})
        );
    }

    @ParameterizedTest(name = "Remove index test. Data input: {0}, {1}, {2}")
    @MethodSource("removeTest")
    public void removeTestMain(IListGenerics<Person> iList, Person value, Person[] expected) {
        iList.remove(value);
        Object[] actual = iList.toArray();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void removeNullExceptions() {
        IListGenerics<Person> myList = new AListGenerics<>();
        myList.add(new Person(2, "Petya"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            myList.remove(null);
        }, ListConstants.INCORRECT_ARGUMENT);
    }

    @Test
    public void removeValueNotFoundExceptions() {
        IListGenerics<Person> myList = new AListGenerics<>();
        myList.add(new Person(2, "Petya"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            myList.remove(new Person(3, "Grisha"));
        }, ListConstants.INCORRECT_ARGUMENT);
    }

    //============================= Remove by Index ======================================
    static Stream<Arguments> removeByIndexTest() {
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

        return Stream.of(
                Arguments.arguments(myList, 0, new Person[]{person1, person2, person3, person4, person5, person6}),
                Arguments.arguments(myList, 2, new Person[]{person1, person2, person4, person5, person6}),
                Arguments.arguments(myList, 4, new Person[]{person1, person2, person4, person5}),
                Arguments.arguments(myList, 3, new Person[]{person1, person2, person4}),
                Arguments.arguments(myList, 1, new Person[]{person1, person4}),
                Arguments.arguments(myList, 1, new Person[]{person1}),
                Arguments.arguments(myList, 0, new Person[]{})

        );
    }

    @ParameterizedTest(name = "Remove index test. Data input: {0}, {1}, {2}")
    @MethodSource("removeByIndexTest")
    public void removeByIndexTestMain(IListGenerics<Person> iList, int index, Person[] expected) {
        iList.removeByIndex(index);
        Object[] actual = iList.toArray();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void removeByIdTestExceptionsNegativeIndex() {
        IListGenerics<Person> myList = new AListGenerics<>();
        myList.add(new Person(2, "Petya"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            myList.removeByIndex(-5);
        }, ListConstants.INCORRECT_ARGUMENT);
    }

    @Test
    public void removeByIdTestExceptionsExtraIndex() {
        IListGenerics<Person> myList = new AListGenerics<>();
        myList.add(new Person(2, "Petya"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            myList.removeByIndex(5);
        }, ListConstants.INCORRECT_ARGUMENT);
    }

    @Test
    public void removeByIndexValueNotFoundExceptions() {
        IListGenerics<Person> myList = new AListGenerics<>();
        myList.add(new Person(2, "Petya"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            myList.removeByIndex(3);
        }, ListConstants.INCORRECT_ARGUMENT);
    }


    //================================ Contains ============================================
    static Stream<Arguments> containsTest() {
        Person person0 = new Person(0, "Xz000");
        Person person1 = new Person(1, "Xz");
        Person person2 = new Person(2, "XX");
        Person person3 = new Person(3, "XXx");
        Person person4 = new Person(4, "XXxEE");
        Person person5 = new Person(5, "XXxEE");
        Person person6 = new Person(6, "XXxEE");
        Person person7 = new Person(9, "XXxE88E");
        Person person8 = new Person(8, "XXx888E");
        Person person9 = new Person(89, "XXx888E");
        IListGenerics<Person> myList = new AListGenerics<>();

        myList.add(person0);
        myList.add(person1);
        myList.add(person2);
        myList.add(person3);
        myList.add(person4);
        myList.add(person5);
        myList.add(person6);


        return Stream.of(

                Arguments.arguments(myList, person7, false),
                Arguments.arguments(myList, person0, true),
                Arguments.arguments(myList, person1, true),
                Arguments.arguments(myList, person3, true),
                Arguments.arguments(myList, person2, true),
                Arguments.arguments(myList, person4, true),
                Arguments.arguments(myList, person8, false),
                Arguments.arguments(myList, person9, false)

        );
    }

    @ParameterizedTest(name = "Contains test. Data input: {0}, {1}, {2}")
    @MethodSource("containsTest")
    public void containsTestMain(IListGenerics<Person> iList, Person value, boolean expected) {
        boolean actual = iList.contains(value);
        assertEquals(expected, actual);
    }

    //==================================== Set =============================
    static Stream<Arguments> setTest() {
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

        return Stream.of(
                Arguments.arguments(myList, 1, new Person(10, "POz"), true),
                Arguments.arguments(myList, 2, new Person(-10, "POz"), true),
                Arguments.arguments(myList, 3, new Person(0, "POzI"), true),
                Arguments.arguments(myList, 4, new Person(666, " "), true),
                Arguments.arguments(myList, 5, new Person(26, "(x_x)"), true)
        );
    }

    @ParameterizedTest(name = "Set test. Data input: {0}, {1}, {2}")
    @MethodSource("setTest")
    public void setTestMain(IListGenerics<Person> iList, int index, Person value, boolean expected) {
        iList.set(index, value);
        boolean actual = iList.contains(value);
        assertEquals(expected, actual);
    }

    @Test
    public void setExceptionsExtraIndex() {
        IListGenerics<Person> myList = new AListGenerics<>();
        myList.add(new Person(2, "Petya"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            myList.set(5, new Person(3, "Cenya"));
        }, ListConstants.INCORRECT_ARGUMENT);
    }

    @Test
    public void setValueNotFoundExceptions() {
        IListGenerics<Person> myList = new AListGenerics<>();
        myList.add(new Person(2, "Petya"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            myList.set(-4, new Person(3, "Cenya"));
        }, ListConstants.INCORRECT_ARGUMENT);
    }

    //================================= To array ================================================
    static Stream<Arguments> toArrayTest() {
        Person person0 = new Person(0, "Xz000");
        Person person1 = new Person(1, "Xz");
        Person person2 = new Person(2, "XX");
        Person person3 = new Person(3, "XXx");
        Person person4 = new Person(4, "XXxEE");
        Person person5 = new Person(5, "XXxEE");
        Person person6 = new Person(6, "XXxEE");
        IListGenerics<Person> myList = new AListGenerics<>();
        IListGenerics<Person> myList2 = new AListGenerics<>();
        IListGenerics<Person> myList3 = new AListGenerics<>();

        myList.add(person0);
        myList.add(person1);
        myList.add(person2);
        myList.add(person3);
        myList.add(person4);
        myList.add(person5);
        myList.add(person6);

        myList2.add(person6);
        myList2.add(person5);

        return Stream.of(
                Arguments.arguments(myList, new Person[]{person0, person1, person2, person3, person4, person5, person6}),
                Arguments.arguments(myList3, new Person[]{}),
                Arguments.arguments(myList2, new Person[]{person6, person5})
        );
    }

    @ParameterizedTest(name = "To array test. Data input: {0}, {1}, {2}")
    @MethodSource("toArrayTest")
    public void toArrayTestMain(IListGenerics<Person> iList, Person[] expected) {
        Object[] actual = iList.toArray();
        assertArrayEquals(expected, actual);
    }

    //============================= Remove All ===========================================
    static Stream<Arguments> removeAllTest() {
        Person person0 = new Person(0, "Xz000");
        Person person1 = new Person(1, "Xz");
        Person person2 = new Person(2, "XX");
        Person person3 = new Person(3, "XXx");
        Person person4 = new Person(4, "XXxEE");
        Person person5 = new Person(5, "XXxEE");
        Person person6 = new Person(6, "XXxEE");

        IListGenerics<Person> myList = new AListGenerics<>();
        IListGenerics<Person> myList2 = new AListGenerics<>();

        myList.add(person0);
        myList.add(person1);
        myList.add(person2);
        myList.add(person3);
        myList.add(person4);
        myList.add(person5);
        myList.add(person6);

        myList2.add(person6);
        myList2.add(person6);
        myList2.add(person6);
        myList2.add(person6);

        return Stream.of(
                Arguments.arguments(myList, new Person[]{person0, person3, person5}, new Person[]{person1, person2, person4, person6}),
                Arguments.arguments(myList2, new Person[]{person6, person6, person6}, new Person[]{person6})
        );
    }

    @ParameterizedTest(name = "Remove all test. Data input: {0}, {1}, {2}")
    @MethodSource("removeAllTest")
    public void removeAllTestMain(IListGenerics<Person> iList, Person[] arr, Person[] expected) {
        iList.removeAll(arr);
        Object[] actual = iList.toArray();
        assertArrayEquals(expected, actual);
    }

    @Test
    public void removeAllTestExceptionsNull() {
        IListGenerics<Person> myList = new AListGenerics<>();
        myList.add(new Person(2, "Petya"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            myList.removeAll(null);
        }, ListConstants.INCORRECT_ARGUMENT);
    }

    @Test
    public void removeAllNullElementTestExceptions() {
        IListGenerics<Person> myList = new AListGenerics<>();
        myList.add(new Person(2, "Petya"));
        myList.add(new Person(3, "IIIII"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            myList.removeAll(new Person[]{new Person(2, "Petya"), null});
        }, ListConstants.INCORRECT_ARGUMENT);
    }

}