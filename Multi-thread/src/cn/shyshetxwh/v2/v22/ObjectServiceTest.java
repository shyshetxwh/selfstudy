package cn.shyshetxwh.v2.v22;

/**
 * FileName: ObjectServiceTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/3 0003 20:42
 */

class ObjectService {
    public void serviceMethod() {
        try {
            synchronized (this) {
                System.out.println("begin time=" + System.currentTimeMillis());
                Thread.sleep(2000);
                System.out.println("end time=" + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Thread3 extends Thread {
    private ObjectService service;

    public Thread3(ObjectService service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.serviceMethod();
    }
}

class Thread4 extends Thread {
    private ObjectService service;

    public Thread4(ObjectService service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.serviceMethod();
    }
}

public class ObjectServiceTest {
    public static void main(String[] args) {
        ObjectService service = new ObjectService();

        Thread3 thread3 = new Thread3(service);
        thread3.setName("a");
        thread3.start();

        Thread4 thread4 = new Thread4(service);
        thread4.setName("b");
        thread4.start();
    }
}
