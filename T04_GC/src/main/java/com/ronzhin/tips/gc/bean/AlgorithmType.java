package com.ronzhin.tips.gc.bean;

public class AlgorithmType implements AlgorithmTypeMBean {

    private int algorithmType = 0;

    @Override
    public void setValueType(int type) {
        System.out.println("Set new value: " + type);
        algorithmType = type;
    }

    @Override
    public int getValueType() {
        return algorithmType;
    }

    public int getAlgorithmType() {
        return algorithmType == 0 ? 0 : 1;
    }

}