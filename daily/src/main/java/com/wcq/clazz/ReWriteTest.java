package com.wcq.clazz;

import java.lang.reflect.Method;
import java.util.Arrays;

public class ReWriteTest {
    public static void main(String[] args) throws NoSuchMethodException {
        Method method = AAA.class.getMethod("test");
        Class baseClass = method.getDeclaringClass();
        System.out.println("在父类 " + baseClass.getSimpleName() + " 中实现");

        Method baseMethod = baseClass.getMethod("test");
        System.out.println(baseMethod);

//        baseClass = baseMethod.getDeclaringClass();
//        System.out.println("父类是 " + baseClass.getSimpleName());

        Class<?>[] classes = AAA.class.getInterfaces();
        Arrays.stream(classes).forEach(System.out::println);

    }

    interface A{
        default void test(){}
    }
    class AA implements A{
//        public void test(){}
    }
    class AAA extends AA{

    }
}
