package cn.shyshetxwh.GenericReflection;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;

public class CodeTest {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException {
        Class<?> cls = Class.forName("java.util.stream.Stream");
        Method m = cls.getMethod("toArray");
//        TypeVariable<Method>[] t1 = m.getTypeParameters();
        Type t2 = m.getGenericReturnType();
//        Type[] t3 = m.getGenericParameterTypes();
//        for (TypeVariable<Method> t : t1) {
//            System.out.println(t);
//        }
//        System.out.println("===");
//        System.out.println("===");
//        for (Type t : t3) {
//            System.out.println(t);
        String s = t2.getClass().getName() + "@" + Integer.toHexString(t2.hashCode());

        String s1 = t2.toString();
        System.out.println(s1);
    }



    }

