package com.wcq.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;


/** Intermediate：
        map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、 peek、 limit、 skip、 parallel、 sequential、 unordered
    Terminal：
        forEach、 forEachOrdered、 toArray、 reduce、 collect、 min、 max、 count、 anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 iterator
    Short-circuiting：
        anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 limit

 */

public class StreamTest {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(4, 1, 2, 5,7);

        /**  IntStream LongStream DoubleStream 无需boxing unboxing*/
        // 1-9
        IntStream.range(1, 10).forEach(System.out::println);
        // 1-10
        IntStream.rangeClosed(1, 10).forEach(System.out::println);
        // 枚举
        IntStream.of(1, 3, 4).forEach(System.out::println);

        /** map */
        List<Integer> nums = Arrays.asList(1, 2, 3, 4);
        List<Integer> squareNums = nums.stream().
                map(n -> n * n).
                collect(Collectors.toList());

        /** flatMap 元素是集合 把所有集合平铺*/
        Stream<List<Integer>> inputStream = Stream.of(
                Arrays.asList(1),
                Arrays.asList(2, 3),
                Arrays.asList(4, 5, 6)
        );
        Stream<Integer> outputStream = inputStream.flatMap((childList) -> childList.stream());

        /** 筛数据 */
        Integer[] sixNums = {1, 2, 3, 4, 5, 6};
        Integer[] evens = Stream.of(sixNums).filter(n -> n%2 == 0).toArray(Integer[]::new);

        /** peek 类似forEach */
        Stream.of("one", "two", "three", "four")
                .filter(e -> e.length() > 3)
                .peek(e -> System.out.println("Filtered value: " + e))
                .map(String::toUpperCase)
                .peek(e -> System.out.println("Mapped value: " + e))
                .collect(Collectors.toList());

        /** reduce 把元素组合起来 */
        // 字符串连接，concat = "ABCD"
        String concat = Stream.of("A", "B", "C", "D").reduce("", String::concat);
        // 求最小值，minValue = -3.0
        double minValue = Stream.of(-1.5, 1.0, -3.0, -2.0).reduce(Double.MAX_VALUE, Double::min);
        // 求和，sumValue = 10, 有起始值
        int sumValue = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
        // 求和，sumValue = 10, 无起始值
        sumValue = Stream.of(1, 2, 3, 4).reduce(Integer::sum).get();
        // 过滤，字符串连接，concat = "ace"
        concat = Stream.of("a", "B", "c", "D", "e", "F").
                filter(x -> x.compareTo("Z") > 0).
                reduce("", String::concat);

        /** limit前几个  skip跳过前几个 */
        Stream.of("A", "B", "C", "D").limit(3).forEach(System.out::println);
        Stream.of("A", "B", "C", "D").skip(3).forEach(System.out::println);

        /** allMatch anyMatch */
        List<Person> persons = new ArrayList();
        persons.add(new Person(1, "name" + 1, 10));
        persons.add(new Person(2, "name" + 2, 21));
        persons.add(new Person(3, "name" + 3, 34));
        persons.add(new Person(4, "name" + 4, 6));
        persons.add(new Person(5, "name" + 5, 55));
        boolean isAllAdult = persons.stream().allMatch(p -> p.getAge() > 18);
        System.out.println("All are adult? " + isAllAdult);
        boolean isThereAnyChild = persons.stream().anyMatch(p -> p.getAge() < 12);
        System.out.println("Any child? " + isThereAnyChild);
        boolean isNone = persons.stream().noneMatch(p -> p.getAge() < 18);
        System.out.println("none child? " + isThereAnyChild);

        /** iterate 按照n+3的规律生成10个元素的stream*/
        Stream.iterate(0, n -> n + 3).limit(10). forEach(x -> System.out.print(x + " "));


        /** Collectors.groupingBy 按照key给list分组成map */
        Map<Integer, List<Person>> personGroups = Stream.generate(new PersonSupplier()).
                limit(100).
                collect(Collectors.groupingBy(Person::getAge));
        Iterator it = personGroups.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<Integer, List<Person>> pers = (Map.Entry) it.next();
            System.out.println("Age " + pers.getKey() + " = " + pers.getValue().size());
        }


        /** Collectors.partitioningBy 按照条件给list分组成map */
        Map<Boolean, List<Person>> children = Stream.generate(new PersonSupplier()).
                limit(100).
                collect(Collectors.partitioningBy(p -> p.getAge() < 18));
        System.out.println("Children number: " + children.get(true).size());
        System.out.println("Adult number: " + children.get(false).size());

    }
}
