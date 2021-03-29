package cn.shyshetxwh.v3.v31.box;

/**
 * FileName: ProducerService
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/6 0006 16:07
 */
public class ProducerService {
    private Box box;

    public ProducerService(Box box) {
        this.box = box;
    }

    public void producer() {
        try {
            synchronized (this) {
                while (box.size() == 50) {
                    System.out.println("●●●●●");
                    this.wait();
                }
            }
            Thread.sleep(300);
            box.add();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void checkBoxStatus() {
        try {
            while (true) {
                synchronized (this) {
                    if (box.size() < 50)
                        this.notifyAll();
                }
                System.out.println("producerService check BoxSize=" + box.size());
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
