package com.pack.java.functional;

public class DefaultInterfaceTest implements InterfaceA, InterfaceB {

}

class InterfaceImplementor implements InterfaceA11, InterfaceA12 {
    
}

interface InterfaceA {
    default void print() {
        System.out.println("InterfaceA");
    }
}

interface InterfaceB {
    default void print() {
        System.out.println("InterfaceA");
    }
}

interface InterfaceA11 extends InterfaceA {
    
}

interface InterfaceA12 extends InterfaceA {
    
}