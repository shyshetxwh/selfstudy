package cn.shyshetxwh.Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Comparator;
import java.util.function.Supplier;

public class ProxyTest2 {
    public static void main(String[] args) {
        Integer a=10;
        TraceHandler handler = new TraceHandler(a);
        Comparable proxy=(Comparable)Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{Comparable.class}, handler);
        Integer b=20;
        int i = proxy.compareTo(20);
        System.out.println(i);


    }
}
