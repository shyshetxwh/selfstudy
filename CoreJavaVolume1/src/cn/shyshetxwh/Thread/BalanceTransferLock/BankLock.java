package cn.shyshetxwh.Thread.BalanceTransferLock;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;


public class BankLock {
    private ArrayList<Double> accounts;
    private ReentrantLock bankLock=new ReentrantLock();
    public BankLock(int n, double initialBalance)
    {
        accounts=new ArrayList<Double>(n);
        for (int i = 0; i < n; i++) {
            accounts.add(initialBalance);
        }
    }

    public void transfer(int from,int to,double amount)
    {
        bankLock.lock();
        try {
            if (accounts.get(from)<amount)
            {
                return;
            }
            System.out.print(Thread.currentThread());
            accounts.set(from,accounts.get(from)-amount);
            System.out.printf("%10.2f from %d to %d",amount,from,to);

            accounts.set(to,accounts.get(to)+amount);
            System.out.printf(" Total Balance: %10.2f",getTotalBalance());
            System.out.printf("%10.2f%n",accounts.get(from));
        } finally {
            bankLock.unlock();
        }
    }

    public double getTotalBalance()
    {
        bankLock.lock();
        try {
            double sum=0;
            for (Double account : accounts) {
                sum+=account;
            }
            return sum;
        } finally {
            bankLock.unlock();
        }

    }

    public int size()
    {
        return accounts.size();
    }
}
