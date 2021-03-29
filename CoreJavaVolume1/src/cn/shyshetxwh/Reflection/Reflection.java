package cn.shyshetxwh.Reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Reflection {
    /**
     * 打印一个类的类名和其父类及接口
     * @param cls 类的反射
     */
    public static void printClassname(Class cls)  {
        String r="";
        Class supercls = cls.getSuperclass();
        Class[] interfaces = cls.getInterfaces();
        int mod = cls.getModifiers();
        String modifiers = Modifier.toString(mod);
        String s = modifiers.length() > 0 ? modifiers : "";
        r+=s+" class "+cls.getName();
        if(supercls!=null&&supercls!=Object.class)
        {
            r+=" extends "+supercls.getName();
        }
        if(interfaces.length>0)
        {
            r+=" implements ";
            for (Class anInterface : interfaces) {
                r+=anInterface.getName()+",";
            }
            r = r.substring(0,r.length()-1);

        }
        System.out.println(r);
    }

    /**
     * 打印一个类所有的构造方法
     * @param cls 一个类的反射
     */
    public static void printConstructs(Class cls) {

        Constructor[] dc = cls.getDeclaredConstructors();
        for (Constructor c : dc) {
            System.out.print("  ");
            String mod = Modifier.toString(c.getModifiers());
            if(mod.length()>0)
            {
                System.out.print(mod+" ");
            }
            String cName = c.getName();
            System.out.print(cName+"(");
            Class[] pt = c.getParameterTypes();
            for (int i = 0; i < pt.length; i++) {
                if(i>0)
                {
                    System.out.print(",");
                }
                System.out.print(pt[i].getName());
            }
            System.out.println(");");

        }

    }

    /**
     * 打印一个类的所有方法
     * @param cls 一个类的反射
     */
    public static void printMethods(Class cls)
    {
        Method[] dm = cls.getDeclaredMethods();
        for (Method m : dm) {
            String mod = Modifier.toString(m.getModifiers());
            Class rt = m.getReturnType();
            String mName = m.getName();
            Class[] pt = m.getParameterTypes();
            System.out.print("  ");
            if(mod.length()>0)
            {
                System.out.print(mod+" ");
            }
            System.out.print(rt.getName()+" "+mName+"(");
            for (int i = 0; i < pt.length; i++) {
                if(i>0)
                {
                    System.out.print(",");
                }
                System.out.print(pt[i].getName());
            }
            System.out.println(");");
        }
    }

    /**
     * 打印一个类所有字段
     * @param cls 一个类的反射
     */
    public static void printFields(Class cls)
    {
        Field[] df = cls.getDeclaredFields();
        for (Field f : df) {
            String mod = Modifier.toString(f.getModifiers());
            Class type = f.getType();
            String fName = f.getName();
            System.out.print("  ");
            if(mod.length()>0)
            {
                System.out.print(mod+" ");
            }
            System.out.println(type.getName()+" "+fName+";");
        }
    }

    /**
     * 打印一个类的所有信息
     * @param name  类名
     * @throws ClassNotFoundException 类未找到异常
     */
    public static void printClass(String name) throws ClassNotFoundException {
        Class cls = Class.forName(name);
        printClassname(cls);
        System.out.println("{");
        printConstructs(cls);
        System.out.println();
        printMethods(cls);
        System.out.println();
        printFields(cls);
        System.out.println("}");
    }
}
