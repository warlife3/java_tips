package com.ronzhin.tips.patterns.behavioral.chain.core;

import java.util.ArrayList;
import java.util.List;

public class ApplicationRecords {
    private final List<String> history = new ArrayList<>();

    void addHistoryRecord(String record) {
        history.add(record);
    }

    public void printHistory() {
        System.out.println(history);
    }

}