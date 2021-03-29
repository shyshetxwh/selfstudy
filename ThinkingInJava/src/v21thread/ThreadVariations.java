package v21thread;

import java.util.concurrent.TimeUnit;

//使用内部类
class InnerThread1{
    private int countDown=5;
    private Inner inner;
    private class Inner extends Thread{
        Inner(String name){
            super(name);
            start();
        }

        @Override
        public void run() {
            try {
                while(true)
                {
                    System.out.println(this);
                    if (--countDown==0) return;
                    sleep(10);
                }
            } catch (InterruptedException e) {
                System.out.println("interrupted");
            }
        }

        @Override
        public String toString() {
            return getName()+": "+countDown;
        }
    }

    public InnerThread1(String name) {
        this.inner=new Inner(name);
    }
}


//使用匿名类
class InnerThread2{
    private int countDown=5;
    private Thread t;

    public InnerThread2(String name) {
        t=new Thread(name){
            @Override
            public void run() {
                try {
                    while(true)
                    {
                        System.out.println(this);
                        if (--countDown==0)
                            return;
                        sleep(10);
                    }
                } catch (InterruptedException e) {
                    System.out.println("sleep() interrupted");
                }
            }

            @Override
            public String toString() {
                return getName()+": "+countDown;
            }
        };
        t.start();
    }
}

//使用一个实现runnable接口的内部类
class InnerRunnable1{
    private int countDown=5;
    private Inner inner;
    private class Inner implements Runnable{
        Thread t;
        Inner(String name)
        {
            t=new Thread(this,name);
            t.start();
        }

        @Override
        public void run() {
            try {
                while(true){
                    System.out.println(this);
                    if (--countDown==0) return;
                    TimeUnit.MILLISECONDS.sleep(10);
                }
            } catch (InterruptedException e) {
                System.out.println("sleep() interrupted");
            }
        }

        @Override
        public String toString() {
            return t.getName()+": "+countDown;
        }
    }

    public InnerRunnable1(String name){
        inner=new Inner(name);
    }
}

//使用一个匿名类实现Runnable接口
class InnerRunnable2{
    private int countDown=5;
    private Thread t;

    public InnerRunnable2(String name) {
        t=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while(true){
                        System.out.println(this);
                        if (--countDown==0) return;
                        TimeUnit.MILLISECONDS.sleep(10);
                    }
                } catch (InterruptedException e) {
                    System.out.println("sleep() interrupted");
                }
            }

            @Override
            public String toString() {
                return Thread.currentThread().getName()+": "+countDown;
            }
        }, name);
        t.start();
    }
}

//一个独立的方法像任务一样去运行某些代码
class ThreadMethod{
    private int countDown=5;
    private Thread t;
    private String name;

    public ThreadMethod(String name) {
        this.name = name;
    }

    public void runTask(){
        if (t==null){
            t=new Thread(name){
                @Override
                public void run() {
                    try {
                        while(true)
                        {
                            System.out.println(this);
                            if (--countDown==0) return;
                            sleep(10);
                        }
                    } catch (InterruptedException e) {
                        System.out.println("sleep() interrupted");
                    }
                }

                @Override
                public String toString() {
                    return getName()+": "+countDown;
                }
            };
            t.start();
        }
    }

}


public class ThreadVariations {
    public static void main(String[] args) {
        new InnerThread1("InnerThread1");
        new InnerThread2("InnerThread2");
        new InnerRunnable1("InnerRunnable1");
        new InnerRunnable2("InnerRunnable2");
        new ThreadMethod("ThreadMethod").runTask();
    }
}
