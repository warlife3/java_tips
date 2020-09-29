package com.ronzhin.tips.patterns.behavioral.chain.core;

public abstract class ApplicationProcessor {

    private ApplicationProcessor next;

    private ApplicationProcessor getNext() {
        return next;
    }

    public void setNext(ApplicationProcessor next) {
        this.next = next;
    }

    public void process(ApplicationRecords applicationRecords) {
        before();
        processInternal(applicationRecords);
        after();
        if (getNext() != null) {
            getNext().process(applicationRecords);
        }
    }

    protected abstract void processInternal(ApplicationRecords applicationRecords);

    public abstract String getProcessorName();

    private void before() {
        System.out.println("before: " + getProcessorName());
    }

    private void after() {
        System.out.println("after: " + getProcessorName());
    }

}