package cn.shyshetxwh.GenericReflection;

import java.lang.reflect.*;
import java.util.Arrays;
import java.util.Scanner;

public class GenericReflectionTest {
    public static void main(String[] args) {
        String name;
        if(args.length>0)
        {
            name=args[0];
        }
        else
        {
            //System.out.println("Enter class name:");
            //name=new Scanner(System.in).next();
//            name="cn.shyshetxwh.GenericReflection.ArrayAlg";
            name="java.util.stream.Stream";
        }
        try
        {
            Class<?> cls = Class.forName(name);
            printClass(cls);
            Method[] methods = cls.getDeclaredMethods();
            for (Method method : methods) {
                printMethod(method);
            }
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    private static void printMethod(Method method) {
        String name=method.getName();
        System.out.print(Modifier.toString(method.getModifiers()));
        System.out.print(" ");
        printTypes(method.getTypeParameters(),"<",", ",">",true);

        printType(method.getGenericReturnType(),false);
        System.out.print(" ");
        System.out.print(name);
        System.out.print("(");
        printTypes(method.getGenericParameterTypes(),"",", ","",false);
        System.out.println(")");
    }

    private static void printClass(Class<?> cls) {
        System.out.print(cls);
        printTypes(cls.getTypeParameters(),"<",", ",">",true);
        Type sc = cls.getGenericSuperclass();
        if(sc!=null)
        {
            System.out.print(" extends ");
            printType(sc,false);
        }
        printTypes(cls.getGenericInterfaces()," implements ",", ","",false);
        System.out.println();
    }

    public static void printType(Type type,boolean isDefinition)
    {
        //如果是类
        if(type instanceof Class)
        {
            Class<?> t = (Class<?>) type;
            System.out.print(t.getName());
        }

        //如果是类型变量
        if(type instanceof TypeVariable)
        {
            TypeVariable<?> t = (TypeVariable<?>) type;
            System.out.print(t.getName());
            if(isDefinition)
            {
                printTypes(t.getBounds()," extends "," & ","",false);
            }
        }

        //如果是通配符
        else if(type instanceof WildcardType)
        {
            WildcardType t = (WildcardType) type;
            System.out.print("?");
            printTypes(t.getUpperBounds()," extends "," & ","",false);
            printTypes(t.getLowerBounds()," super "," & ","",false);
        }

        //如果是泛类或者接口
        else if(type instanceof ParameterizedType)
        {
            //System.out.println("flag........");
            ParameterizedType t = (ParameterizedType) type;
            Type owner = t.getOwnerType();
            if(owner!=null)
            {
                printType(owner,false);
            }
            printType(t.getRawType(),false);
            printTypes(t.getActualTypeArguments(),"<",", ",">",false);

        }

        //如果是泛型数组
        else if(type instanceof GenericArrayType)
        {
            GenericArrayType t = (GenericArrayType) type;
            System.out.print("");
            printType(t.getGenericComponentType(),isDefinition);
            System.out.print("[]");
        }
    }

    private static void printTypes(Type[] types,String pre,String sep,String suf,boolean isDefinition) {
        if(pre.equals(" extends ")&& Arrays.equals(types,new Type[] {Object.class}))
        {
            return;
        }
        if (types.length>0)
        {
            System.out.print(pre);
        }
        for (int i = 0; i < types.length; i++) {
            if(i>0)
            {
                System.out.print(sep);
            }
            printType(types[i],isDefinition);
        }
        if (types.length>0)
        {
            System.out.print(suf);
        }
    }
}
