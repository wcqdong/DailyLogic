package com.wcq.bit;

public class BitSetEx {


    public static void main(String[] args) {
        BitSetEx set = new BitSetEx(40);
//        set.set(7);
//        set.set(9, true);
        set.set(8, true);
        set.set(7, true);
//        set.set(6, true);
//        set.set(5, true);
//        set.set(20, true);
        set.set(34, true);

//        set.set(8, false);
//        int result = set.toInt();
//        System.out.println(Integer.toBinaryString(result));
        System.out.println(Long.toBinaryString(set.toLong()));
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

        System.out.println(4L << 32);

    }

    private int size;
    private byte[] data;

    BitSetEx(int size){
        if(size <= 0){
            return;
        }
        this.size = (size + 1) / 8;
        this. data = new byte[this.size];
    }

    public void set(int index, boolean value){
        if(index  < 0 ){
            return;
        }
        int idx = index / 8;
        if(idx >= size){
            return;
        }
        byte b = data[idx];

        int ix = index % 8;

        if(value){
            int bb = b | (1 << ix);
            data[idx] = (byte)bb;
        }else{
            int bb = b & ~(1 << ix);
            data[idx] = (byte)bb;
        }
    }

    public boolean get(int index){
        if(index  < 0 ){
            return false;
        }
        int idx = index / 8;
        if(idx >= size){
            return false;
        }

        byte b = data[idx];

        int ix = index % 8;

        return (b & (1 << ix)) != 0;
    }

    public void clean(){
        for (int i=0; i<size; ++i) {
            data[i] = 0;
        }
    }

    public int toInt() {
        if (size > 4) {
            return 0;
        }
        int result = 0;
        for(int i=Math.min(size, 4) - 1; i>=0; --i){
            int r = data[i] & 0xff;
            result += r << (i * 8);
        }

        return result;
    }

    public long toLong() {
        if (size > 8) {
            return 0;
        }
        long result = 0;
        for(int i=Math.min(size, 8) - 1; i>=0; --i){
            long r = data[i] & 0xff;
            result += r << (i * 8);
        }

        return result;
    }

    public int getSize(){
        return this.size * 8;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i=size - 1; i>=0; --i){
            sb.append(Integer.toBinaryString(data[i]));
            sb.append(",");
        }
        return sb.toString();
    }
}
