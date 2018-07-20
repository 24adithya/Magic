package com.pack.java;

class TestPerformance {
    public static void main(String[] args) {

        long startTime = System.currentTimeMillis();
        int t = 0;
        for (int i = 0; i < 999999999; i++) {
            for (int j = 0; j < 999999999; j++) {
                t = t + j;
            }
        }

        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("1. ElapsedTime---------->" + elapsedTime);

    }
}
