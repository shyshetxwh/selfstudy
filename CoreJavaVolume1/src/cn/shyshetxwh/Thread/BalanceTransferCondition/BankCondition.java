package cn.shyshetxwh.Thread.BalanceTransferCondition;

import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankCondition {
    private ArrayList<Double> accounts;
    private Lock bankLock;
    private Condition sufficientFunds;


    public BankCondition(int n,double initialBalance)
    {
        accounts=new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            accounts.add(initialBalance);
        }
        bankLock=new ReentrantLock();
        sufficientFunds=bankLock.newCondition();
    }


    public void transfer(int from,int to,double amount) throws InterruptedException {
        bankLock.lock();
        try {
            while(accounts.get(from)<amount)
            {
                sufficientFunds.await();
            }
            System.out.print(Thread.currentThread());
            accounts.set(from,accounts.get(from)-amount);
            System.out.printf("%10.2f from %d to %d",amount,from,to);

            accounts.set(to,accounts.get(to)+amount);
            System.out.printf(" Total Balance: %10.2f",getTotalBalance());
            System.out.printf("%10.2f%n",accounts.get(from));
            sufficientFunds.signalAll();
        } finally {
            bankLock.unlock();
        }
    }

    public double getTotalBalance() {
        bankLock.lock();
        try {
            double sum=0;
            for (Double a : accounts) {
                sum+=a;
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
