package com.pack.java;

import java.util.regex.Pattern;

public class PatternTest {
    public static void main(String[] args) {
        String string = "Pool 2 - pool 2";
        new PatternTest().testSplit(string);
    }

    private void testSplit(String sample) {
        Pattern ptn = Pattern.compile(" \\- ");
        String[] parts = ptn.split(sample);
        parts = sample.split(" \\- ");
        System.out.println(parts);
    }
}
