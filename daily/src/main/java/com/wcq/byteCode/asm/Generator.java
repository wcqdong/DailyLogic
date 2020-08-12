package com.wcq.byteCode.asm;

import co.paralleluniverse.asm.ClassReader;
import co.paralleluniverse.asm.ClassVisitor;
import co.paralleluniverse.asm.ClassWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Generator {
    public static void main(String[] args) throws IOException {
        ClassReader classReader = new ClassReader("com.wcq.byteCode.ByteCode");
        ClassWriter classWriter = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        //处理
        ClassVisitor classVisitor = new MyClassVisitor(classWriter);
        classReader.accept(classVisitor, ClassReader.SKIP_DEBUG);
        byte[] data = classWriter.toByteArray();
        //输出
        File f = new File("daily/target/classes/com/wcq/byteCode/ByteCode.class");
        FileOutputStream fout = new FileOutputStream(f);
        fout.write(data);
        fout.close();
        System.out.println("now generator cc success!!!!!");

    }

}
