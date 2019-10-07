package com.wcq.forkJoin;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * 多任务并行
 *
 * 不是很好用，每个子任务得达到一定数量（1000以上）和一定复杂度（耗时）的时候才能看出效果
 */
public class ForkJoin {
    static ForkJoinPool forkJoinPool = new ForkJoinPool(8);

    public static void fork(String[] array){
        LoadResTask task = new LoadResTask(array, 0, array.length - 1);
//        Future<List<byte[]>> result = forkJoinPool.submit(task);
        List<byte[]> list = forkJoinPool.invoke(task);
//        try {
//            List<byte[]> list = result.get();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

//        forkJoinPool.shutdown();
    }

    static class LoadResTask extends RecursiveTask<List<byte[]>> {
        private static final int THRESHOLD = 5;
        private final int low, high;

        private String[] array;

        private LoadResTask(String[] array, int low, int high) {
            this.array = array;
            this.low = low;
            this.high = high;
        }

        @Override
        protected List<byte[]> compute() {
            if (high - low < THRESHOLD) {
                String[] modules = new String[high - low + 1];
                int index=0;
                for (int i = low; i <= high; i++) {
                    modules[index++] = array[i];
                }

                return doSS(modules);
            } else {
                int middle = low + THRESHOLD;
                LoadResTask leftTask = new LoadResTask(array, low, middle - 1);
                LoadResTask rightTask = new LoadResTask(array, middle, high);

                rightTask.fork();

                List<byte[]> left = leftTask.compute();
                List<byte[]> right = rightTask.join();

//                List<byte[]> left = (List<byte[]>) leftTask.join();
//                List<byte[]> rifht = (List<byte[]>) rightTask.join();
                left.addAll(right);
                return left;
            }
        }


    }
    private static List<byte[]> doSS(String[] modules) {
        return null;
    }

    private static byte[] doSS1(String[] modules) {
        return null;
    }

    static ExecutorService service = Executors.newFixedThreadPool(8);

    private static final int THRESHOLD = 1000;


    public static void submit(String[] modules){
        List<Future<byte[]>> list = new ArrayList<>();
        int offset = 0;

        for(int i=0; i<modules.length; i++){
            String[] array = new String[Math.min(THRESHOLD, modules.length - offset)];
            for (int j = 0; j < array.length; j++) {
                array[j] = modules[offset++];
            }

            Future<byte[]> f = service.submit(new SubTask(array));
            list.add(f);
        }

        List<byte[]> result = new ArrayList<>();
        for(Iterator<Future<byte[]>> iter = list.iterator(); iter.hasNext();){
            Future<byte[]> f = iter.next();
            try {
                byte[] re = f.get();
                result.add(re);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }

    static class SubTask implements Callable<byte[]> {

        private String[] modules;

        SubTask(String[] modules){
            this.modules = modules;
        }

        @Override
        public byte[] call() {
            return doSS1(modules);

        }
    }

    static class XuThread extends Thread{
        public volatile String[] modules;
        public volatile byte[] bytes;
        public Object lock = new Object();

        @Override
        public void run() {
            while(true){
                synchronized (lock){
                    if (modules == null){
                        try {
//                            System.out.println("线程等待");

                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
//                    System.out.println("线程唤醒");

                    bytes = doSS1(modules);

                    modules = null;

                    lock.notifyAll();

                }
            }
        }
    }

    static XuThread thread = new XuThread();
    static XuThread thread2 = new XuThread();

    static {
        thread.start();
        thread2.start();
    }

    public static void  run(String[] modules){

        int lenHalf = modules.length /3;

        String[] half1 = new  String[lenHalf];
        String[] half2 = new  String[lenHalf];
        String[] half3 = new  String[modules.length  - 2*lenHalf];

        for(int i=0; i<lenHalf; i++){
            half1[i] = modules[i];
        }
        for(int i=0; i<half2.length; i++){
            half2[i] = modules[lenHalf + i];
        }

        for(int i=0; i<half3.length; i++){
            half3[i] = modules[2*lenHalf + i];
        }

        thread.modules = half1;
        thread2.modules = half2;

        synchronized(thread.lock){
            thread.lock.notifyAll();
        }

        synchronized(thread2.lock){
            thread2.lock.notifyAll();
        }

        List<byte[]> list = new ArrayList<>();
        list.add(doSS1(half3));


        synchronized (thread.lock){
            while (thread.bytes == null){
                try {
                    thread.lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }

        synchronized (thread2.lock){
            while (thread2.bytes == null){
                try {
                    thread2.lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }


//        while (thread.bytes == null){
////            System.out.println(1);
//        }

        list.add(thread.bytes);
        list.add(thread2.bytes);
        thread.bytes = null;
        thread2.bytes = null;

    }
}
