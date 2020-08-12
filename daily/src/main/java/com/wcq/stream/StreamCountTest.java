package com.wcq.stream;

import java.util.Map;
import java.util.stream.Stream;

/**
 * stream 的api陷阱
 */
public class StreamCountTest {
    public static void main(String[] args) {
//        long count = Stream.of(1, 2, 3, 4, 5)
//                .map(i -> print(i))
//                .peek(System.out::println)
//                // 加上filter，map和peek才会走
//                // 原因：count()的优化，count认为没有改变集合长度的操作，那么中间的其他操作不会走，除非加上filter等改变长度的操作
////                .filter(i -> i > 0)
//                .count();
//        System.out.println(count);
        System.out.println(Map.class.getSimpleName());
    }

    public static int print(int i){
        System.out.println("---" + i);
        return i + 1;
    }
}
