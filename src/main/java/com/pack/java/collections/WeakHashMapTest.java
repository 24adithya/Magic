package com.pack.java.collections;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.WeakHashMap;

public class WeakHashMapTest {

    public static void main(String[] args) {
        WeakHashMapTest test = new WeakHashMapTest();

        Reference adamsReference = test.new Reference("Adams");
        Reference abstractReference = test.new Reference("Abstract");

        SoftReference<Reference> softReference = new SoftReference<>(adamsReference);
        WeakReference<Reference> weakReference = new WeakReference<>(abstractReference);

        Map<Reference, String> weakMap = new WeakHashMap<>();
        weakMap.put(abstractReference, "Abstract");
        weakMap.put(adamsReference, "Adams");

        adamsReference = null;
        abstractReference = null;
        
        test.testWeakReferences(weakMap);
        test.testSoftReferences(weakMap);
    }

    private void testSoftReferences(Map<Reference, String> weakMap) {
        // TODO Auto-generated method stub
        
    }

    private void testWeakReferences(Map<Reference, String> weakMap) {
        int count = 1000000;
        for (; count >= 0; count--) {
            /*if (map.get(abstractReference) == null) {
                System.out.println("abstract reference removed");
            }

            if (map.get(adamsReference) == null) {
                System.out.println("adams reference removed");
            }*/

            if (weakMap.isEmpty()) {
                System.out.println("Map is finally empty");
                break;
            } else {
                System.out.println("Iteration " + count + " - map.size = " + weakMap.size());
            }
        }
        
        if(count <= 0) {//Map is still not empty!!
            System.out.println(weakMap);
        }
    }

    private class Reference {
        String value;

        public Reference(String value) {
            this.value = value;
        }
    }
}
