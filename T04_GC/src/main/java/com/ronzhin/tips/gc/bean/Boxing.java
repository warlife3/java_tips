package com.ronzhin.tips.gc.bean;
/*
-Xms256m
-Xmx256m
-verbose:gc
-XX:+UseG1GC
*/

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

public class Boxing {
    public static void main(String[] args) throws Exception {
        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("com.ronzhin.tips.gc.bean:type=AlgorithmType");

        AlgorithmType bean = new AlgorithmType();
        mbs.registerMBean(bean, name);
        bean.setValueType(0);

        calculation(bean);
    }

    private static void calculation(AlgorithmTypeMBean algorithmTypeBean) {
        final int limit = Integer.MAX_VALUE;
        Integer integerCounter = 0;
        int intCounter = 0;
        for (int i = 0; i < limit; i++) {

            if (algorithmTypeBean.getValueType() == 0)
                intCounter += 1;
            else integerCounter += Integer.valueOf(1);

            if (i % 1_000_000 == 0) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println(intCounter + integerCounter);
    }

}