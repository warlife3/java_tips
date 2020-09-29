package com.ronzhin.tips.patterns.behavioral.chain.core;

public class ApplicationResult extends ApplicationProcessor {

    @Override
    protected void processInternal(ApplicationRecords applicationRecords) {
        applicationRecords.addHistoryRecord("Result issued");
    }

    @Override
    public String getProcessorName() {
        return "Output of the result";
    }

}