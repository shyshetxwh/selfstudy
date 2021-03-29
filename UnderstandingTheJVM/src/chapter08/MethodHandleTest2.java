package chapter08;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Field;

/**
 * FileName: MethodHandleTest1
 * Author:   admin+shyshetxwh
 * Date:     2021/03/03 19:36
 */
public class MethodHandleTest2 {
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
                Field impl_lookup = MethodHandles.Lookup.class.getDeclaredField("IMPL_LOOKUP");
                impl_lookup.setAccessible(true);
                MethodHandle mh = ((MethodHandles.Lookup) impl_lookup.get(null)).findSpecial(GrandFather.class, "thinking", mt, GrandFather.class);
                mh.invoke(this);

            } catch (Throwable throwable) {
                //throwable.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new MethodHandleTest2().new Son().thinking();
    }
}
