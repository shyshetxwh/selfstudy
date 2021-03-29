package cn.shyshetxwh.Thread.blockingQueue;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class BlockingQueueTest {
    private static final int FILE_QUEUE_SIZE=10;
    private static BlockingQueue<Path> queue=new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);
    private static final Path DUMMY=Paths.get("");
    private static final int SEARCH_THREADS=100;

    public static void main(String[] args) {
        String directory=".";
        String keyword="Path";

        new Thread(()->{
            try {
                enumerate(Paths.get(directory));
                queue.put(DUMMY);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        for (int i = 0; i < SEARCH_THREADS; i++) {
            Runnable searcher=()->{
                try {
                    boolean done=false;
                    while(!done)
                    {
                        Path file = queue.take();
                        if (file==DUMMY)
                        {
                            queue.put(file);
                            done=true;
                        }
                        else
                        {
                            search(file,keyword);
                        }
                    }
                } catch (InterruptedException e) {

                } catch (IOException e) {
                    e.printStackTrace();
                }
            };
            new Thread(searcher).start();
        }

    }

    public static void enumerate(Path directory) throws IOException,InterruptedException {
        try(Stream<Path> children = Files.list(directory)) {
            for (Path child : children.collect(Collectors.toList())) {
                if (Files.isDirectory(child))
                {
                    enumerate(child);
                }
                else
                {
                    queue.put(child);
                }
            }
        }
    }

    public static void search(Path file,String keyword) throws IOException {
        try(Scanner in = new Scanner(file, "utf-8")) {
            int lineNumber=0;
            while(in.hasNextLine())
            {
                lineNumber++;
                String line = in.nextLine();
                if(line.contains(keyword))
                {
                    System.out.printf("%s:%d:%s%n",file,lineNumber,line);
                }
            }
        }
    }
}
