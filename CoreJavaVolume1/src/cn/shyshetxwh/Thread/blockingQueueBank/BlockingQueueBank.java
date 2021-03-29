package cn.shyshetxwh.Thread.blockingQueueBank;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueBank {
    private static final int ORDER_QUEUE_SIZE=10;
    public static BlockingQueue<TransferOrder> queue=new ArrayBlockingQueue<>(ORDER_QUEUE_SIZE);
    private static final double MAX_AMOUNT=1000;


    public static void putOrder(int from) throws InterruptedException {
        int to=(int)(100*Math.random());
        double amount = MAX_AMOUNT * Math.random();
        TransferOrder order = new TransferOrder(from,to,amount);
        queue.put(order);
    }


}
