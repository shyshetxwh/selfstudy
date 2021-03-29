package cn.shyshetxwh.v4;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * FileName: Piped
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/10 0010 9:11
 */
public class Piped {
    static class Print implements Runnable {
        private PipedReader in;

        public Print(PipedReader in) {
            this.in = in;
        }

        @Override
        public void run() {
            int r = 0;
            try {
                while ((r = in.read()) != -1) {
                    System.out.print((char) r);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        PipedWriter out = new PipedWriter();
        PipedReader in = new PipedReader();
        out.connect(in);

        Thread printThread = new Thread(new Print(in), "PrintThread");
        printThread.start();

        int r = 0;

        try {
            while ((r = System.in.read()) != -1) {
                out.write(r);
            }
        } finally {
            out.close();
        }

    }
}
