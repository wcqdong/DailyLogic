package com.wcq.js;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileReader;
import java.io.IOException;

public class Js {
    public static void main(String[] args) throws IOException, ScriptException, NoSuchMethodException {
//        ScriptEngineManager engineManager = new ScriptEngineManager();
//        ScriptEngine engine = engineManager.getEngineByName("JavaScript");
//
//        String filePath = Js.class.getResource("/").getPath() + "../../src/main/resources/js/test.js";
////        FileReader fReader = new FileReader(filePath);
//        try (FileReader fReader = new FileReader(filePath)){
//            engine.eval(fReader);
//        }
//
//        Invocable inv = (Invocable) engine;
//        Js.A a = new Js.A();
//        JSModel jsModel = new JSModel();
//        Object result = inv.invokeFunction("tt", a);
//
//        System.out.println(result.toString());

        int a = 1;
        Integer b = 2;
        Object obj = new Object();
        System.out.println(int.class == Integer.TYPE);
//        System.out.println(int.class);
        test(a, b);
    }
    public static void test(Object a, Object b){
        System.out.println(a.getClass() == b.getClass());
    }

    public static class A{
        public int a;

        public int getA() {
            return a;
        }

        public void setA(int a) {
            this.a = a;
        }
    }
}
