package com.ronzhin.tips.ioc.services;

import com.ronzhin.tips.ioc.model.Equation;

import java.util.List;

public interface EquationPreparer {
    List<Equation> prepareEquationsFor(int base);
}
