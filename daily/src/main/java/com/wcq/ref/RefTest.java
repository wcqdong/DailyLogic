package com.wcq.ref;

public class RefTest {
    public static void main(String[] args) {
        Integer a = 1;
        change(a);
        System.out.println(a);

        Integer b = Integer.valueOf(1);
        change(b);
        System.out.println(b);
    }

    private static void change(Integer a){
        a = 2;
//        a = Integer.valueOf(2);
    }
}
