package ilist.impl.alist;

import ilist.IList;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class AListIntTest {
   private IList aListInt;
   AListIntTest(){
       aListInt = new AListInt();
   }

   @Test
    public void aListTest(){
       System.out.println(aListInt.size());
       aListInt.add(1);
       aListInt.add(2);
       aListInt.add(3);
       aListInt.add(4);
       aListInt.add(5);
       aListInt.add(6);
       aListInt.add(7);
       aListInt.add(8);
       aListInt.add(9);
       aListInt.add(10);
       aListInt.add(11);
       aListInt.add(0);
       aListInt.add(114);
       System.out.println(aListInt.size());
       aListInt.remove(1);
       aListInt.remove(114);
       aListInt.remove(0);
      System.out.println(aListInt.size());
       aListInt.add(1,99);
       aListInt.add(1,99);
       aListInt.add(10,99);
       aListInt.add(10,99);
       aListInt.add(5,99);
       aListInt.add(11,99);

       aListInt.print();
      System.out.println(aListInt.size());

   }
}