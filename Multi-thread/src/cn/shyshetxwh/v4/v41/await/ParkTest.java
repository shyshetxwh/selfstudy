package cn.shyshetxwh.v4.v41.await;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * FileName: ParkTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/7 0007 15:10
 */
public class ParkTest {
    public static void main(String[] args) throws Exception {
        Field f = Unsafe.class.getDeclaredField("theUnsafe");
        f.setAccessible(true);
        Unsafe unsafe = (Unsafe) f.get(null);
        System.out.println("begin " + System.currentTimeMillis());

        //unsafe.park(true, System.currentTimeMillis() + 2000);
//        unsafe.park(false, 2000000000L);
//        unsafe.park(true, 0L);
        unsafe.park(false, 0L);


        System.out.println("  end " + System.currentTimeMillis());
    }
}
