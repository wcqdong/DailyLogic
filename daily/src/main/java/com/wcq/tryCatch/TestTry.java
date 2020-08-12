package com.wcq.tryCatch;

import org.apache.commons.collections4.IterableMap;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

public class TestTry {
    public static void main(String[] args) {
//        List<Integer> list = new ArrayList<>();
////        for(int i=0; i<10; i++){
////            try(InputStream in = new FileInputStream("c")) {
////
////            }
////        }
//
//        try (A a = new A()){
//            System.out.println(1/0);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        System.out.println("finish");

        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 1);
        map.put(2, 2);
        map.put(3, 3);
        for(Iterator<Map.Entry<Integer, Integer>> iter = map.entrySet().iterator(); iter.hasNext();){
            Map.Entry<Integer, Integer> en = iter.next();
            if(en.getValue() == 3){
                iter.remove();
                continue;
            }
            en.setValue(en.getValue() + 10);
        }
        for(Map.Entry<Integer, Integer> en : map.entrySet()){
            System.out.println(en.getValue());
        }
    }

    static class A implements AutoCloseable {

        @Override
        public void close(){
            System.out.println("closed");
        }
    }
}
