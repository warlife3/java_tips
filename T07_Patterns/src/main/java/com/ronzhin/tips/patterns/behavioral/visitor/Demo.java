package com.ronzhin.tips.patterns.behavioral.visitor;

import com.ronzhin.tips.patterns.behavioral.visitor.core.Visitor;
import com.ronzhin.tips.patterns.behavioral.visitor.core.element.Brake;
import com.ronzhin.tips.patterns.behavioral.visitor.core.element.Element;
import com.ronzhin.tips.patterns.behavioral.visitor.core.element.Engine;
import com.ronzhin.tips.patterns.behavioral.visitor.core.element.Transmission;
import com.ronzhin.tips.patterns.behavioral.visitor.core.service.CarService;
import com.ronzhin.tips.patterns.behavioral.visitor.core.service.CarServiceVip;

import java.util.List;

public class Demo {
    public static void main(String[] args) {

        List<Element> elements = List.of(new Brake(), new Engine(), new Transmission());
        List<Visitor> visitors = List.of(new CarService(), new CarServiceVip());
        for (Visitor visitor : visitors) {
            elements.forEach(elem -> elem.accept(visitor));
        }
    }
}