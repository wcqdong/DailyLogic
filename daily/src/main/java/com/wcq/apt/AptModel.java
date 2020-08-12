package com.wcq.apt;

@AnnotationApt("tt")
public class AptModel {

    @AnnotationApt("this is a field")
    String hello;

    @AnnotationApt("this is a method")
    public String say(@AnnotationApt("this is a parameter") String arg1) {
        return "hello world";
    }
}
