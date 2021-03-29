package cn.shyshetxwh.v3.v31.box;

/**
 * FileName: ConsumerService
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/6 0006 16:16
 */
public class ConsumerService {
    private Box box;

    public ConsumerService(Box box) {
        this.box = box;
    }

    public void consumer() {
        try {
            synchronized (this) {
                while (box.size() == 0) {
                    System.out.println("○○○○○");
                    this.wait();
                }
                box.popFirst();
            }
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void checkBoxStatus() {
        try {
            while (true) {
                synchronized (this) {
                    if (box.size() > 0)
                        this.notifyAll();
                }
                System.out.println("Consumer Check Box Size=" + box.size());
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
