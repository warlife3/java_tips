package com.ronzhin.tips.bytecodes.agent.core;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.instrument.ClassFileTransformer;
import java.security.ProtectionDomain;

class CustomTransformer implements ClassFileTransformer {

    public static byte[] changeMethod(byte[] originalClass) {
        ClassReader cr = new ClassReader(originalClass);
        ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_MAXS);
        ClassVisitor cv = new CustomVisitor(Opcodes.ASM7, cw);
        cr.accept(cv, Opcodes.ASM7);

        byte[] finalClass = cw.toByteArray();

        File dir = new File("T06_ByteCodes" + File.separator + "custom-build");
        File file = new File("T06_ByteCodes" + File.separator + "custom-build" + File.separator + "generated.class");

        try {
            dir.mkdir();
            file.createNewFile();
        } catch (IOException e) {
        }

        try (OutputStream fos = new FileOutputStream(file)) {
            fos.write(finalClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return finalClass;
    }

    @Override
    public byte[] transform(ClassLoader loader, String className,
                            Class<?> classBeingRedefined,
                            ProtectionDomain protectionDomain,
                            byte[] classfileBuffer) {
        if (className.equals("com/ronzhin/tips/bytecodes/agent/Sum"))
            classfileBuffer = changeMethod(classfileBuffer);

        return classfileBuffer;
    }

}