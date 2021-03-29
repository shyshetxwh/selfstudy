package cn.shyshetxwh.Thread.BalanceTransfer;

import java.util.ArrayList;


public class Bank {
    ArrayList<Double>accounts;
    public Bank(int n,double initialBalance)
    {
        accounts=new ArrayList<Double>(n);
        for (int i = 0; i < n; i++) {
            accounts.add(initialBalance);
        }
    }

    public void transfer(int from,int to,double amount)
    {
        if (accounts.get(from)<amount)
        {
            return;
        }
        System.out.print(Thread.currentThread());
        accounts.set(from,accounts.get(from)-amount);
        System.out.printf("%10.2f from %d to %d",amount,from,to);

        accounts.set(to,accounts.get(to)+amount);
        System.out.printf("Total Balance: %10.2f%n",getTotalBalance());
    }

    public double getTotalBalance()
    {
        double sum=0;
        for (Double account : accounts) {
            sum+=account;
        }
        return sum;

    }

    public int size()
    {
        return accounts.size();
    }
}
