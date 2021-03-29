package cn.shyshetxwh.Thread.blockingQueueBank;

import java.util.ArrayList;


public class Bank {
    private ArrayList<Double> accounts;

    public Bank(int n, double initialBalance)
    {
        accounts=new ArrayList<Double>(n);
        for (int i = 0; i < n; i++) {
            accounts.add(initialBalance);
        }
    }

    public  void transfer(int from,int to,double amount) throws InterruptedException {


            if(accounts.get(from)<amount)
            {
                return;
            }
            System.out.print(Thread.currentThread());
            accounts.set(from,accounts.get(from)-amount);
            System.out.printf("%10.2f from %d to %d",amount,from,to);

            accounts.set(to,accounts.get(to)+amount);
            System.out.printf(" Total Balance: %10.2f",getTotalBalance());
            System.out.printf("%10.2f%n",accounts.get(from));


    }

    public  double getTotalBalance()
    {


            double sum=0;
            for (Double account : accounts) {
                sum += account;
            }
            return sum;


    }

    public int size()
    {
        return accounts.size();
    }
}
