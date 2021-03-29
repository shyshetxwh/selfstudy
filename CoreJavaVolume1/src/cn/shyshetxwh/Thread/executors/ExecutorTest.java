package cn.shyshetxwh.Thread.executors;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

public class ExecutorTest {
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        String start="./";
        String word="out";

        Set<Path> files = ExecutorUtils.descendants(Paths.get(start));
        ArrayList<Callable<Long>> tasks=new ArrayList<>();
        for (Path file : files) {
            Callable<Long> task=()->ExecutorUtils.occurrences(word,file);
            tasks.add(task);
        }
        ExecutorService executor = Executors.newCachedThreadPool();
//        ExecutorService executor = Executors.newSingleThreadExecutor();
        Instant startTime = Instant.now();
        List<Future<Long>> results = executor.invokeAll(tasks);
        long total=0;
        for (Future<Long> result : results) {
            total+=result.get();
        }
        Instant endTime = Instant.now();
        System.out.println("Occurrences of "+word+": "+total);
        System.out.println("Time elapsed: "+ Duration.between(startTime,endTime).toMillis()+" ms");


        ArrayList<Callable<Path>> searchTasks=new ArrayList<>();
        for (Path file : files) {
            searchTasks.add(ExecutorUtils.searchForTask(word,file));
        }
        Path found = executor.invokeAny(searchTasks);
        System.out.println(word+" occurs in: "+found);

        if (executor instanceof ThreadPoolExecutor)
        {
            System.out.println("Largest pool size:"+((ThreadPoolExecutor)executor).getLargestPoolSize());
        }
        executor.shutdown();

    }
}
