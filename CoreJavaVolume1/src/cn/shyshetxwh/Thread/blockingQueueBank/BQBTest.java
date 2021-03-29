package cn.shyshetxwh.Thread.blockingQueueBank;

public class BQBTest {

    public static void main(String[] args) {
        Bank bank=new Bank(100,1000);
        for (int i = 0; i < 100; i++) {
            int from=i;
            Runnable r=()->{
                while(true)
                {
                    try {
                        BlockingQueueBank.putOrder(from);
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {

                    }
                }


            };
            new Thread(r).start();
        }

        new Thread(()->{
            while(true)
            {
                try {
                    TransferOrder order = BlockingQueueBank.queue.take();
                    bank.transfer(order.getFrom(),order.getTo(),order.getAmount());

                } catch (InterruptedException e) {

                }
            }

        }).start();
    }
}
