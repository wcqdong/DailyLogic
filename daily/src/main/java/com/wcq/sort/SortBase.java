package com.wcq.sort;

public abstract class SortBase {

    abstract void sort(Comparable[] a);

    boolean less(Comparable a, Comparable b){
        return a.compareTo(b) < 0;
    }

    void exch(Comparable[] a, int i, int j){
        Comparable iValue = a[i];
        a[i] = a[j];
        a[j] = iValue;
    }

    void show(Comparable[] a){
        for(Comparable c : a){
            System.out.println(c);
        }
    }

    boolean isSorted(Comparable[] a){

        for(int i= 1; i<a.length; ++i){
            if(less(a[i], a[i - 1])){
                System.out.println("sort fail??");
                return false;
            }
        }
        System.out.println("sort success!!");
        return true;
    }


}
