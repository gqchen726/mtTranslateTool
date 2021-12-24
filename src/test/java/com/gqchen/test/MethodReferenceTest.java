package com.gqchen.test;

import com.gqchen.bean.MethodReference;
import com.gqchen.bean.Trans_Result;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 方法引用学习测试
 */
public class MethodReferenceTest {
    private Logger logger = Logger.getLogger(MethodReferenceTest.class);





    /*
     * 方法引用
     * 对象::实例方法
     * 类::静态方法
     * 类::实例方法
     */
    @Test
    public void test1() {
        Consumer<String> consumer1 = (t) -> System.out.println(t);
        Consumer<String> consumer = System.out::println;
        System.out.print("lambda:");
        consumer1.accept("hello!");
        System.out.print("methodReference:");
        consumer.accept("hello!");
        System.out.println("--------------------------");

        /*
         * Integer 的 compare方法 t > u res=1,t < u res=-1;
         */
        BiFunction<Integer,Integer,Integer> biFunction1 = (x,y) -> Integer.compare(x,y);
        BiFunction<Integer,Integer,Integer> biFunction = Integer::compare;
        System.out.println("lambda:"+biFunction1.apply(64164156,21851913));
        System.out.println("methodReference:"+biFunction.apply(64164156,81851913));
        System.out.println("--------------------------");

        BiFunction<String,String,Boolean> biFunction2 = (str1,str2) -> str1.equals(str2);
        BiFunction<String,String,Boolean> biFunction3 = String::equals;
        System.out.println(biFunction2.apply("hello!","hello!"));
        System.out.println(biFunction3.apply("hello!","hello!"));
        System.out.println("--------------------------");

        Consumer<Integer> consumer2 = logger::info;
        consumer2.accept(biFunction.apply(646465168,684351684));
    }

    /**
     * 构造器引用
     * 类名::new
     */
    @Test
    public void test2() {
        /*
         * 构造方法方法的引用,无参
         */
        Supplier<MethodReference> supplier = () -> new MethodReference();
        System.out.println(supplier.get());
        Supplier<MethodReference> supplier1 = MethodReference::new;
        /*
         * 构造方法的引用,一参
         */
        Function<String, MethodReference> function = (t) -> new MethodReference(t);
        System.out.println(function.apply("test"));
        Function<String,MethodReference> function1 = MethodReference::new;
        System.out.println(function1.apply("test1"));
    }

    /**
     * 数组引用
     * Type[]::new
     */
    @Test
    public void test3() {
        Function<Integer,String[]> function = (size) -> new String[size];
        Function<Integer,String[]> function1 = String[]::new;
        System.out.println(function.apply(5));
        System.out.println(function1.apply(10));
    }
}