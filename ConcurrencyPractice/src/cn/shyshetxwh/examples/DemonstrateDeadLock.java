package cn.shyshetxwh.examples;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * FileName: DemonstrateDeadLock
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/15 0015 22:34
 */
public class DemonstrateDeadLock {
    private static final int NUM_THREADS = 20;
    private static final int NUM_ACCOUNTS = 5;
    private static final int NUM_ITERATIONS = 1000000;

    public static void main(String[] args) {
        final Random rand = new Random();
        final Account[] accounts = new Account[NUM_ACCOUNTS];

        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new Account();
        }

        class TransferThread extends Thread {
            @Override
            public void run() {
                for (int i = 0; i < NUM_ITERATIONS; i++) {
                    int from = rand.nextInt(NUM_ACCOUNTS);
                    int to = rand.nextInt(NUM_ACCOUNTS);
                    DollarAmount amount = new DollarAmount(rand.nextInt(1000));

                    try {
                        transferMoney(accounts[from], accounts[to], amount);
                    } catch (InsufficientFundsException e) {
                        //e.printStackTrace();
                    }
                }
            }
        }

        for (int i = 0; i < NUM_THREADS; i++) {
            new TransferThread().start();
        }
    }


    static class DollarAmount implements Comparable<DollarAmount> {
        // Needs implementation


        public DollarAmount() {
        }

        public DollarAmount(int amount) {
        }

        public DollarAmount add(DollarAmount d) {
            return new DollarAmount();
        }

        public DollarAmount subtract(DollarAmount d) {
            return new DollarAmount();
        }

        public int compareTo(DollarAmount dollarAmount) {
            return 0;
        }
    }

    static class Account {
        private DollarAmount balance;
        private final int acctNo;
        private static final AtomicInteger sequence = new AtomicInteger();

        public Account() {
            balance = new DollarAmount(1000000000);
            acctNo = sequence.incrementAndGet();
        }

        void debit(DollarAmount d) {
            balance = balance.subtract(d);
        }

        void credit(DollarAmount d) {
            balance = balance.add(d);
        }

        DollarAmount getBalance() {
            return balance;
        }

        int getAcctNo() {
            return acctNo;
        }
    }

    static class InsufficientFundsException extends Exception {
    }

    public static void transferMoney(Account fromAccount,
                                     Account toAccount,
                                     DollarAmount amount)
            throws InsufficientFundsException {
        synchronized (fromAccount) {
            synchronized (toAccount) {
                if (fromAccount.getBalance().compareTo(amount) < 0)
                    throw new InsufficientFundsException();
                else {
                    fromAccount.debit(amount);
                    toAccount.credit(amount);
                }
            }
        }
    }
}
