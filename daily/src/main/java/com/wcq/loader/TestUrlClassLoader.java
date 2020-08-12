package com.wcq.loader;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Objects;

public class TestUrlClassLoader {
    public static void main(String[] args) {
        try {
            URLClassLoader ucl = new URLClassLoader(new URL[]{new URL("file:\\d:\\tt\\")});
            Class<?> clazz = ucl.loadClass("com.wcq.loader.T");
            Constructor<?> constructor = clazz.getConstructor();
            Object obj = constructor.newInstance(null);

//            Object obj = clazz.newInstance();
            Method method = clazz.getMethod("excute");
            Object result = method.invoke(obj);
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
