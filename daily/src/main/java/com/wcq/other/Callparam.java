package com.wcq.other;

public class Callparam {
    public static void main(String[] args) {
        call(9);
        call(9, "1");
    }
//    private static void call(int a){
//        System.out.println(1);
//    }

    private static void call(int a, String... param){
        System.out.println(2);
    }
}
