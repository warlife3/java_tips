package com.ronzhin.tips.remotedebug;

/*
java -Xdebug -Xrunjdwp:transport=dt_socket,address=5005,server=y,suspend=y -jar T05_ObjectSizeRemoteDebug/build/libs/T05_ObjectSizeRemoteDebug-0.1.jar
java -agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=*:5005 -jar T05_ObjectSizeRemoteDebug/build/libs/T05_ObjectSizeRemoteDebug-0.1.jar
*/

public class RemoteDebug {

    private int value = 0;

    public static void main(String[] args) throws InterruptedException {
        new RemoteDebug().loop();
    }

    private void loop() throws InterruptedException {
        while (!Thread.currentThread().isInterrupted()) {
            value += 0;
            incVal();
            System.out.println(value);
            Thread.sleep(2_000);
        }
    }

    private void incVal() {
        value += 10;
    }
}