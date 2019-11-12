package com.wcq.fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializeFilter;

import java.util.function.Supplier;

public class FTTest {



//    public static void main(String[] args) {
//        Supplier s0 = new Supplier() {
//            @Override
//            public Object get() {
//                JSONObject jo = new JSONObject();
//                jo.put("a", 1);
//                jo.put("b", 5555);
//                jo.put("c", 23543);
//                String str = JSON.toJSONString(jo);
//                return null;
//            }
//        };
//        Supplier s1 = new Supplier() {
//            @Override
//            public Object get() {
//                FtModel1 m = new FtModel1();
//                m.setA(1);
//                m.setB("2345");
//                m.setC(23543);
//                String str = JSON.toJSONString(m);
//                return null;
//            }
//        };
//
//        Supplier s2 = new Supplier() {
//            @Override
//            public Object get() {
//                FtModel2 m = new FtModel2();
//                m.setA(1);
//                m.setB("2345");
//                m.setC(23543);
//                String str = JSON.toJSONString(m);
//                return null;
//            }
//        };
//
//        Supplier s3 = new Supplier() {
//            @Override
//            public Object get() {
//                FtModel3 m = new FtModel3();
//                m.setA(1);
//                m.setB("2345");
//                m.setC(23543);
//                String str = JSON.toJSONString(m);
//                return null;
//            }
//        };
//
//
//        doIt(s1);
//        doIt(s1);
//        doIt(s2);
//        doIt(s3);
//        doIt(s0);
//
//    }

    static int count = 1;
//    static int count = 10000000;
    public static void main(String[] args) {
        FtModel1 m1 = new FtModel1();
        m1.setA(0);
        m1.setB("2345");
        m1.setC(23543);
        m1.setD(23543);
        m1.setE("23543");
        m1.setG("23543");
        m1.setU("23543");
        FtModel3 f3 = new FtModel3();
        f3.setA(1231423);
        m1.getList().add(f3);
        m1.getList().add(new FtModel3());

        FtModel3 f4 = new FtModel3();
        f4.setA(586);
        m1.getMap().put(7, f4);
        m1.getMap().put(4, f3);
        String ms1 = JSON.toJSONString(m1);


        JSONObject jo = new JSONObject();
        jo.put("a", 0);
        jo.put("b", "2345");
        jo.put("c", 23543);
        jo.put("e", 23543);
        jo.put("f", 23543);
        jo.put("g", 23543);
        jo.put("ch", 23543);

        String joStr = jo.toJSONString();

        for(int i=0; i<count; i++){
            String str = JSON.toJSONString(m1);
        }

//        FtNameFilter nf = new FtNameFilter();
//        FtPropertyFilter pf = new FtPropertyFilter();
//        SerializeFilter[] sf = new SerializeFilter[]{nf, pf};

        long time1 = System.currentTimeMillis();
        for(int i=0; i<count; i++){
            String str = JSON.toJSONString(m1);
//            String str = JSON.toJSONString(m1, sf);
        }
        long time2 = System.currentTimeMillis();
        System.out.println(time2 - time1);
        time1 = time2;

        for(int i=0; i<count; i++){
            FtModel1 m = JSON.parseObject(ms1, FtModel1.class);
        }
        time2 = System.currentTimeMillis();
        System.out.println(time2 - time1);
        time1 = time2;

        for(int i=0; i<count; i++){
            String str = JSON.toJSONString(jo);
        }
        time2 = System.currentTimeMillis();
        System.out.println(time2 - time1);
        time1 = time2;

        for(int i=0; i<count; i++){
            JSONObject j = JSON.parseObject(joStr);
        }
        time2 = System.currentTimeMillis();
        System.out.println(time2 - time1);
        time1 = time2;






    }

    public static void doIt (Supplier s){
        long time1 = System.currentTimeMillis();
        for(int i=0; i< 10000000; i++){
            s.get();
        }

        System.out.println(System.currentTimeMillis() - time1);
    }
}
