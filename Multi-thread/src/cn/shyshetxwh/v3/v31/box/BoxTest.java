package cn.shyshetxwh.v3.v31.box;

/**
 * FileName: BoxTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/6 0006 16:25
 */
public class BoxTest {
    public static void main(String[] args) throws InterruptedException {
        Box box = new Box();
        ProducerService ps = new ProducerService(box);
        for (int i = 0; i < 2; i++) {
            new ThreadProducer(ps).start();
        }

        Thread.sleep(50);
        new ThreadProducerCheck(ps).start();


        Thread.sleep(10000);

        ConsumerService cs = new ConsumerService(box);
        for (int i = 0; i < 10; i++) {
            new ThreadConsumer(cs).start();
        }

        Thread.sleep(50);
        new ThreadConsumerCheck(cs).start();
    }
}
