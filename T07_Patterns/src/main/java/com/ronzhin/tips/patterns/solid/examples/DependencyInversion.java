package com.ronzhin.tips.patterns.solid.examples;

public class DependencyInversion {

    interface SteeringWheel {}

    interface Engine {}

    /*Good implementation*/
    class Car {
        private SteeringWheel steeringWheel;
        private Engine engine;

        public Car(SteeringWheel steeringWheel, Engine engine) {
            this.steeringWheel = steeringWheel;
            this.engine = engine;
        }
    }
}