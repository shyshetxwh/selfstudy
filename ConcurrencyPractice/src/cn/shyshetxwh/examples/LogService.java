package cn.shyshetxwh.examples;

import net.jcip.annotations.GuardedBy;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * FileName: LogService
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/14 0014 22:08
 */
public class LogService {
    private final BlockingQueue<String> queue;
    private final LoggerThread loggerThread;
    private final PrintWriter writer;
    @GuardedBy("this")
    private boolean isShutdown;
    @GuardedBy("this")
    private int reservation;

    public LogService(Writer writer) {
        this.queue = new LinkedBlockingQueue<>();
        this.loggerThread = new LoggerThread();
        this.writer = new PrintWriter(writer);
    }

    public void start() {
        loggerThread.start();
    }

    public void stop() {
        synchronized (this) {
            isShutdown = true;
        }
        loggerThread.interrupt();
    }

    public void log(String msg) throws InterruptedException {
        synchronized (this) {
            if (isShutdown)
                throw new IllegalStateException();
            ++reservation;
        }
        queue.put(msg);
    }


    private class LoggerThread extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    try {
                        synchronized (LogService.this) {
                            if (isShutdown && reservation == 0)
                                break;
                        }
                        String msg = queue.take();
                        synchronized (LogService.this) {
                            --reservation;
                        }
                        writer.println(msg);
                    } catch (InterruptedException e) {
                        /*retry*/
                    }
                }
            } finally {
                writer.close();
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        LogService service = new LogService(new FileWriter("aaa.txt"));
        for (int i = 0; i < 100; i++) {
            service.log("msg" + i);
        }

        TimeUnit.SECONDS.sleep(1);

        service.start();


        service.stop();
    }
}
