package cn.shyshetxwh.v2.v22;

/**
 * FileName: InnerClassTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/4 0004 16:06
 */

class OutClass {
    static class InnerClass1 {
        public void method1(InnerClass2 class2) {
            String threadName = Thread.currentThread().getName();
            synchronized (class2) {
                System.out.println(threadName + " enter InnerClass1.method1");
                for (int i = 0; i < 10; i++) {
                    System.out.println("i = " + i);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(threadName + " exit InnerClass1.method1");
            }
        }

        public synchronized void method2() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " enter InnerClass1.method2");
            for (int j = 0; j < 10; j++) {
                System.out.println("j = " + j);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(threadName + " exit InnerClass1.method2");
        }
    }

    static class InnerClass2 {
        public synchronized void method1() {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " enter InnerClass2.method1");
            for (int k = 0; k < 10; k++) {
                System.out.println("k = " + k);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(threadName + " exit InnerClass2.method1");
        }
    }
}

public class InnerClassTest {
    public static void main(String[] args) {
        /*final*/
        OutClass.InnerClass1 in1 = new OutClass.InnerClass1();
        /*final*/
        OutClass.InnerClass2 in2 = new OutClass.InnerClass2();

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                in1.method1(in2);
            }
        }, "T1");

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                in1.method2();
            }
        }, "T2");

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                in2.method1();
            }
        }, "T3");

        t1.start();
        t2.start();
        t3.start();
    }
}
