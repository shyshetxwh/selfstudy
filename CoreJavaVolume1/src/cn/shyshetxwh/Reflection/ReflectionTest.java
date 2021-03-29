package cn.shyshetxwh.Reflection;

import java.util.Scanner;

public class ReflectionTest {
    public static void main(String[] args) throws ClassNotFoundException {
        String name;
        if(args.length>0)
        {
            name=args[0];
        }
        else
        {
            Scanner sc=new Scanner(System.in);
            System.out.println("请输入你想查询的类，比如java.util.Date");
            name = sc.next();
        }

        Reflection.printClass(name);

    }
}
