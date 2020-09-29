package com.ronzhin.tips.patterns.behavioral.visitor.core.element;

import com.ronzhin.tips.patterns.behavioral.visitor.core.Visitor;

public class Engine implements Element {
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    public String checkEngine() {
        return "Engine Ok";
    }
}
