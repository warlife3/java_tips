package com.ronzhin.tips.patterns.behavioral.chain;

import com.ronzhin.tips.patterns.behavioral.chain.core.*;

public class Demo {

    public static void main(String[] args) {
        ApplicationRecords applicationRecords = new ApplicationRecords();

        ApplicationProcessor input = new ApplicationInput();
        ApplicationProcessor reader = new ApplicationReader();
        ApplicationProcessor result = new ApplicationResult();

        input.setNext(reader);
        reader.setNext(result);

        input.process(applicationRecords);
        applicationRecords.printHistory();
    }

}