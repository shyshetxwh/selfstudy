package chapter08;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * FileName: MethodHandleTest1
 * Author:   admin+shyshetxwh
 * Date:     2021/03/03 19:36
 */
public class MethodHandleTest1 {
    class GrandFather {
        void thinking() {
            System.out.println("i am grandfather");
        }
    }

    class Father extends GrandFather {
        @Override
        void thinking() {
            System.out.println("i am father");
        }
    }

    class Son extends Father {
        @Override
        void thinking() {
            try {
                MethodType mt = MethodType.methodType(void.class);
                MethodHandle mh = MethodHandles.lookup().findSpecial(GrandFather.class, "thinking", mt, getClass());
                mh.invoke(this);
            } catch (Throwable throwable) {
                //throwable.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new MethodHandleTest1().new Son().thinking();
    }
}
