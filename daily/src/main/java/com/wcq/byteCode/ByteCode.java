package com.wcq.byteCode;

import java.util.ArrayList;
import java.util.List;

public class ByteCode {
    private int a = 1;

    public int add(){
        int b = 2;
        int c = a + b;
        System.out.println(c);

        List<Integer> list = new ArrayList<>();
        return c;
    }
}
