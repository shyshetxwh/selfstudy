package v21thread;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Customer{
    private final int serviceTime;

    public Customer(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    @Override
    public String toString() {
        return "["+serviceTime+"]";
    }
}

class CustomerLine extends ArrayBlockingQueue<Customer>{
    public CustomerLine(int capacity) {
        super(capacity);
    }

    @Override
    public String toString() {
        if (this.size()==0) return "[Empty]";
        StringBuilder result = new StringBuilder();
        for (Customer customer : this) {
            result.append(customer);
        }
        return result.toString();
    }
}

class CustomerGenerator implements Runnable{
    private CustomerLine customers;
    private static Random rand=new Random(47);

    public CustomerGenerator(CustomerLine customers) {
        this.customers = customers;
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()){
                TimeUnit.MILLISECONDS.sleep(rand.nextInt(300));
                customers.put(new Customer(rand.nextInt(1000)));
            }
        } catch (InterruptedException e) {
            System.out.println("CustomerGenerator interrupted");
        }
        System.out.println("CustomerGenerator terminating");
    }
}

class Teller implements Runnable,Comparable<Teller>{
    private static int counter=0;
    private final int id=counter++;
    private int customerServed=0;
    private CustomerLine customers;
    private boolean servingCustomerLine=true;

    public Teller(CustomerLine customers) {
        this.customers = customers;
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()){
                Customer customer = customers.take();
                TimeUnit.MILLISECONDS.sleep(customer.getServiceTime());
                synchronized (this){
                    customerServed++;
                    while(!servingCustomerLine)
                        wait();
                }
            }
        } catch (InterruptedException e) {
            System.out.println(this +"interrupted");
        }
        System.out.println(this+"terminating");
    }

    public synchronized void doSomethingElse(){
        customerServed=0;
        servingCustomerLine=false;
    }

    public synchronized void serveCustomerLine(){
        assert !servingCustomerLine:"already serving: "+this;
        servingCustomerLine=true;
        notifyAll();
    }

    @Override
    public String toString() {
        return "Teller "+id+" ";
    }

    public String shortString(){
        return "T"+id;
    }

    @Override
    public synchronized int compareTo(Teller other) {
        return customerServed<other.customerServed?-1:(customerServed==other.customerServed?0:1);
    }

}

class TellerManager implements Runnable{
    private ExecutorService exec;
    private CustomerLine customers;
    private PriorityQueue<Teller> workingTellers=new PriorityQueue<Teller>();
    private Queue<Teller> tellersDoingOtherThings=new LinkedList<Teller>();
    private int adjustmentPeriod;
    private static Random rand=new Random(47);

    public TellerManager(ExecutorService exec, CustomerLine customers, int adjustmentPeriod) {
        this.exec = exec;
        this.customers = customers;
        this.adjustmentPeriod = adjustmentPeriod;
        Teller teller = new Teller(customers);
        exec.execute(teller);
        workingTellers.add(teller);
    }

    public void adjustTellerNumber(){
        if (customers.size()/workingTellers.size()>2){
            if (tellersDoingOtherThings.size()>0){
                Teller teller = tellersDoingOtherThings.remove();
                teller.serveCustomerLine();
                workingTellers.add(teller);
                return;
            }
            Teller teller = new Teller(customers);
            exec.execute(teller);
            workingTellers.add(teller);
            return;
        }

        if (workingTellers.size()>1&&customers.size()/workingTellers.size()<2){
            reassignOneTeller();
        }
        if (customers.size()==0){
            while (workingTellers.size()>1)
                reassignOneTeller();
        }
    }

    private void reassignOneTeller(){
        Teller teller = workingTellers.poll();
        teller.doSomethingElse();
        tellersDoingOtherThings.offer(teller);
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()){
                TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
                adjustTellerNumber();
                System.out.print(customers+"{ ");
                for (Teller teller : workingTellers) {
                    System.out.print(teller.shortString()+" ");
                }
                System.out.println("}");
            }
        } catch (InterruptedException e) {
            System.out.println(this+"interrupted");
        }
        System.out.println(this+"terminating");
    }

    @Override
    public String toString() {
        return "TellerManager ";
    }
}

public class BankTellerSimulation {
    static final int MAX_LINE_SIZE=50;
    static final int ADJUSTMENT_PERIOD=1000;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        CustomerLine customers = new CustomerLine(MAX_LINE_SIZE);
        exec.execute(new CustomerGenerator(customers));
        exec.execute(new TellerManager(exec,customers,ADJUSTMENT_PERIOD));
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
}
