package com.pack.java.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListIteratorTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        List<Integer> intList = new ArrayList<>();
        for (int count = 10; count < 20; count++) {
            intList.add(count);
        }
        testNormalIterator(intList);
        testListIterator(intList);
    }

    private static void testListIterator(List<Integer> intList) {
        Iterator<Integer> iterator = intList.iterator();
        int value;
        while (iterator.hasNext()) {
            value = iterator.next();
            if (value == 10 || value == 20) {
                iterator.remove();
            }
        }
        
        System.out.println( intList.toString() );
    }

    private static void testNormalIterator(List<Integer> intList) {

    }

}
