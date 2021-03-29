package cn.shyshetxwh.v3.v31.pipeByte;

import java.io.IOException;
import java.io.PipedInputStream;

/**
 * FileName: ReadData
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/6 0006 16:41
 */
public class ReadData {
    public void readMethod(PipedInputStream in) {
        try {
            System.out.println("read: ");
            byte[] bytes = new byte[20];
            int n = 0;
            while ((n = in.read(bytes)) != -1) {
                String newData = new String(bytes, 0, n);
                System.out.print(newData);
            }
            System.out.println();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
