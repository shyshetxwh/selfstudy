package v21thread;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

class Blocker{
    synchronized void waitingCall(){
        try {
            while(!Thread.interrupted()){
                wait();
                System.out.println(Thread.currentThread()+" ");
            }
        } catch (InterruptedException e) {

        }
    }
    synchronized void prod() {notify();}
    synchronized void prodAll() {notifyAll();}
}

class Task implements Runnable{
    static Blocker blocker=new Blocker();

    @Override
    public void run() {
        blocker.waitingCall();
    }
}

class Task2 implements Runnable{
    static Blocker blocker=new Blocker();

    @Override
    public void run() {
        blocker.waitingCall();
    }
}

public class NotifyVsNotifyAll {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            exec.execute(new Task());
        }
        exec.execute(new Task2());
        Timer timer = new Timer();
        //每0.4秒切换执行Task.blocker notify()和notifyAll()
        timer.scheduleAtFixedRate(new TimerTask() {
            boolean prod=true;
            @Override
            public void run() {
                if (prod){
                    System.out.print("\nnotify() ");
                    Task.blocker.prod();
                    prod=false;
                }
                else{
                    System.out.print("\nnotifyAll() ");
                    Task.blocker.prodAll();
                    prod=true;
                }
            }
        }, 400, 400);
        TimeUnit.SECONDS.sleep(5);
        timer.cancel();
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.print("Task2.blocker.prodAll() ");
        TimeUnit.MILLISECONDS.sleep(500);
        System.out.println("\nShutting down");
        exec.shutdownNow();

    }
}
