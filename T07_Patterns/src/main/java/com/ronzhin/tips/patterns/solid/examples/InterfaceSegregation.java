package com.ronzhin.tips.patterns.solid.examples;

public class InterfaceSegregation {

    // Simple bicycle
    interface RideBicycle {
        void pedal();

        void keepBalance();

        void ringTheBell();
    }

    // Basic functional
    interface RideBicycleOk {
        void pedal();

        void keepBalance();
    }

    // Additional functional
    interface RingingBicycle {
        void ringTheBell();
    }

    // Right choose:
    // Stunt bicycle do not have the bell
    class StuntBicycle implements RideBicycleOk {

        @Override
        public void pedal() {
        }

        @Override
        public void keepBalance() {
        }

    }

}