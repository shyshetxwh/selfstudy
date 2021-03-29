package cn.shyshetxwh.v2.v21;

/**
 * FileName: Service
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/3 0003 18:47
 */

class Thread6 extends Thread {
    @Override
    public void run() {
        Service service = new Service();
        service.service1();
    }
}

public class Service {
    public synchronized void service1() {
        System.out.println("service1");
        service2();
    }

    private synchronized void service2() {
        System.out.println("service2");
        service3();
    }

    private synchronized void service3() {
        System.out.println("service3");
    }

    public static void main(String[] args) {
        new Thread6().start();
    }
}
