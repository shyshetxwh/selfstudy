package cn.shyshetxwh.examples;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * FileName: InduceDeadLockOrder
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/15 0015 22:21
 */
public class InduceDeadLockOrder {
    private static final Object TIELOCK = new Object();
    private static Random rnd = new Random();
    private static final int DELAY_FIXED = 1;
    private static final int DELAY_RANDOM = 2;

    static long getFixedDelayComponentNanos(long timeout, TimeUnit unit) {
        return DELAY_FIXED;
    }

    static long getRandomDelayModulusNanos(long timeout, TimeUnit unit) {
        return DELAY_RANDOM;
    }


    public void transferMoney(final Account from, final Account to, final DollarAmount amount) throws InsufficientFundsException {
        class Helper {
            public void transfer() throws InsufficientFundsException {
                if (from.getBalance().compareTo(amount) < 0)
                    throw new InsufficientFundsException();
                else {
                    from.debit(amount);
                    to.credit(amount);
                }
            }

        }
        int fromHash = System.identityHashCode(from);
        int toHash = System.identityHashCode(to);

        if (fromHash < toHash) {
            synchronized (from) {
                synchronized (to) {
                    new Helper().transfer();
                }
            }
        } else if (fromHash > toHash) {
            synchronized (to) {
                synchronized (from) {
                    new Helper().transfer();
                }
            }
        } else {
            synchronized (TIELOCK) {
                synchronized (from) {
                    synchronized (to) {
                        new Helper().transfer();
                    }
                }
            }
        }
    }

    public boolean transferMoneyByLock(Account from, Account to, DollarAmount amount, long timeout, TimeUnit unit) throws InsufficientFundsException, InterruptedException {
        long fixedDelay = getFixedDelayComponentNanos(timeout, unit);
        long randMod = getRandomDelayModulusNanos(timeout, unit);
        long stopTime = System.nanoTime() + unit.toNanos(timeout);
        while (true) {
            if (from.lock.tryLock()) {
                try {
                    if (to.lock.tryLock()) {
                        try {
                            if (from.getBalance().compareTo(amount) < 0)
                                throw new InsufficientFundsException();
                            else {
                                from.debit(amount);
                                to.credit(amount);
                                return true;
                            }
                        } finally {
                            to.lock.unlock();
                        }
                    }
                } finally {
                    from.lock.unlock();
                }
            }
            if (System.nanoTime() < stopTime)
                return false;
            TimeUnit.NANOSECONDS.sleep(fixedDelay + rnd.nextLong() % randMod);
        }
    }

    interface DollarAmount extends Comparable<DollarAmount> {
    }

    interface Account {
        public Lock lock = new ReentrantLock();

        void debit(DollarAmount d);

        void credit(DollarAmount d);

        DollarAmount getBalance();

        int getAcctNo();
    }

    class InsufficientFundsException extends Exception {
    }
}
