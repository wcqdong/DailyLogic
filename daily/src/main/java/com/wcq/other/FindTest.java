package com.wcq.other;

import java.util.List;

public class FindTest {
    public boolean find(List<Integer> list, Integer element){
        int size = list.size();
        list.contains(element);
        for(int i=0; i<list.size(); ++i){

        }
        return true;
    }

    public String reverse(String str){
        StringBuilder builder = new StringBuilder();
        int length = str.length();
        for(int i=length - 1; i>= 0; --i){
            builder.append(str.charAt(i));
        }
        return builder.toString();
    }

    static int N = 10;
    static int[] indexArray = new int[N];
    static boolean checkRepeated(int[] array){
        for(int i=0; i<array.length; ++i){
            int value = indexArray[array[i]];
            if(value != 0){
                return true;
            }
        }
        return false;
    }
}
