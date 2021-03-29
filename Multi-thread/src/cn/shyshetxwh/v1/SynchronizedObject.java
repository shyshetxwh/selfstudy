package cn.shyshetxwh.v1;

/**
 * FileName: SynchronizedObject
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/3 0003 9:04
 */
public class SynchronizedObject {
    public synchronized void printString() {
        System.out.println("begin");
        if (Thread.currentThread().getName().equals("a")) {
            System.out.println("a线程用于暂停了");
            Thread.currentThread().suspend();
        }
        System.out.println("end");
    }

    public static void main(String[] args) {
        try {
            final SynchronizedObject obj = new SynchronizedObject();

            Thread t1 = new Thread() {
                @Override
                public void run() {
                    obj.printString();
                }
            };
            t1.setName("a");
            t1.start();

            Thread.sleep(1000);

            Thread t2 = new Thread() {
                @Override
                public void run() {
                    obj.printString();
                }
            };
            t2.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
