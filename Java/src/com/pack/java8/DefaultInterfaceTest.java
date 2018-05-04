package com.pack.java8;

public class DefaultInterfaceTest {
    public static void main(String[] args) {
        new InterfaceImplementor().print();
    }
}

interface InterfaceA {
    default void print() {
        System.out.println("InterfaceA");
    }
}

//class InterfaceImplementor implements InterfaceA, InterfaceB {
//}


class InterfaceImplementor implements
InterfaceA11, InterfaceA12{} 

interface InterfaceA11 {
    default void print() {
        System.out.println("InterfaceA11");
    }
}

interface InterfaceA12 {
    /*default void print() {
        System.out.println("InterfaceA12");
    }*/
}

interface InterfaceB {
    default void print() {
        System.out.println("InterfaceB");
    }
}
