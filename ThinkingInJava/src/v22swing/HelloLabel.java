package v22swing;
/**
 * FileName: HelloLabel
 * Author:   Administrator+shyshetxwh
 * Date:     2020/12/31 0031 15:55
 */

import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class HelloLabel {
    public static void main(String[] args) throws InterruptedException {
        JFrame frame = new JFrame();
        frame.setTitle("HelloLabel");
        JLabel label = new JLabel("A Label");
        frame.add(label);
        frame.setSize(300,100);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        TimeUnit.SECONDS.sleep(1);
        label.setText("Hey! This is Different!");
    }
}