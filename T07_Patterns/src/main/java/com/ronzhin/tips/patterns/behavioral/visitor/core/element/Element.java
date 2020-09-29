package com.ronzhin.tips.patterns.behavioral.visitor.core.element;

import com.ronzhin.tips.patterns.behavioral.visitor.core.Visitor;

public interface Element {
    void accept(Visitor v);
}