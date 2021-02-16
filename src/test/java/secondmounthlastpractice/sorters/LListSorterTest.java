package secondmounthlastpractice.sorters;

import ilist.impl.llist.LList;
import ilist.interfaces.IList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LListSorterTest {
    private final LListSorter lListSorter;

    LListSorterTest() {
        lListSorter = new LListSorter();
    }

    static Stream<Arguments> lListSortedTest() {
        IList iList = new LList(new int[]{0});
        IList iList1 = new LList(new int[]{1});
        IList iList2 = new LList(new int[]{2, 1});
        IList iList3 = new LList(new int[]{-1, 2, 1});
        IList iList4 = new LList(new int[]{-10, 0, 20, 10});
        IList iList5 = new LList(new int[]{-100, -50, 0, 50, 100, 150, 200});
        IList iList6 = new LList(new int[]{7, 3, 9, -5, 0, 7, 8, 6, 1, -7, 3, 5, 9});
        return Stream.of(
                Arguments.arguments(iList, new int[]{0}),
                Arguments.arguments(iList1, new int[]{1}),
                Arguments.arguments(iList2, new int[]{1, 2}),
                Arguments.arguments(iList3, new int[]{-1, 1, 2}),
                Arguments.arguments(iList4, new int[]{-10, 0, 10, 20}),
                Arguments.arguments(iList4, new int[]{-10, 0, 10, 20}),
                Arguments.arguments(iList5, new int[]{-100, -50, 0, 50, 100, 150, 200}),
                Arguments.arguments(iList6, new int[]{-7, -5, 0, 1, 3, 3, 5, 6, 7, 7, 8, 9, 9})
        );
    }

    @ParameterizedTest(name = "List sorted. {1}")
    @MethodSource("lListSortedTest")
    void lListSortedTestMain(LList iList, int[] expected) {
        iList = lListSorter.lListSort(iList);
        int[] actual = iList.toArray();
        assertArrayEquals(expected, actual);
    }

    @Test
    void treeSortTestExceptionArrayIsNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            LList lList = lListSorter.lListSort(null);
        }, "Array is empty or null.");
    }

    @Test
    void treeSortTestExceptionArrayIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            LList lList = lListSorter.lListSort(new LList());
        }, "Array is empty or null.");
    }
}