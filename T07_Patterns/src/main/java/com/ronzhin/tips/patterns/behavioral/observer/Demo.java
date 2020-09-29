package com.ronzhin.tips.patterns.behavioral.observer;

import com.ronzhin.tips.patterns.behavioral.observer.core.ConsumerA;
import com.ronzhin.tips.patterns.behavioral.observer.core.ConsumerB;
import com.ronzhin.tips.patterns.behavioral.observer.core.EventProducer;

public class Demo {

    public static void main(String[] args) {
        EventProducer producer = new EventProducer();
        ConsumerA consumerA = new ConsumerA();
        ConsumerB consumerB = new ConsumerB();

        producer.addListener(consumerA);
        producer.addListener(consumerB.getListener());

        producer.event("eventA");
        producer.event("eventB");

        producer.removeListener(consumerA);
        producer.removeListener(consumerB.getListener());

        producer.event("eventC");
        producer.event("eventD");
    }

}