package com.wcq.Number;


public class TestNumber {
    public static void main(String[] args) {
        int a = 1;
        int b = 2;

        compareTo(a, b);

        double c = 1D;
        double d = 2D;

        compareTo(c, d);

        // Integer转object Object转long
        Integer id = Integer.valueOf(1);

        Object obj = id;
        long id1 = ((Number)obj).longValue();
        System.out.println(id1);
    }

    private  static <T extends Number> void compareTo(T a, T b) {
        System.out.println(a.doubleValue() > b.doubleValue());
    }
}
