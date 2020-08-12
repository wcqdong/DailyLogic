package com.wcq.bit;

public class Bytebit {

    private final static byte humanInBit9 = 1;
    private final static byte humanInBit25 = 2;
    private final static byte humanInBit49 = 4;
    public static void main(String[] args) {
        byte hasHuman = 0;
//        System.out.println(b | humanInBit9);
//        System.out.println(b | humanInBit25);
//        System.out.println(b | humanInBit49 | humanInBit25);

        hasHuman = (byte) (hasHuman | humanInBit49);
        System.out.println(hasHuman);

//        hasHuman &= ~humanInBit25;
//        System.out.println(hasHuman);

        System.out.println((hasHuman & humanInBit9) > 0);
        System.out.println((hasHuman & (humanInBit25 | humanInBit9)) > 0);
        System.out.println((hasHuman & (humanInBit49 | humanInBit25 | humanInBit9)) > 0);


    }
}
