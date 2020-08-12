package com.wcq.byteCode.javassist;

import com.wcq.byteCode.ByteCode;
import javassist.*;

import java.lang.reflect.InvocationTargetException;

public class JavassistTest {
    public static void main(String[] args) throws NotFoundException, CannotCompileException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        ClassPool cp = ClassPool.getDefault();
        CtClass cc = cp.get("com.wcq.byteCode.ByteCode");
        CtMethod m = cc.getDeclaredMethod("add");

        m.insertBefore("System.out.println(\"start1\");");
        m.insertAfter("System.out.println(\"end1\");");
        Class c = cc.toClass();
        ByteCode byteCode = (ByteCode)c.getConstructor().newInstance();
        byteCode.add();
    }
}
