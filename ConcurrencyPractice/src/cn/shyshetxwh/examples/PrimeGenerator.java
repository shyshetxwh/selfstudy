package cn.shyshetxwh.examples;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * FileName: PrimeGenerator
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/14 0014 19:50
 */
@ThreadSafe
public class PrimeGenerator implements Runnable {
    @GuardedBy("this")
    private final List<BigInteger> primes = new ArrayList<>();
    private volatile boolean cancelled;

    @Override
    public void run() {
        BigInteger p = BigInteger.ONE;
        while (!cancelled) {
            p = p.nextProbablePrime();
            synchronized (this) {
                primes.add(p);
            }
        }
    }

    public void cancel() {
        cancelled = true;
    }

    public synchronized List<BigInteger> get() {
        return new ArrayList<BigInteger>(primes);
    }

    public static void main(String[] args) throws InterruptedException {
        PrimeGenerator generator = new PrimeGenerator();
        new Thread(generator).start();
        TimeUnit.SECONDS.sleep(3);
        new Thread(() -> {
            generator.cancel();
        }).start();
        TimeUnit.SECONDS.sleep(1);
        List<BigInteger> integers = generator.get();
        for (BigInteger integer : integers) {
            System.out.println(integer.intValue());
        }
    }
}
