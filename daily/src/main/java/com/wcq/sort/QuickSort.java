package com.wcq.sort;

/**
 * 快排
 */
public class QuickSort extends SortBase{
    
    public void sort(Comparable[] a){
        sort(a, 0, a.length - 1);
    }

    private void sort(Comparable[] array, int lo, int hi) {
        if(hi <= lo){
            return;
        }
        // 切分
        int part = partition(array, lo, hi);
        sort(array, lo, part - 1);
        sort(array, part + 1, hi);
        
    }

    private int partition(Comparable[] array, int lo, int hi) {
        int i=lo;
        int j = hi + 1;
        Comparable v = array[lo];
        while (true){
            // 从前面找，从第二个开始，因为第一个元素作为切分比较值
            while(less(array[++i], v)){
                if(i == hi){
                    break;
                }
            }
            // 从后面找
            while (less(v, array[--j])){
                if(j == lo){
                    break;
                }
            }
            if(i >= j){
                break;
            }
            exch(array, i, j);
        }

        // 交换
        exch(array, lo, j);

        return j;

    }

}
