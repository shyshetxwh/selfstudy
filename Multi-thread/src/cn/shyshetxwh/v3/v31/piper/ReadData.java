package cn.shyshetxwh.v3.v31.piper;

import java.io.IOException;
import java.io.PipedReader;

/**
 * FileName: ReadData
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/6 0006 16:54
 */
public class ReadData {
    public void readMethod(PipedReader in) {
        try {
            System.out.println("read: ");
            char[] chars = new char[20];
            int n = 0;
            while ((n = in.read(chars)) != -1) {
                String newData = new String(chars, 0, n);
                System.out.print(newData);
            }
            System.out.println();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
