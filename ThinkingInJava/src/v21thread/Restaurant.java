package v21thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Meal{
    private final int orderNumber;

    public Meal(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public String toString() {
        return "Meal "+orderNumber;
    }
}

class WaitPerson implements Runnable{
    private Restaurant restaurant;

    public WaitPerson(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()){
                synchronized (this){
                    while(restaurant.meal==null)
                        wait();
                }
                System.out.println("WaitPerson got "+restaurant.meal);
                synchronized (restaurant.chef){
                    restaurant.meal=null;
                    restaurant.chef.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            System.out.println("WaitPerson interrupted");
        }
    }
}

class Chef implements Runnable{
    private Restaurant restaurant;
    private int count=0;

    public Chef(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()){
                synchronized (this){
                    while(restaurant.meal!=null)
                        wait();
                }
                if (++count==10)
                {
                    System.out.println("Out of food,Closing");
                    restaurant.exec.shutdownNow();
                }
                System.out.print("Order up\t77");
                synchronized (restaurant.waitPerson){
                    restaurant.meal=new Meal(count);
                    restaurant.waitPerson.notifyAll();
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("Chef interrupted");
        }
    }
}


public class Restaurant {
    Meal meal;
    ExecutorService exec= Executors.newCachedThreadPool();
    WaitPerson waitPerson=new WaitPerson(this);
    Chef chef=new Chef(this);

    public Restaurant() {
        exec.execute(chef);
        exec.execute(waitPerson);
    }

    public static void main(String[] args) {
        new Restaurant();
    }
}
