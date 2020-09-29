package com.ronzhin.tips.patterns.behavioral.chain.core;

public class ApplicationReader extends ApplicationProcessor {

    @Override
    protected void processInternal(ApplicationRecords applicationRecords) {
        applicationRecords.addHistoryRecord("Application reviewed");
    }

    @Override
    public String getProcessorName() {
        return "Consideration of the application";
    }

}