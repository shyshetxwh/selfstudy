package cn.shyshetxwh.v1.test;

import cn.shyshetxwh.v1.MyService;
import cn.shyshetxwh.v1.MyServiceThread1;
import cn.shyshetxwh.v1.MyServiceThread2;

/**
 * FileName: MyServiceTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/3 0003 8:24
 */
public class MyServiceTest {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        try {
            MyService service = new MyService();
            MyServiceThread1 thread1 = new MyServiceThread1(service);
            thread1.start();
            Thread.sleep(100);
            MyServiceThread2 thread2 = new MyServiceThread2(service);
            thread2.start();
            Thread.sleep(3000);

            thread1.stop();
            System.out.println("stop执行后，开始打印");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
