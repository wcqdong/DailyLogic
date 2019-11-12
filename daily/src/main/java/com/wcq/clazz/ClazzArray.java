package com.wcq.clazz;

public class ClazzArray {
    public static void main(String[] args) {
        Object o = get();
        System.out.println(o.getClass().isArray());
        o = get1();
        System.out.println(o.getClass().isArray());

    }

    static Object get(){
        return new Object[]{"1"};
    }

    static Object get1(){
        return "1";
    }

    public class tt{

    }
}
