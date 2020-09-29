package com.ronzhin.tips.bytecodes.agent.core;

import java.lang.instrument.Instrumentation;

public class AsmAgent {

    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("premain");
        inst.addTransformer(new CustomTransformer());
    }

}

