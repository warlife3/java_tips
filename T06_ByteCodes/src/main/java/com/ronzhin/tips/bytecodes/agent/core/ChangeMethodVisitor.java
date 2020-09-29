package com.ronzhin.tips.bytecodes.agent.core;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.AdviceAdapter;

class ChangeMethodVisitor extends AdviceAdapter {

    ChangeMethodVisitor(MethodVisitor methodVisitor, int access, String name, String descriptor) {
        super(Opcodes.ASM7, methodVisitor, access, name, descriptor);
        System.out.println(name);
    }

    @Override
    public void visitInsn(int opcode) {
        opcode = opcode == IADD ? ISUB : opcode;
        super.visitInsn(opcode);
    }

}
