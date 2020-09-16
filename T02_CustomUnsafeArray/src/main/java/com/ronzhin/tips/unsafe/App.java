package com.ronzhin.tips.unsafe;

import com.ronzhin.tips.unsafe.array.IntArray;

public class App {

    public static void main(String[] args) throws Exception {

        int arraySizeMax = 1_000_000;
        int arraySizeInit = 10;

        long sum = 0;

        try (IntArray myArr = new IntArray(arraySizeInit)) {
            for (int i = 0; i < arraySizeMax; i++) {
                myArr.add(i);
            }

            for (int i = 0; i < myArr.getSize(); i++) {
                sum += myArr.get(i);
            }
        }

        System.out.println("Sum of all elements is: " + sum);

    }

}