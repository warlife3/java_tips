package com.ronzhin.tips.objectsize;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;
//VM option:
//-Djdk.attach.allowAttachSelf
//-XX:ObjectAlignmentInBytes=256 - memory object alignment (standards by 8 )
//-XX:-UseCompressedOops - deactivate compressed 32bit link. If alignment by 8 than size 8 * 4 Gb

public class JolExample {

    public static void main(String[] args) {

        System.out.println("boolean:" + VM.current().sizeOfField("boolean"));

        System.out.println(ClassLayout.parseClass(OneBoolean.class).toPrintable());
        System.out.println(ClassLayout.parseClass(TwoBoolean.class).toPrintable());
        System.out.println(ClassLayout.parseClass(FourBoolean.class).toPrintable());
        System.out.println(ClassLayout.parseClass(OneInt.class).toPrintable());

        System.out.println(ClassLayout.parseClass(TwoInt.class).toPrintable());
        System.out.println(ClassLayout.parseClass(FourInt.class).toPrintable());

        System.out.println(ClassLayout.parseClass(MixBooleanInt.class).toPrintable());
        System.out.println(ClassLayout.parseClass(TwoLong.class).toPrintable());
    }

    public class OneBoolean {
        boolean value;
    }

    public class TwoBoolean {
        boolean value1;
        boolean value2;
    }

    public class FourBoolean {
        boolean value1;
        boolean value2;
        boolean value3;
        boolean value4;
    }

    public class OneInt {
        int value1;
    }

    public class TwoInt {
        int value1;
        int value2;
    }

    public class FourInt {
        int value1;
        int value2;
        int value3;
        int value4;
    }

    public class MixBooleanInt {
        Object object = new Object();
        boolean boolVal;
        int intVal;
        boolean isBoolVal;
    }

    public class TwoLong {
        long l1;
        long l2;
    }

}