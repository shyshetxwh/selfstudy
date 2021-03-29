package cn.shyshetxwh.Thread.concurrentHashMap;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CHMTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        int processors = Runtime.getRuntime().availableProcessors();
        ExecutorService executor = Executors.newFixedThreadPool(processors);
        Path rootPath = Paths.get(".");
        for (Path p : CHMUtils.descendants(rootPath)) {
            if (p.getFileName().toString().endsWith(".java"))
            {
                executor.execute(()->CHMUtils.process(p));
            }
        }
        executor.shutdown();

        executor.awaitTermination(10, TimeUnit.MINUTES);

        CHMUtils.map.forEach((k,v)->{
            if (v>=10)
            {
                System.out.println(k+" occurs "+v+" times");
            }
        });
    }
}
