package cn.shyshetxwh.v3.v31.piper;

import java.io.IOException;
import java.io.PipedWriter;

/**
 * FileName: WriteData
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/6 0006 16:53
 */
public class WriteData {
    public void writeMethod(PipedWriter out) {
        try {
            System.out.println("write: ");
            for (int i = 0; i < 300; i++) {
                String outData = "" + (i + 1);
                out.write(outData);
                System.out.print(outData);
            }
            System.out.println();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
