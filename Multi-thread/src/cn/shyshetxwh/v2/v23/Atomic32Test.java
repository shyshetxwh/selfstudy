package cn.shyshetxwh.v2.v23;

/**
 * FileName: Atomic32Test
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/4 0004 22:31
 */

class AtomicService {
    public /*volatile*/ long i;
}

class Thread3 extends Thread {
    private AtomicService service;

    public Thread3(AtomicService service) {
        this.service = service;
    }

    @Override
    public void run() {
        while (true) {
            service.i = 1;
        }
    }
}

class Thread4 extends Thread {
    private AtomicService service;

    public Thread4(AtomicService service) {
        this.service = service;
    }

    @Override
    public void run() {
        while (true) {
            service.i = -1;
        }
    }
}

public class Atomic32Test {
    public static void main(String[] args) throws InterruptedException {
        AtomicService service = new AtomicService();
        Thread3 t1 = new Thread3(service);
        Thread4 t2 = new Thread4(service);
        t1.start();
        t2.start();

        Thread.sleep(1000);

        System.out.println("long 1 to binary: " + Long.toBinaryString(1));
        System.out.println("long -1 to binary: " + Long.toBinaryString(-1));
        while (true) {
            long getValue = service.i;
            if (getValue != 1 && getValue != -1) {
                System.out.println("binary i= " + Long.toBinaryString(getValue)
                        + "i= " + getValue);
                System.exit(0);
            }
        }
    }
}
