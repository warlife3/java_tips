package com.ronzhin.tips.patterns.creational.singleton;

public class Singleton {

    private Singleton() {
        System.out.println("lazy creation");
    }

    static Singleton getInstance() {
        System.out.println("getInstance");
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        static final Singleton instance = new Singleton();

        static {
            System.out.println("init SingletonHolder");
        }
    }
}

class SingletonDemo {
    public static void main(String[] args) {
        System.out.println("--- begin ---");
        Singleton singleton1 = Singleton.getInstance();

        System.out.println();
        Singleton singleton2 = Singleton.getInstance();

        System.out.printf("singleton1 == singleton2 => %b\n", singleton1 == singleton2);
        System.out.println("---end ---");
    }
}