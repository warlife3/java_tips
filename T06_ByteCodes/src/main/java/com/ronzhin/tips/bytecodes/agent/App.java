package com.ronzhin.tips.bytecodes.agent;

/**
 * java -javaagent:T06_ByteCodes/build/libs/SummatorApp.jar -jar T06_ByteCodes/build/libs/SummatorApp.jar
 */

public class App {

    public static void main(String[] args) {
        Sum sum = new Sum();
        System.out.println(sum.calculate(2, 2));
    }

}