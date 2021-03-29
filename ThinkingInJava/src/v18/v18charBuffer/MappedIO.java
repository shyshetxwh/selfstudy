package v18.v18charBuffer;

import cn.shyshetxwh.util.Directory;

import java.io.*;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;


public class MappedIO {
    private static int numOfInts=4000000;
    private static int numOfUbuffInts=200000;
    private static String path= new File(Directory.findFilePath("MappedIO")).getParent()+"/temp.tmp";
    private abstract static class Tester{
        private String name;

        public Tester(String name) {
            this.name = name;
        }

        public void runTest(){
            System.out.print(name+": ");
            try {
                long start = System.nanoTime();
                test();
                long duration = System.nanoTime() - start;
                System.out.format("%.2f\n",duration/1.0e9);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public abstract void test() throws IOException;
    }

    private static Tester[] tests={
            new Tester("Stream Write") {
                @Override
                public void test() throws IOException {
                    DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File(path))));

                    for (int i = 0; i < numOfInts; i++) {
                        dos.writeInt(i);
                    }
                    dos.close();
                }
            },
            new Tester("Mapped Write") {
                @Override
                public void test() throws IOException {
                    FileChannel fc = new RandomAccessFile(path, "rw").getChannel();
                    IntBuffer ib = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size()).asIntBuffer();

                    for (int i = 0; i < numOfInts; i++) {
                        ib.put(i);
                    }
                    fc.close();
                }
            },
            new Tester("Stream Read") {
                @Override
                public void test() throws IOException {
                    DataInputStream dis = new DataInputStream(new BufferedInputStream(new FileInputStream(path)));
                    for (int i = 0; i < numOfInts; i++) {
                        dis.readInt();
                    }
                    dis.close();
                }
            },
            new Tester("Mapped Read") {
                @Override
                public void test() throws IOException {
                    FileChannel fc = new FileInputStream(new File(path)).getChannel();
                    IntBuffer ib = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size()).asIntBuffer();
                    while (ib.hasRemaining())
                    {
                        ib.get();
                    }
                    fc.close();
                }
            },
            new Tester("Stream Read/Write") {
                @Override
                public void test() throws IOException {
                    RandomAccessFile raf = new RandomAccessFile(new File(path), "rw");
                    raf.writeInt(1);
                    for (int i = 0; i < numOfUbuffInts; i++) {
                        raf.seek(raf.length()-4);
                        raf.writeInt(raf.readInt());
                    }
                    raf.close();
                }
            },
            new Tester("Mapped Read/Write") {
                @Override
                public void test() throws IOException {
                    FileChannel fc = new RandomAccessFile(new File(path), "rw").getChannel();
                    IntBuffer ib = fc.map(FileChannel.MapMode.READ_WRITE, 0, fc.size()).asIntBuffer();
                    ib.put(0);
                    for (int i = 1; i < numOfUbuffInts; i++) {
                        ib.put(ib.get(i-1));
                    }
                    fc.close();
                }
            }
    };

    public static void main(String[] args) throws IOException {
//        for (Tester test : tests) {
//
//            test.runTest();
//        }

        /*FileOutputStream fos = new FileOutputStream(new File(Directory.findPathFromCurrent("MappedIO")).getParent() + "/test.txt");
        fos.write(65);
        fos.close();
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(Directory.findPathFromCurrent("MappedIO")).getParent() + "/test.txt"));
        bw.write(66);
        bw.close();
        BufferedReader br = new BufferedReader(new FileReader(new File(Directory.findPathFromCurrent("MappedIO")).getParent() + "/test.txt"));
        String s;
        while((s=br.readLine())!=null)
        {
            System.out.println(Arrays.toString(s.getBytes()));
        }*/

        String path = new File(Directory.findFilePath("MappedIO")).getParent() + "/test.txt";
        RandomAccessFile raf = new RandomAccessFile(new File(path), "rw");
        raf.writeInt(1);
        for (int i = 0; i < 10; i++) {
            long length = raf.length();
            long l = raf.length() - 4;
            raf.seek(raf.length()-4);
            raf.writeInt(raf.readInt());
        }
        raf.close();
    }
}
