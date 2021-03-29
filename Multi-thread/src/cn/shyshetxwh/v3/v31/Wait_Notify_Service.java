package cn.shyshetxwh.v3.v31;

import java.util.ArrayList;
import java.util.List;

/**
 * FileName: Wait_Notify_Service
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/5 0005 16:46
 */

class MyList {
    private volatile List list = new ArrayList();

    public void add() {
        list.add("anyString");
    }

    public int size() {
        return list.size();
    }
}

class MyService {
    private Object lock = new Object();
    private MyList list = new MyList();

    public void waitMethod() {
        try {
            synchronized (lock) {
                if (list.size() != 5) {
                    System.out.println("begin wait " + System.currentTimeMillis()
                            + "  " + Thread.currentThread().getName());
                    lock.wait();
                    System.out.println("end wait " + System.currentTimeMillis()
                            + "  " + Thread.currentThread().getName());
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void notifyMethod() {
        try {
            synchronized (lock) {
                System.out.println("begin notify " + System.currentTimeMillis()
                        + "  " + Thread.currentThread().getName());
                for (int i = 0; i < 10; i++) {
                    list.add();
                    if (list.size() == 5) {
                        lock.notify();
                        System.out.println("send the notify,but not release the lock");
                    }
                    System.out.println("add timer: " + (i + 1));
                    Thread.sleep(1000);
                }
                System.out.println("end notify " + System.currentTimeMillis()
                        + "  " + Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Thread5 extends Thread {
    private MyService service;

    public Thread5(MyService service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.waitMethod();
    }
}

class Thread6 extends Thread {
    private MyService service;

    public Thread6(MyService service) {
        this.service = service;
    }

    @Override
    public void run() {
        service.notifyMethod();
    }
}


@SuppressWarnings("unchecked")
public class Wait_Notify_Service {
    public static void main(String[] args) throws InterruptedException {
        MyService service = new MyService();

        new Thread5(service).start();

        Thread.sleep(5000);

        new Thread6(service).start();
    }
}
