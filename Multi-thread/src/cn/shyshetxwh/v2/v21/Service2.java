package cn.shyshetxwh.v2.v21;

/**
 * FileName: Service2
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/3 0003 19:04
 */

class Thread8 extends Thread {
    private Service2 service;

    public Thread8(Service2 service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.testMethod();
    }
}

class Thread9 extends Thread {
    private Service2 service;

    public Thread9(Service2 service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.testMethod();
    }
}

public class Service2 {
    public synchronized void testMethod() {
        if (Thread.currentThread().getName().equals("a")) {
            System.out.println("ThreadName=" + Thread.currentThread().getName() +
                    " run beginTime=" + System.currentTimeMillis());
            int i = 1;
            while (i == 1) {
                if (("" + Math.random()).substring(0, 8).equals("0.123456")) {
                    System.out.println("ThreadName="
                            + Thread.currentThread().getName()
                            + " run exceptionTime="
                            + System.currentTimeMillis());
                    Integer.parseInt("a");
                }
            }
        } else {
            System.out.println("Thread B run Time=" + System.currentTimeMillis());
        }
    }

    public static void main(String[] args) {
        try {
            Service2 service = new Service2();

            Thread8 thread8 = new Thread8(service);
            thread8.setName("a");
            thread8.start();
            Thread.sleep(500);

            Thread9 thread9 = new Thread9(service);
            thread9.setName("b");
            thread9.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
