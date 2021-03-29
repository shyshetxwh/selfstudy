package v22swing.v22thread;

import cn.shyshetxwh.util.SwingConsole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;

/**
 * FileName: LongRunningTask
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/1 0001 22:33
 */
public class LongRunningTask extends JFrame {
    private JButton
        b1=new JButton("Start Long Running Task"),
        b2=new JButton("End Long Running Task");

    public LongRunningTask() throws HeadlessException {
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e1) {
                    System.out.println("Task interrupted");
                    return;
                }
                System.out.println("Task completed");
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Thread.currentThread().interrupt();
            }
        });
        setLayout(new FlowLayout());
        add(b1);
        add(b2);
    }

    public static void main(String[] args) {
        SwingConsole.run(new LongRunningTask(),200,150);
    }
}
