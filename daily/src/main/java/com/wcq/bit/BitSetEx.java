package com.wcq.bit;

import java.io.IOException;

public class BitSetEx {


    public static void main(String[] args) throws Exception {
        BitSetEx set = new BitSetEx(16);
//        set.set(1, true);
//        set.set(9, true);
        set.set(8, true);
        set.set(7, true);
//        set.set(6, true);
        set.set(5, true);
//        set.set(19, true);
//        set.set(34, true);
//        System.out.println(set.get(8));

//        set.set(8, false);
//        int result = set.toInt();
//        System.out.println(Integer.toBinaryString(result));
//        for(byte b : set.getData()){
//            if(b == 0){
//                System.out.println(0);
//            }
//            System.out.println(Integer.toBinaryString(b).substring(24));
//        }
        System.out.println(set.toString());

        BitSetEx set1 = BitSetEx.strToBitSetEx(set.toString());
        System.out.println(set1.toString());

//        System.out.println(Long.toBinaryString(set.toLong()));
//        set.set(9);
//        System.out.println(set.get(9));
//        System.out.println(set.toString());

//        byte a = -1;
//        System.out.println(Integer.toBinaryString(a));

//        for(int i=0; i<40; i++){
//            if(set.get(i)){
//                System.out.println("index=" + i);
//            }
//        }

//        System.out.println(4L << 32);

    }

    private int size;
    private byte[] data;

    public BitSetEx() throws Exception {
        this(16);
    }

    public BitSetEx(int size) throws Exception {
        this.size = size;
        if(size <= 0){
            throw new Exception("Index excced size, index={}, size={}");
        }

        int arraySize = size / 8 + (size%8 == 0 ? 0 : 1);
        this.data = new byte[arraySize];
    }

    private BitSetEx(byte[] data) {
        this.size = data.length * 8;
        this.data = data;
    }

    public static BitSetEx strToBitSetEx(String byteStr) throws Exception {
        if(byteStr.isEmpty()){
            return new BitSetEx();
        }
        String[] strs = byteStr.split(",");
        int len = strs.length;
        byte[] data = new byte[len];
        for(int i=len - 1; i>=0; --i){
            data[len - 1 - i] = (byte)Integer.parseInt(strs[i], 2);
        }

        return new BitSetEx(data);
    }


    public void set(int index, boolean value) throws Exception {
        if(index >= size){
            throw new Exception("Index excced size, index={}, size={}");
        }
        if(index  < 0 ){
            throw new Exception("Index excced size, index={}, size={}");
        }
        int byteIndex = index / 8;
        if(byteIndex >= data.length){
            throw new Exception("Index excced size, index={}, size={}");
        }
        byte b = data[byteIndex];

        int ix = index % 8;

        if(value){
            int bb = b | (1 << ix);
            data[byteIndex] = (byte)bb;
        }else{
            int bb = b & ~(1 << ix);
            data[byteIndex] = (byte)bb;
        }
    }

    public boolean get(int index) throws Exception {
        if(index >= size){
            throw new Exception("Index excced size, index={}, size={}");
        }
        if(index  < 0 ){
            throw new Exception("Index excced size, index={}, size={}");
        }
        int byteIndex = index / 8;
        if(byteIndex >= data.length){
            throw new Exception("Index excced size, index={}, size={}");
        }

        byte b = data[byteIndex];

        int ix = index % 8;

        return (b & (1 << ix)) != 0;
    }

    public void clean(){
        for (int i=0; i<data.length; ++i) {
            data[i] = 0;
        }
    }

    public int toInt() {
//        if (data.length > 4) {
//            throw new SysException("Size is large than integer, size={}", size);
//        }
        int result = 0;
        for(int i=Math.min(data.length, 4) - 1; i>=0; --i){
            int r = data[i] & 0xFF;
            result += r << (i * 8);
        }

        return result;
    }

    public long toLong() {
//        if (data.length > 8) {
//            throw new SysException("Size is large than integer, size={}", size);
//        }
        long result = 0;
        for(int i=Math.min(data.length, 8) - 1; i>=0; --i){
            long r = data[i] & 0xFF;
            result += r << (i * 8);
        }

        return result;
    }

    public int getSize(){
        return this.size;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        int len = data.length;
        for(int i=len - 1; i>=0; --i){
            sb.append(Integer.toBinaryString((data[i] & 0xFF) + 0x100).substring(1));
            if(i > 0){
                sb.append(",");
            }
        }

        return sb.toString();
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }
}
