package cn.shyshetxwh.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * FileName: TaskManager
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/1 0001 22:51
 */
public class TaskManager<R,C extends Callable<R>> extends ArrayList<TaskItem<R,C>> {
    private ExecutorService exec= Executors.newSingleThreadExecutor();


    public void add(C task) {
        add(new TaskItem<R,C>(exec.submit(task),task));
    }

    public List<R> getResults(){
        Iterator<TaskItem<R, C>> items = iterator();
        List<R> results =new ArrayList<>();
        while (items.hasNext()){
            TaskItem<R, C> item = items.next();
            if (item.future.isDone()){
                try {
                    results.add(item.future.get());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                items.remove();
            }
        }
        return results;
    }

    public List<String> purge(){
        Iterator<TaskItem<R, C>> items = iterator();
        List<String> results=new ArrayList<>();
        while(items.hasNext()){
            TaskItem<R, C> item = items.next();
            if (!item.future.isDone()){
                results.add("Cancelling "+item.task);
                item.future.cancel(true);
                items.remove();
            }
        }
        return results;
    }
}
