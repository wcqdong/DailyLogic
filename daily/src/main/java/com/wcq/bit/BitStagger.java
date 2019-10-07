package com.wcq.bit;

/**
 * 位操作,两个位错开
 * 例如1111，和0000，错开后变成10101010
 * geohash中用，别的地方不知道有什么用
 */
public class BitStagger {
    public static void main(String[] args) {
        int v1 = 421249;
        int v2 = 435534;

        System.out.println(Long.toBinaryString(v1));
        System.out.println(Long.toBinaryString(v2));

        long v3 = stragger(v1);
        long v4 = stragger(v2);

        System.out.println(Long.toBinaryString(v3));
        System.out.println(Long.toBinaryString(v4 << 1));

        long v = v3 | (v4 << 1);

        System.out.println(Long.toBinaryString(v));

    }

    private static final long B[] = {0x5555555555555555L, 0x3333333333333333L,
            0x0F0F0F0F0F0F0F0FL, 0x00FF00FF00FF00FFL,
            0x0000FFFF0000FFFFL};
    private static final int S[] = {1, 2, 4, 8, 16};


    /**
     * 111变成101010，用0穿插
     * @param v
     * @return
     */
    static long stragger(long v){
        v = (v | (v << S[4])) & B[4];
        v = (v | (v << S[3])) & B[3];
        v = (v | (v << S[2])) & B[2];
        v = (v | (v << S[1])) & B[1];
        v = (v | (v << S[0])) & B[0];

        return v;
    }


}
