package cn.shyshetxwh.v3.v31.piper;

import java.io.PipedWriter;

/**
 * FileName: ThreadWrite
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/6 0006 16:56
 */
public class ThreadWrite extends Thread {
    private WriteData write;
    private PipedWriter out;

    public ThreadWrite(WriteData write, PipedWriter out) {
        this.write = write;
        this.out = out;
    }

    @Override
    public void run() {
        write.writeMethod(out);
    }
}
