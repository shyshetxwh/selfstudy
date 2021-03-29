package cn.shyshetxwh.v3.v31.piper;

import java.io.PipedReader;

/**
 * FileName: ThreadRead
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/6 0006 16:57
 */
public class ThreadRead extends Thread {
    private ReadData read;
    private PipedReader in;

    public ThreadRead(ReadData read, PipedReader in) {
        this.read = read;
        this.in = in;
    }

    @Override
    public void run() {
        read.readMethod(in);
    }
}
