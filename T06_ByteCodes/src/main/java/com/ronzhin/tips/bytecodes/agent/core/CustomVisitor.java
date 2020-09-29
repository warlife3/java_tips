package com.ronzhin.tips.bytecodes.agent.core;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

class CustomVisitor extends ClassVisitor {

    public CustomVisitor(int api, ClassVisitor classVisitor) {
        super(api, classVisitor);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor methodVisitor = super.visitMethod(access, name, descriptor, signature, exceptions);

        if (name.equals("calculate")) {
            methodVisitor = new ChangeMethodVisitor(methodVisitor, access, name, descriptor);
        }

        return methodVisitor;
    }
}