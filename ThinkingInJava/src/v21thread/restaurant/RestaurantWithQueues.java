package v21thread.restaurant;

import v19enum.v19menu.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

class Order{
    private static int counter=0;
    private final int id=counter++;
    private final Customer customer;
    private final WaitPerson waitPerson;
    private final Food food;

    public Order(Customer customer, WaitPerson waitPerson, Food food) {
        this.customer = customer;
        this.waitPerson = waitPerson;
        this.food = food;
    }

    public Food item() {return food;}

    public Customer getCustomer() {
        return customer;
    }

    public WaitPerson getWaitPerson() {
        return waitPerson;
    }

    @Override
    public String toString() {
        return "Order: "+id+" item: "+food+" for: "+customer+" served by: "+waitPerson;
    }
}

class Plate{
    private final Order order;
    private final Food food;

    public Plate(Order order, Food food) {
        this.order = order;
        this.food = food;
    }

    public Order getOrder() {
        return order;
    }

    public Food getFood() {
        return food;
    }

    @Override
    public String toString() {
        return food.toString();
    }
}

class Customer implements Runnable{
    private static int counter=0;
    private final int id=counter++;
    private final WaitPerson waitPerson;
    private SynchronousQueue<Plate> placeSetting=new SynchronousQueue<Plate>();

    public Customer(WaitPerson waitPerson) {
        this.waitPerson = waitPerson;
    }

    public void deliver(Plate p) throws InterruptedException {
        placeSetting.put(p);
    }

    @Override
    public void run() {
        for (Course course : Course.values()) {
            Food food = course.randomSelection();
            try{
                waitPerson.placeOrder(this,food);
                System.out.println(this+"eating "+placeSetting.take());
            }catch (InterruptedException e){
                System.out.println(this+"waiting for "+course+" interrupted");
                break;
            }
        }
        System.out.println(this+"finished meal,leaving");
    }

    @Override
    public String toString() {
        return "Customer "+id+" ";
    }
}

class WaitPerson implements Runnable{
    private static int counter=0;
    private final int id=counter++;
    private final Restaurant restaurant;
    private static Random rand=new Random(47);
    BlockingQueue<Plate> filledOrders=new LinkedBlockingQueue<Plate>();
    public WaitPerson(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
    public void placeOrder(Customer customer,Food food){
        try {
            restaurant.orders.put(new Order(customer,this,food));
        }catch (InterruptedException e){
            System.out.println(this+" placeOrder interrupted");
        }
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()){
                Plate plate = filledOrders.take();
                System.out.println(this +"received "+plate+" delivering to "+plate.getOrder().getCustomer());
                plate.getOrder().getCustomer().deliver(plate);
            }
        } catch (InterruptedException e) {
            System.out.println(this+" interrupted");
        }
        System.out.println(this +" off duty");
    }

    @Override
    public String toString() {
        return "WaitPerson "+id+" ";
    }
}

class Chef implements Runnable{
    private static int counter=0;
    private final int id=counter++;
    private final Restaurant restaurant;
    private static Random rand=new Random(47);

    public Chef(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()){
                Order order=restaurant.orders.take();
                Food requestedItem = order.item();
                TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
                Plate plate = new Plate(order, requestedItem);
                order.getWaitPerson().filledOrders.put(plate);
            }
        } catch (InterruptedException e) {
            System.out.println(this+" interrupted");
        }
        System.out.println(this +" off duty");
    }

    @Override
    public String toString() {
        return "Chef "+id +" ";
    }
}

class Restaurant implements Runnable{
    private List<WaitPerson> waitPersons=new ArrayList<WaitPerson>();
    private List<Chef> chefs=new ArrayList<Chef>();
    private ExecutorService exec;
    private static Random rand=new Random(47);
    BlockingQueue<Order> orders=new LinkedBlockingQueue<Order>();

    public Restaurant(ExecutorService exec,int nWaitPersons,int nChefs) {
        this.exec = exec;
        for (int i = 0; i < nWaitPersons; i++) {
            WaitPerson waitPerson = new WaitPerson(this);
            waitPersons.add(waitPerson);
            exec.execute(waitPerson);
        }
        for (int i = 0; i < nChefs; i++) {
            Chef chef = new Chef(this);
            chefs.add(chef);
            exec.execute(chef);
        }
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()){
                WaitPerson wp = waitPersons.get(rand.nextInt(waitPersons.size()));
                Customer c = new Customer(wp);
                exec.execute(c);
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            System.out.println("Restaurant interrupted");
        }
        System.out.println("Restaurant closing");
    }
}


public class RestaurantWithQueues {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        Restaurant restaurant = new Restaurant(exec, 5, 2);
        exec.execute(restaurant);
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
}
