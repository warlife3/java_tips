package com.ronzhin.tips.patterns.behavioral.chain.core;

public class ApplicationInput extends ApplicationProcessor {

    @Override
    protected void processInternal(ApplicationRecords applicationRecords) {
        applicationRecords.addHistoryRecord("Application accepted");
    }

    @Override
    public String getProcessorName() {
        return "Acceptance of application";
    }

}