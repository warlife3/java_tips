package com.ronzhin.tips.patterns.solid.poly;

import com.ronzhin.tips.patterns.solid.poly.operation.Create;
import com.ronzhin.tips.patterns.solid.poly.operation.Operation;
import com.ronzhin.tips.patterns.solid.poly.operation.Update;

public class PolyGood {

    public static void main(String[] args) {
        new PolyGood().go("OK1", new Create());
        new PolyGood().go("OK2", new Update());
    }

    private void go(String data, Operation operation) {
        operation.action(data);
    }

}