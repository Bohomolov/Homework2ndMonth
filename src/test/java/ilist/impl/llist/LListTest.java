package ilist.impl.llist;

import ilist.interfaces.IList;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LListTest {
    @Test
    void test(){
        IList myList = new LList();
        myList.add(5);
        myList.add(2);
        myList.add(3);
        myList.add(1);
        myList.add(2);
        myList.add(4);
        myList.add(4);
        myList.add(7);
        myList.add(7);
        myList.add(7);
        System.out.println(myList.get(0));
        System.out.println(myList.get(3));
        System.out.println(myList.get(myList.size() - 1));
        System.out.println(myList.get(myList.size()));
        System.out.println(myList.size());
        myList.set(0,0);
        myList.set(3,3);
        myList.set(9,9);
        myList.set(8,8);
        System.out.println(myList.contains(10));
        System.out.println(myList.contains(1));
        System.out.println(myList.contains(0));
        System.out.println(myList.contains(3));


        myList.print();
       int[] o = myList.toArray();
        for (int i: o) {
            System.out.print( i + " ");
        }
    }
}