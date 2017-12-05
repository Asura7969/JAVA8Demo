package com.java8;

import org.junit.Test;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by root on 2017/12/3.
 *
 */
public class TestJava8 {

    /**
     * Predicate接口
     * 输入一个参数，并返回一个Boolean值，其中内置许多用于逻辑判断的默认方法:
     */
    @Test
    public void predicateTest() {
        Predicate<String> predicate = (s) -> s.length() > 0;
        boolean test = predicate.test("test");
        System.out.println("字符串长度大于0:" + test);

        test = predicate.test("");
        System.out.println("字符串长度大于0:" + test);

        test = predicate.negate().test("");
        System.out.println("字符串长度小于0:" + test);

        Predicate<Object> pre = Objects::nonNull;
        Object ob = null;
        test = pre.test(ob);
        System.out.println("对象不为空:" + test);
        ob = new Object();
        test = pre.test(ob);
        System.out.println("对象不为空:" + test);
    }


    /**
     * Function接口
     * 接收一个参数，返回单一的结果，默认的方法（andThen）可将多个函数串在一起，形成复合Funtion（有输入，有输出）结果，
     */
    @Test
    public  void functionTest() {
        Function<String, Integer> toInteger = Integer::valueOf;
        //toInteger的执行结果作为第二个backToString的输入
        Function<String, String> backToString = toInteger.andThen(String::valueOf);
        String result = backToString.apply("1234");
        System.out.println(result);

        Function<Integer, Integer> add = (i) -> {
            System.out.println("frist input:" + i);
            return i * 2;
        };
        Function<Integer, Integer> zero = add.andThen((i) -> {
            System.out.println("second input:" + i);
            return i * 0;
        });

        Integer res = zero.apply(8);
        System.out.println(res);
    }


    /**
     *Supplier接口
     * 返回一个给定类型的结果，与Function不同的是，Supplier不需要接受参数(供应者，有输出无输入)
     */
    @Test
    public void supplierTest() {
        Supplier<String> supplier = () -> "special type value";
        String s = supplier.get();
        System.out.println(s);
    }

    /**
     *Consumer接口
     * 代表了在单一的输入参数上需要进行的操作。和Function不同的是，Consumer没有返回值(消费者，有输入，无输出)
     */
    @Test
    public void consumerTest() {
        Consumer<Integer> add5 = (p) -> {
            System.out.println("old value:" + p);
            p = p + 5;
            System.out.println("new value:" + p);
        };
        add5.accept(10);
    }

    /**
     * Stream
     */
    //========================================过滤器（Filter）======================================
    @Test
    public void streamFilterTest() {

        List<String> lists=new ArrayList<String>();
        lists.add("a1");
        lists.add("a2");
        lists.add("b1");
        lists.add("b2");
        lists.add("b3");
        lists.add("o1");

        lists.stream().filter((s -> s.startsWith("a"))).forEach(System.out::println);

        //等价于以上操作
        Predicate<String> predicate = (s) -> s.startsWith("a");
        lists.stream().filter(predicate).forEach(System.out::println);

        //连续过滤
        Predicate<String> predicate1 = (s -> s.endsWith("1"));
        lists.stream().filter(predicate).filter(predicate1).forEach(System.out::println);




    }
    //========================================排序（Sorted）======================================
    @Test
    public void streamSortedTest() {
        List<String> lists=new ArrayList<String>();
        lists.add("a1");
        lists.add("a2");
        lists.add("b1");
        lists.add("b2");
        lists.add("b3");
        lists.add("o1");

        System.out.println("默认Comparator");
        lists.stream().sorted().filter((s -> s.startsWith("a"))).forEach(System.out::println);

        System.out.println("自定义Comparator");
        lists.stream().sorted((p1, p2) -> p2.compareTo(p1)).filter((s -> s.startsWith("a"))).forEach(System.out::println);

    }

    //========================================映射（Map）======================================
    @Test
    public void streamMapTest() {
        List<String> lists=new ArrayList<String>();
        lists.add("a1");
        lists.add("a2");
        lists.add("b1");
        lists.add("b2");
        lists.add("b3");
        lists.add("o1");
        lists.stream().map(String::toUpperCase).sorted((a, b) -> b.compareTo(a)).forEach(System.out::println);

        System.out.println("自定义映射规则");
        Function<String, String> function = (p) -> {
            return p + ".txt";
        };
        lists.stream().map(String::toUpperCase).map(function).sorted((a, b) -> b.compareTo(a)).forEach(System.out::println);

    }

    //========================================匹配（Match）======================================
    @Test
    public void streamMatchTest() {
        List<String> lists=new ArrayList<String>();
        lists.add("a1");
        lists.add("a2");
        lists.add("b1");
        lists.add("b2");
        lists.add("b3");
        lists.add("o1");
        //流对象中只要有一个元素匹配就返回true
        boolean anyStartWithA = lists.stream().anyMatch((s -> s.startsWith("a")));
        System.out.println(anyStartWithA);
        //流对象中每个元素都匹配就返回true
        boolean allStartWithA
                = lists.stream().allMatch((s -> s.startsWith("a")));
        System.out.println(allStartWithA);
    }

    //========================================收集（Collect）======================================
    @Test
    public void streamCollectTest() {
        List<String> lists=new ArrayList<String>();
        lists.add("a1");
        lists.add("a2");
        lists.add("b1");
        lists.add("b2");
        lists.add("b3");
        lists.add("o1");
        List<String> list = lists.stream().filter((p) -> p.startsWith("a")).sorted().collect(Collectors.toList());
        System.out.println(list);

    }

    //========================================计数（Count）======================================
    @Test
    public void streamCountTest() {
        List<String> lists=new ArrayList<String>();
        lists.add("a1");
        lists.add("a2");
        lists.add("b1");
        lists.add("b2");
        lists.add("b3");
        lists.add("o1");
        long count = lists.stream().filter((s -> s.startsWith("a"))).count();
        System.out.println(count);
    }

    //========================================规约（Reduce）======================================
    @Test
    public void streamReduceTest() {
        List<String> lists=new ArrayList<String>();
        lists.add("a1");
        lists.add("a2");
        lists.add("b1");
        lists.add("b2");
        lists.add("b3");
        lists.add("o1");
        Optional<String> optional = lists.stream().sorted().reduce((s1, s2) -> {
            System.out.println(s1 + "|" + s2);
            return s1 + "|" + s2;
        });
    }





    //============================并行Stream VS 串行Stream============================

    /**
     * 测试串行流下排序所用的时间：
     */
    @Test
    public void notParallelStreamSortedTest() {

        List<String> bigLists = new ArrayList<>();
        for (int i = 0; i < 10000000; i++) {
            UUID uuid = UUID.randomUUID();
            bigLists.add(uuid.toString());
        }


        long startTime = System.nanoTime();
        long count = bigLists.stream().sorted().count();
        long endTime = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        System.out.println(System.out.printf("串行排序: %d ms", millis));
        //串行排序: 12001 msjava.io.PrintStream@ed17bee

    }



    /**
     * 测试并行流下排序所用的时间：
     */
    @Test
    public void parallelStreamSortedTest() {
        List<String> bigLists = new ArrayList<>();
        for (int i = 0; i < 10000000; i++) {
            UUID uuid = UUID.randomUUID();
            bigLists.add(uuid.toString());
        }


        long startTime = System.nanoTime();
        long count = bigLists.parallelStream().sorted().count();
        long endTime = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        System.out.println(System.out.printf("并行排序: %d ms", millis));
        //并行排序: 6420 msjava.io.PrintStream@12bb4df8

    }





    //===================================懒操作==================================

    /**
     * 上面我们谈到Stream尽可能以延迟的方式运行，这里通过创建一个无穷大的Stream来说明：
     * 首先通过Stream的generate方法来一个自然数序列，然后通过map变换Stream：
     */


    /**
     * 我们发现开始时对这个无穷大的Stream做任何中间操作（如：filter,map等，但sorted不行）都是可以的，
     * 也就是对Stream进行中间操作并生存一个新的Stream的过程并非立刻生效的（不然此例中的map操作会永远的运行下去，被阻塞住），
     * 当遇到完结方法时stream才开始计算。通过limit()方法，把这个无穷的Stream转为有穷的Stream。
     */


    //递增序列
    class NatureSeq implements Supplier<Long> {
        long value = 0;

        @Override
        public Long get() {
            value++;
            return value;
        }
    }
    @Test
    public void streamCreateTest() {
        Stream<Long> stream = Stream.generate(new NatureSeq());
        System.out.println("元素个数："+stream.map((param) -> {
            return param;
        }).limit(1000).count());

    }



}
