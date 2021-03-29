package cn.shyshetxwh.v2.v23;

/**
 * FileName: SynchronizedUpdateNewValue
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/4 0004 22:18
 */

class Service {
    private boolean isContinueRun = true;

    public void runMethod() {
        /*String anyString = new String();*/
        while (isContinueRun == true) {
            /*synchronized (anyString){}*/
        }
        System.out.println("stop!!!");
    }

    public void stopMethod() {
        isContinueRun = false;
    }
}

class Thread1 extends Thread {
    private Service service;

    public Thread1(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.runMethod();
    }
}

class Thread2 extends Thread {
    private Service service;

    public Thread2(Service service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.stopMethod();
    }
}

public class SynchronizedUpdateNewValue {
    public static void main(String[] args) throws InterruptedException {
        Service service = new Service();

        new Thread1(service).start();

        Thread.sleep(1000);

        new Thread2(service).start();

        System.out.println("send stop order");
    }
}
