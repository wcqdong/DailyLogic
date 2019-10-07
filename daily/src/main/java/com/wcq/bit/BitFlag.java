package com.wcq.bit;

public class BitFlag {
    public static void main(String[] args) {
        BitSet set = new BitSet(40);
//        set.set(7);
        set.set(9, true);
//        set.set(9);
        System.out.println(set.get(9));
        set.toString();
    }
}

class BitSet{
    int size;
    byte[] data;

    BitSet(int size){
        this.size = (size + 1) / 8;
        data = new byte[this.size];
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

    boolean get(int index){
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

    public String toString() {
        for(int i=0; i<size; i++){
            System.out.println(data[i]);
        }
        return null;
    }
}
