package cn.shyshetxwh.v4;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * FileName: Priority
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/9 0009 22:39
 */
public class Priority {
    private static volatile boolean noStart = true;
    private static volatile boolean noEnd = true;

    static class Job implements Runnable {
        private int priority;
        private long jobCount;

        public Job(int priority) {
            this.priority = priority;
        }

        @Override
        public void run() {
            while (noStart)
                Thread.yield();
            while (noEnd) {
                Thread.yield();
                jobCount++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        List<Job> jobs = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int priority = i < 5 ? Thread.MIN_PRIORITY : Thread.MAX_PRIORITY;
            Job job = new Job(priority);
            jobs.add(job);
            Thread t = new Thread(job, "Thread" + i);
            t.setPriority(priority);
            t.start();
        }
        noStart = false;
        TimeUnit.SECONDS.sleep(10);
        noEnd = false;

        for (Job job : jobs) {
            System.out.println("Job priority :" + job.priority +
                    " count:" + job.jobCount);
        }
    }
}
