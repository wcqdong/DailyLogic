package com.wcq.arrayCopy;

import java.util.ArrayList;
import java.util.List;

public class ArrayCopyTest {
    public static void main(String[] args) {
//        Model model1 = new Model();
//        System.out.println(model1.hashCode());
//        Model model2 = new Model();
//        System.out.println(model2.hashCode());
//
//        Model[] models = new Model[]{model1, model2};
//
//        Model[] newModels = new Model[2];
//
//        System.arraycopy(models, 0, newModels, 0, newModels.length);
//
//        System.out.println(newModels[0].hashCode());
//        System.out.println(newModels[1].hashCode());

//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(1);
//
//        System.out.println(list.subList(0, 1).size());
        byte[] b = new byte[500*1024];
        for(int i=0; i<100; i++){
            byte[] bb = new byte[b.length];
            System.arraycopy(bb, 0, b, 0, b.length);
        }

        long time = System.currentTimeMillis();
        for(int i=0; i<1000; i++){
            byte[] bb = new byte[b.length];
            System.arraycopy(bb, 0, b, 0, b.length);
        }
        System.out.println(System.currentTimeMillis() - time);

    }

    static class Model{
        int a = 1;
    }
}
