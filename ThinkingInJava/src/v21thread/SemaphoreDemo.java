package v21thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

class CheckTask<T> implements Runnable{
    private static int counter=0;
    private final int id=counter++;
    private Pool<T> pool;

    public CheckTask(Pool<T> pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        try {
            T item = pool.checkOut();
            System.out.println(this +"checked out "+item);
            TimeUnit.SECONDS.sleep(1);
            System.out.println(this +"checked in"+item);
            pool.checkIn(item);
        } catch (InterruptedException e) {
            //e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "CheckoutTask "+id +" ";
    }
}

public class SemaphoreDemo {
    final static int SIZE=25;

    public static void main(String[] args) throws Exception {
        final Pool<Fat> pool=new Pool<Fat>(Fat.class,SIZE);
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < SIZE; i++) {
            exec.execute(new CheckTask<Fat>(pool));
        }
        System.out.println("All CheckoutTasks created");
        List<Fat> list=new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            Fat f = pool.checkOut();
            System.out.print(i+": main() thread checked out ");
            f.operation();
            list.add(f);
        }

        Future<?> blocked = exec.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    pool.checkOut();
                } catch (InterruptedException e) {
                    System.out.println("checkOut() interrupted");
                }

            }
        });
        TimeUnit.SECONDS.sleep(2);
        blocked.cancel(true);
        System.out.println("Checking in objects in "+list);
        for (Fat fat : list) {
            pool.checkIn(fat);
        }
        for (Fat fat : list) {
            pool.checkIn(fat);
        }
        exec.shutdown();
    }

}
