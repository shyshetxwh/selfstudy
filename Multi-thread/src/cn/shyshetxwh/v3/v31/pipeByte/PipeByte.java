package cn.shyshetxwh.v3.v31.pipeByte;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

/**
 * FileName: PipeByte
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/6 0006 16:48
 */
public class PipeByte {
    public static void main(String[] args) throws IOException, InterruptedException {
        WriteData writeData = new WriteData();
        ReadData readData = new ReadData();

        PipedOutputStream out = new PipedOutputStream();
        PipedInputStream in = new PipedInputStream();

        out.connect(in);

        new ThreadRead(readData, in).start();

        Thread.sleep(2000);

        new ThreadWrite(writeData, out).start();
    }
}
