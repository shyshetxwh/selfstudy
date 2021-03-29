package cn.shyshetxwh.v3.v31.pipeByte;

import java.io.PipedOutputStream;

/**
 * FileName: ThreadWrite
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/6 0006 16:44
 */
public class ThreadWrite extends Thread {
    private WriteData write;
    private PipedOutputStream out;

    public ThreadWrite(WriteData write, PipedOutputStream out) {
        this.write = write;
        this.out = out;
    }

    @Override
    public void run() {
        write.writeMethod(out);
    }
}
