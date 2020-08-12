package com.wcq.kotlin.coroutine;

import co.paralleluniverse.fibers.Fiber;
import co.paralleluniverse.fibers.SuspendExecution;
import co.paralleluniverse.strands.SuspendableCallable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class Test {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for(int i=0; i<20; i++){
            list.add(i);
        }


        long time = System.currentTimeMillis();
        List<String> result = KTConcurrentKt.batch(list);
//        System.out.println("end=" + result.size());
//        result.forEach(System.out::println);
        System.out.println(System.currentTimeMillis() - time);

//        time = System.currentTimeMillis();
//        result = batch(list);
//        System.out.println(System.currentTimeMillis() - time);
//
//        time = System.currentTimeMillis();
//        result = batch(list);
//        System.out.println(System.currentTimeMillis() - time);
//
//        time = System.currentTimeMillis();
//        result = KTConcurrentKt.batch(list);
//        System.out.println(System.currentTimeMillis() - time);
//
//        time = System.currentTimeMillis();
//        result = batch(list);
//        System.out.println(System.currentTimeMillis() - time);
//
//        time = System.currentTimeMillis();
//        result = KTConcurrentKt.batch(list);
//        System.out.println(System.currentTimeMillis() - time);

//        result = KTConcurrentKt.batch1(list);
//
//
//        time = System.currentTimeMillis();
//        result = KTConcurrentKt.batch1(list);
//        System.out.println(System.currentTimeMillis() - time);
//
//        time = System.currentTimeMillis();
//        result = KTConcurrentKt.batch(list);
//        System.out.println(System.currentTimeMillis() - time);
//
//        result = new ArrayList<>();
////
//        time = System.currentTimeMillis();
//        for(int e : list){
//            result.add(deal(e));
//        }
//        System.out.println(System.currentTimeMillis() - time);
//
//        time = System.currentTimeMillis();
//        result = KTConcurrentKt.batch1(list);
//
//        System.out.println(System.currentTimeMillis() - time);
    }

    public static String deal(int i){
        return Thread.currentThread().getName() + "  " + i +"result";
    }

//    static SuspendableCallable<String> sr = new SuspendableCallable<String>() {
//        @Override
//        public String run() throws SuspendExecution, InterruptedException {
//            return null;
//        }
//    };

    public static List<String> batch(List<Integer> list){
        List<String> result = new ArrayList<>();
        List<Fiber> fiberList = new ArrayList<>();
        list.forEach(i -> {
            Fiber fiber = new Fiber(new SuspendableCallable<String>() {
                @Override
                public String run() throws SuspendExecution, InterruptedException {
                    return deal(i);
                }
            }){

            }.start();
            fiberList.add(fiber);
        });

        fiberList.forEach(fiber->{
            try {
                result.add((String) fiber.get());
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        return result;

    }
}
