package cn.shyshetxwh.v3.v31.piper;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * FileName: Piper
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/6 0006 16:58
 */
public class Piper {
    public static void main(String[] args) throws IOException, InterruptedException {
        WriteData writeData = new WriteData();
        ReadData readData = new ReadData();

        PipedReader in = new PipedReader();
        PipedWriter out = new PipedWriter();

        out.connect(in);

        new ThreadRead(readData, in).start();

        Thread.sleep(2000);

        new ThreadWrite(writeData, out).start();

    }
}
