package cn.shyshetxwh.Proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class TraceHandler implements InvocationHandler {

    private Object target;

    public TraceHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //打印隐式参数
        System.out.print(target);
        //打印方法名
        System.out.print("."+method.getName()+"(");
        //打印显示参数
        if(args!=null)
        {
            for (int i = 0; i < args.length; i++) {
                if(i>0)
                {
                    System.out.print(", ");
                }
                System.out.print(args[i]);
            }
        }
        System.out.println(")");
        return method.invoke(target,args);
    }
}
