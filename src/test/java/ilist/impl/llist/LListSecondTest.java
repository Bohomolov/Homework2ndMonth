package ilist.impl.llist;

import org.junit.jupiter.api.Test;

class LListSecondTest {
    @Test
    void test() {
        LListDoubleList myList = new LListDoubleList();
        myList.add(1);
        myList.add(2);
        myList.add(3);
        myList.add(4);
        myList.add(5);
        myList.add(6);
        myList.add(1,7);
        System.out.println(myList.add(0, 8));

        myList.print();
    }
}