package cn.shyshetxwh.v8;

import java.util.Map;
import java.util.concurrent.*;

/**
 * FileName: BankWaterService
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/12 0012 21:47
 */
public class BankWaterService implements Runnable {
    private CyclicBarrier c = new CyclicBarrier(4, this);
    private ExecutorService exec = Executors.newFixedThreadPool(4);
    private ConcurrentHashMap<String, Integer> sheetBankWaterCount = new ConcurrentHashMap<>();

    private void count() {
        for (int i = 0; i < 4; i++) {
            exec.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName());
                    sheetBankWaterCount.put(Thread.currentThread().getName(), 1);
                    try {
                        c.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Override
    public void run() {
        int result = 0;
        for (Map.Entry<String, Integer> sheet : sheetBankWaterCount.entrySet()) {
            result += sheet.getValue();
        }
        sheetBankWaterCount.put("result", result);
        System.out.println(result);
        System.out.println(Thread.currentThread().getName());
        exec.shutdown();
    }

    public static void main(String[] args) {
        BankWaterService service = new BankWaterService();
        service.count();

    }
}
