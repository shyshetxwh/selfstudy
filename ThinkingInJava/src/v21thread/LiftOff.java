package v21thread;

public class LiftOff implements Runnable {
    protected int countDown=10;
    private static int taskCount=0;
    private final int id=taskCount++;

    public LiftOff(int countDown) {
        this.countDown = countDown;
    }

    public LiftOff() {
    }

    public String status(){
        return "#"+id+"("+(countDown>0?countDown:"LiftOff!")+"),";
    }

    @Override
    public void run() {
        while (countDown-- >0)
        {
            System.out.print(status());
            Thread.yield();
        }
    }
}
