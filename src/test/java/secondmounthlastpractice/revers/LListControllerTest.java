package secondmounthlastpractice.revers;

import ilist.impl.llist.DoubleLList;
import ilist.impl.llist.LList;
import ilist.interfaces.IList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class LListControllerTest {
    private final LListController lListController;

    LListControllerTest() {
        lListController = new LListController();
    }

    static Stream<Arguments> lListReversTest() {
        LList lList = new LList(new int[]{0});
        LList lList1 = new LList(new int[]{1});
        LList lList2 = new LList(new int[]{2, 1});
        LList lList3 = new LList(new int[]{-1, 2, 1});
        LList lList4 = new LList(new int[]{1, 2, 3, 4, 5, 6});
        LList lList5 = new LList(new int[]{4, 5, 6, 1, 2, 3});
        return Stream.of(
                Arguments.arguments(lList, new int[]{0}),
                Arguments.arguments(lList1, new int[]{1}),
                Arguments.arguments(lList2, new int[]{1, 2}),
                Arguments.arguments(lList3, new int[]{1, 2, -1}),
                Arguments.arguments(lList4, new int[]{6, 5, 4, 3, 2, 1}),
                Arguments.arguments(lList4, new int[]{1, 2, 3, 4, 5, 6}),
                Arguments.arguments(lList5, new int[]{3, 2, 1, 6, 5, 4})
        );
    }

    @ParameterizedTest(name = "Revers lList")
    @MethodSource("lListReversTest")
    void lListReversTestMain(IList lList, int[] expected) {
        lList = lListController.reversLList(lList);
        int[] actual = lList.toArray();
        assertArrayEquals(expected, actual);
    }

    static Stream<Arguments> doubleListReversTest() {
        DoubleLList lList = new DoubleLList(new int[]{0});
        DoubleLList lList1 = new DoubleLList(new int[]{1});
        DoubleLList lList2 = new DoubleLList(new int[]{2, 1});
        DoubleLList lList3 = new DoubleLList(new int[]{-1, 2, 1});
        DoubleLList lList4 = new DoubleLList(new int[]{1, 2, 3, 4, 5, 6});
        DoubleLList lList5 = new DoubleLList(new int[]{4, 5, 6, 1, 2, 3});
        return Stream.of(
                Arguments.arguments(lList, new int[]{0}),
                Arguments.arguments(lList1, new int[]{1}),
                Arguments.arguments(lList2, new int[]{1, 2}),
                Arguments.arguments(lList3, new int[]{1, 2, -1}),
                Arguments.arguments(lList4, new int[]{6, 5, 4, 3, 2, 1}),
                Arguments.arguments(lList4, new int[]{1, 2, 3, 4, 5, 6}),
                Arguments.arguments(lList5, new int[]{3, 2, 1, 6, 5, 4})
        );
    }

    @ParameterizedTest(name = "Revers doubleList")
    @MethodSource("doubleListReversTest")
    void doubleListReversTestMain(IList lList, int[] expected) {
        lList = lListController.reversLList(lList);
        int[] actual = lList.toArray();
        assertArrayEquals(expected, actual);
    }
    @Test
    void treeSortTestExceptionArrayIsNull() {

        assertThrows(IllegalArgumentException.class, () -> {
            lListController.reversLList(null);
        }, "Array is empty or null.");
    }

    @Test
    void treeSortTestExceptionArrayIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> {
            lListController.reversLList(new LList());
        }, "Array is empty or null.");
    }
}