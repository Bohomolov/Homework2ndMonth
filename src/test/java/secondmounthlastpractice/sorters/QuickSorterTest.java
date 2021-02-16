package secondmounthlastpractice.sorters;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import secondmounthlastpractice.sorters.QuickSorter;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class QuickSorterTest {
    private final QuickSorter quickSorter;

    QuickSorterTest() {
        quickSorter = new QuickSorter();
    }

    static Stream<Arguments> quickSortTest() {
        return Stream.of(
                Arguments.arguments(new int[]{0}, new int[]{0}),
                Arguments.arguments(new int[]{1}, new int[]{1}),
                Arguments.arguments(new int[]{2, 1}, new int[]{1, 2}),
                Arguments.arguments(new int[]{2, 0, 1, -1}, new int[]{-1, 0, 1, 2}),
                Arguments.arguments(new int[]{-100, -75, -55, -25, -10, 0}, new int[]{-100, -75, -55, -25, -10, 0}),
                Arguments.arguments(new int[]{0, 100, 200, 300, 400, 500}, new int[]{0, 100, 200, 300, 400, 500}),
                Arguments.arguments(new int[]{-100, -75, -55, -25, -10, 0, 100, 200, 300, 400, 500}, new int[]{-100, -75, -55, -25, -10, 0, 100, 200, 300, 400, 500}),
                Arguments.arguments(new int[]{400, 300, -55, -25, -100, 100, -10, -75, 200, 500, 0}, new int[]{-100, -75, -55, -25, -10, 0, 100, 200, 300, 400, 500})
        );
    }

    @ParameterizedTest(name = "Quick sort. Input data {0},{1}")
    @MethodSource("quickSortTest")
    void quickSortTestMain(int[] actualArray, int[] expectedArray) {
        quickSorter.quickSort(actualArray);
        assertArrayEquals(expectedArray, actualArray);
    }

    @Test
    void quickSortTestExceptionArrayIsNull() {
        assertThrows(IllegalArgumentException.class,()->{
            quickSorter.quickSort(null);
        },"Array is empty or null.");
    }
    @Test
    void quickSortTestExceptionArrayIsEmpty() {
        assertThrows(IllegalArgumentException.class,()->{
            quickSorter.quickSort(new int[]{});
        }, "Array is empty or null.");
    }
}