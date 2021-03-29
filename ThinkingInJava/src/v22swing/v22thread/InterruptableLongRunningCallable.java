package v22swing.v22thread;

import cn.shyshetxwh.util.SwingConsole;
import cn.shyshetxwh.util.TaskItem;
import cn.shyshetxwh.util.TaskManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Callable;

/**
 * FileName: InterruptableLongRunningCallable
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/1 0001 23:06
 */

class CallableTask extends Task implements Callable<String>{
    @Override
    public String call() {
        run();
        return "Return value of "+this;
    }
}

public class InterruptableLongRunningCallable extends JFrame {
    private JButton
        b1=new JButton("Start Long Running Task"),
        b2=new JButton("End Long Running Task"),
        b3=new JButton("Get results");
    private TaskManager<String,CallableTask> manager=new TaskManager<>();

    public InterruptableLongRunningCallable() throws HeadlessException {
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CallableTask task = new CallableTask();
                manager.add(task);
                System.out.println(task+" added to the queue");
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (String result : manager.purge()) {
                    System.out.println(result);
                }
            }
        });
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (TaskItem<String, CallableTask> tt : manager) {
                    tt.task.id();
                }
                for (String result : manager.getResults()) {
                    System.out.println(result);
                }
            }
        });
        setLayout(new FlowLayout());
        add(b1);
        add(b2);
        add(b3);
    }

    public static void main(String[] args) {
        SwingConsole.run(new InterruptableLongRunningCallable(),200,150);
    }
}
