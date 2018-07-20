package com.pack.java.collections;

import java.util.HashMap;
import java.util.Map;

public class RetainRemoveTest {

    public static void main(String[] args) {
        Map<Integer, Integer> mgrMap = new HashMap<>();
        mgrMap.put(1, 10);
        mgrMap.put(2, 20);
        
        Map<Integer, Integer> empMap = new HashMap<>();
        empMap.put(3, 30);
        empMap.put(4, 40);
        empMap.put(1, 10);
        mgrMap.put(2, 20);
        
        Map<Integer, Integer> icMap = new HashMap<>(empMap);
        icMap.keySet().removeAll(mgrMap.keySet());
        
        System.out.println(icMap);
    }
}
