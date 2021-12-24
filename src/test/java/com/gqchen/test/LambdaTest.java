package com.gqchen.test;

import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * lambda表达式学习测试 & 四大常用的函数式接口
 */
public class LambdaTest {
    /**
     * 1.消费型函数式接口,有参数无返回值
     */
    @Test
    public void consumerTest() {
        /*
         * 当参数只有一个时，参数的'()'可省略
         */
        Consumer<String> consumer = (t) -> System.out.println("lambdaTest:"+t);
        Consumer<String> consumer1 = t -> System.out.println("lambdaTest:"+t);
        consumer.accept("hello world!");
        consumer1.accept("hello world!");
    }
    /**
     * 2.供给型函数式接口,无参数有返回值
     */
    @Test
    public void supplierTest() {
        Supplier<String> supplier = () -> "hello world!";
        System.out.println(supplier.get());
    }
    /**
     * 3.功能型函数式接口,有参数有返回值
     */
    @Test
    public void functionTest() {
        Function<String,String> function = t -> "FunctionTest:"+t;
        System.out.println(function.apply("hello world!"));
    }
    /**
     * 4.断言型函数式接口,有参数有返回值,返回值时布尔类型
     */
    @Test
    public void predicateTest() {
        Predicate<String> predicate = t -> "hello world!".equals(t);
        System.out.println(predicate.test("hello world!"));
    }
}
