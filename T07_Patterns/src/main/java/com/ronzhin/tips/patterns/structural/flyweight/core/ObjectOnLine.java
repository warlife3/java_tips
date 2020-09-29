package com.ronzhin.tips.patterns.structural.flyweight.core;

public class ObjectOnLine {
    private final int x;

    private final HeavyCommonPart heavyCommonPart;

    ObjectOnLine(HeavyCommonPart heavyCommonPart, int x) {
        this.heavyCommonPart = heavyCommonPart;
        this.x = x;
    }

    @Override
    public String toString() {
        return "ObjectOnLine{" +
                "x=" + x +
                ", heavyCommonPart=" + heavyCommonPart +
                '}';
    }

}