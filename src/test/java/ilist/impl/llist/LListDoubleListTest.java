package ilist.impl.llist;

import org.junit.jupiter.api.Test;


class LListDoubleListTest {
    @Test
    void test(){
        LListDoubleList myList = new LListDoubleList();
        myList.add(0,5);
        myList.add(1,6);
        myList.add(7);
        myList.add(8);
        myList.add(4,9);
        myList.add(5,10);

        System.out.println(myList.contains(10));
        System.out.println(myList.contains(11));
        System.out.println(myList.contains(5));
        System.out.println(myList.contains(7));

        System.out.println(myList.getRootValue());
        System.out.println(myList.getCrownValue());

        myList.print();
    }
}