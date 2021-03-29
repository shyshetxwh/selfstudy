package cn.shyshetxwh.IO.memoryMap;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.CRC32;

public class MemoryMapTest2 {
    private static final int BLOCK_SIZE=1024;
    public static void main(String[] args) throws IOException {
        System.out.println("Input Stream:");
        long start = System.currentTimeMillis();
        Path filename = Paths.get("aaa.jpg");
        long crcValue=checksumInputStream(filename);
        long end = System.currentTimeMillis();
        System.out.println(Long.toHexString(crcValue));
        System.out.println((end-start)+" milliseconds");

        System.out.println("Buffered Input Stream:");
        start = System.currentTimeMillis();
        crcValue=checksumBufferedInputStream(filename);
        end = System.currentTimeMillis();
        System.out.println(Long.toHexString(crcValue));
        System.out.println((end-start)+" milliseconds");

        System.out.println("Random Access File:");
        start = System.currentTimeMillis();
        crcValue=checksumRandomAccessFile(filename);
        end = System.currentTimeMillis();
        System.out.println(Long.toHexString(crcValue));
        System.out.println((end-start)+" milliseconds");

        System.out.println("Mapped File:");
        start = System.currentTimeMillis();
        crcValue=checksumMappedFile(filename);
        end = System.currentTimeMillis();
        System.out.println(Long.toHexString(crcValue));
        System.out.println((end-start)+" milliseconds");
    }

    private static long checksumMappedFile(Path filename) throws IOException {
        try(FileChannel channel=FileChannel.open(filename))
        {
            CRC32 crc = new CRC32();
            int length = (int) channel.size();
            MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, length);
            byte[] bytes = new byte[BLOCK_SIZE];
            for (int p = 0; p < length; p+=BLOCK_SIZE) {
                int n=Math.min(BLOCK_SIZE,length-p);
                buffer.get(bytes, 0, n);
                crc.update(bytes,0,n);
            }
            return crc.getValue();
        }
    }

    private static long checksumRandomAccessFile(Path filename) throws IOException {
        try(RandomAccessFile file= new RandomAccessFile(filename.toFile(),"r"))
        {
            CRC32 crc = new CRC32();
            long length = file.length();
            byte[] bytes = new byte[BLOCK_SIZE];
            for (long p = 0; p < length; p+=BLOCK_SIZE) {
                file.seek(p);
                int n = file.read(bytes);
                crc.update(bytes,0,n);
            }
            return crc.getValue();
        }
    }

    private static long checksumBufferedInputStream(Path filename) throws IOException {
        try(BufferedInputStream in= new BufferedInputStream(Files.newInputStream(filename)))
        {
            CRC32 crc = new CRC32();
            int n;
            byte[] bytes = new byte[BLOCK_SIZE];
            while ((n=in.read(bytes))!=-1)
            {
                crc.update(bytes,0,n);
            }
            return crc.getValue();
        }
    }

    private static long checksumInputStream(Path filename) throws IOException {
        try(InputStream in= Files.newInputStream(filename))
        {
            CRC32 crc = new CRC32();
            int n;
            byte[] bytes = new byte[BLOCK_SIZE];
            while ((n=in.read(bytes))!=-1)
            {
                crc.update(bytes,0,n);
            }
            return crc.getValue();
        }
    }
}
