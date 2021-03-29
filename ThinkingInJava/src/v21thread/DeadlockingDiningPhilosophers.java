package v21thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class DeadlockingDiningPhilosophers {
    public static void main(String[] args) throws InterruptedException {
        int ponder=5;
        int size=5;
        ExecutorService exec = Executors.newCachedThreadPool();
        Chopstick[] chopsticks = new Chopstick[size];
        for (int i = 0; i < size; i++) {
            chopsticks[i]=new Chopstick();
        }
        for (int i = 0; i < size; i++) {
            exec.execute(new Philosopher(chopsticks[i],chopsticks[(i+1)%size],i,ponder));
        }
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
}
