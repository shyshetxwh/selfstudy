package cn.shyshetxwh.IO.memoryMap;

import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.CRC32;

public class MemoryMapTest {
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
            for (int p = 0; p < length; p++) {
                int c = buffer.get(p);
                crc.update(c);
            }
            return crc.getValue();
        }
    }

    private static long checksumRandomAccessFile(Path filename) throws IOException {
        try(RandomAccessFile file= new RandomAccessFile(filename.toFile(),"r"))
        {
            CRC32 crc = new CRC32();
            long length = file.length();
            for (long p = 0; p < length; p++) {
                file.seek(p);
                int c = file.readByte();
                crc.update(c);
            }
            return crc.getValue();
        }
    }

    private static long checksumBufferedInputStream(Path filename) throws IOException {
        try(BufferedInputStream in= new BufferedInputStream(Files.newInputStream(filename)))
        {
            CRC32 crc = new CRC32();
            int c;
            while ((c=in.read())!=-1)
            {
                crc.update(c);
            }
            return crc.getValue();
        }
    }

    private static long checksumInputStream(Path filename) throws IOException {
        try(InputStream in= Files.newInputStream(filename))
        {
            CRC32 crc = new CRC32();
            int c;
            while ((c=in.read())!=-1)
            {
                crc.update(c);
            }
            return crc.getValue();
        }
    }
}
