package com.ronzhin.tips.patterns.behavioral.visitor.core;

import com.ronzhin.tips.patterns.behavioral.visitor.core.element.Brake;
import com.ronzhin.tips.patterns.behavioral.visitor.core.element.Engine;
import com.ronzhin.tips.patterns.behavioral.visitor.core.element.Transmission;

public interface Visitor {
    void visit(Engine item);

    void visit(Transmission item);

    void visit(Brake item);
}