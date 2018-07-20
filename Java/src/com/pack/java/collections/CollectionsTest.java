package com.pack.java.collections;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CollectionsTest {

    public static void main(String[] args) {
        CollectionsTest test = new CollectionsTest();
        String[] array = new String[] {"A","C","B","B","C","A","C","Z"};
        test.testLinkedHashSet(array);
        System.out.println();
        test.testHashSet(array);
        System.out.println();
        test.testLinkedList(array);
    }
    
    private void testLinkedHashSet(String[] array) {
        Set<String> linkedSet = Stream.of(array).collect(Collectors.toCollection(LinkedHashSet::new));
        linkedSet.forEach(System.out::println);
    }
    
    private void testHashSet(String[] array) {
        Set<String> linkedSet = Stream.of(array).collect(Collectors.toCollection(HashSet::new));
        linkedSet.forEach(System.out::println);
    }
    
    private void testLinkedList(String[] array) {
        List<String> linkedList = Stream.of(array).collect(Collectors.toCollection(LinkedList::new));
        ListIterator<String> iterator = linkedList.listIterator();
        while(iterator.hasPrevious()) {
            System.out.println(iterator.previous());
        }
        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        linkedList.forEach(System.out::println);
    }
}
