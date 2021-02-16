package secondmounthlastpractice.sorters;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class TreeSorterTest {
    private final TreeSorter treeSorter;

    TreeSorterTest() {
        treeSorter = new TreeSorter();
    }

    static Stream<Arguments> treeSortedTest() {
        return Stream.of(
                Arguments.arguments(new int[]{0}, new int[]{0}),
                Arguments.arguments(new int[]{1}, new int[]{1}),
                Arguments.arguments(new int[]{2, 1}, new int[]{1, 2}),
                Arguments.arguments(new int[]{1, 2, -1}, new int[]{-1, 1, 2}),
                Arguments.arguments(new int[]{-10, 0, 10, 101, 102, 598}, new int[]{-10, 0, 10, 101, 102, 598}),
                Arguments.arguments(new int[]{102, -10, 598, 0, 10, 101}, new int[]{-10, 0, 10, 101, 102, 598}),
                Arguments.arguments(new int[]{5, 4, 6, 3}, new int[]{3, 4, 5, 6})
        );
    }

    @ParameterizedTest(name = "Tree sorted. Input data {0},{1}")
    @MethodSource("treeSortedTest")
    void treeSortedTestMain(int[] array, int[] expectedArray) {
        int[] actualArray = treeSorter.treeSort(array);
        assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    void treeSortTestExceptionArrayIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            int[] arr = treeSorter.treeSort(null);
        }, "Array is empty or null.");
    }

    @Test
    void treeSortTestExceptionArrayIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            int[] arr = treeSorter.treeSort(new int[]{});
        }, "Array is empty or null.");
    }
}