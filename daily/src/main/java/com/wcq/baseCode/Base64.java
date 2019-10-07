package com.wcq.baseCode;

import sun.misc.BASE64Encoder;

public class Base64 {
    public static void main(String[] args) {
        String s = "M";
        Integer code = (int)s.charAt(0);
        System.out.println(Integer.toBinaryString(code));
    }
}
