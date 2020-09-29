package com.ronzhin.tips.patterns.behavioral.visitor.core.element;

import com.ronzhin.tips.patterns.behavioral.visitor.core.Visitor;

public class Brake implements Element {
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    public String replaceBrakePad() {
        return "Replace Brake Pad";
    }
}