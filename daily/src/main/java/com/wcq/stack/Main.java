package com.wcq.stack;

public class Main {
    public static void main(String[] args) {
        test1();
    }
    public static void test1(){
        test2();
    }

    private static void test2() {
        print();
//        System.out.println(getCallerInfo());
    }
    public static void print(){
        StringBuffer sbf = new StringBuffer();
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if(stackTrace != null){
            for(int i=0; i<stackTrace.length; ++i){
                sbf.append(stackTrace[i]).append("\n");
            }
        }
        System.out.println(sbf);
    }

    public static String getCallerInfo() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement e = stackTrace[3];
        return new StringBuilder().append(e.getFileName()).append(":")
                .append(e.getLineNumber()).append(" ")
                .append(e.getMethodName()).toString();
    }

}
