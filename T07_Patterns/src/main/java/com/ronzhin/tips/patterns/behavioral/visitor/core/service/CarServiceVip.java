package com.ronzhin.tips.patterns.behavioral.visitor.core.service;

import com.ronzhin.tips.patterns.behavioral.visitor.core.Visitor;
import com.ronzhin.tips.patterns.behavioral.visitor.core.element.Brake;
import com.ronzhin.tips.patterns.behavioral.visitor.core.element.Engine;
import com.ronzhin.tips.patterns.behavioral.visitor.core.element.Transmission;

public class CarServiceVip implements Visitor {
    @Override
    public void visit(Engine item) {
        System.out.println("Vip: " + item.checkEngine());
    }

    @Override
    public void visit(Transmission item) {
        System.out.println("Vip: " + item.refreshOil());
    }

    @Override
    public void visit(Brake item) {
        System.out.println("Vip: " + item.replaceBrakePad());
    }
}