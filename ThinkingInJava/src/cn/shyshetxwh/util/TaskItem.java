package cn.shyshetxwh.util;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;

/**
 * FileName: TaskItem
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/1 0001 22:49
 */
public class TaskItem<R,C extends Callable<R>> {
    public final Future<R> future;
    public final C task;

    public TaskItem(Future<R> future, C task) {
        this.future = future;
        this.task = task;
    }
}
