package cn.shyshetxwh.v3.v31.pipeByte;

import java.io.IOException;
import java.io.PipedOutputStream;

/**
 * FileName: WriteData
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/6 0006 16:38
 */
public class WriteData {
    public void writeMethod(PipedOutputStream out) {
        try {
            System.out.println("write: ");
            for (int i = 0; i < 300; i++) {
                String outData = "" + (i + 1);
                out.write(outData.getBytes());
                System.out.print(outData);
            }
            System.out.println();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
