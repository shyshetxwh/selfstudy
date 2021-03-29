package cn.shyshetxwh.v3.v31.pipeByte;

import java.io.PipedInputStream;

/**
 * FileName: ThreadRead
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/6 0006 16:46
 */
public class ThreadRead extends Thread {
    private ReadData read;
    private PipedInputStream in;

    public ThreadRead(ReadData read, PipedInputStream in) {
        this.read = read;
        this.in = in;
    }

    @Override
    public void run() {
        read.readMethod(in);
    }
}
