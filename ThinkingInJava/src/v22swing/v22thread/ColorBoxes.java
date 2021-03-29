package v22swing.v22thread;

import cn.shyshetxwh.util.SwingConsole;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * FileName: ColorBoxes
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/1 0001 23:53
 */
class CBox extends JPanel implements Runnable{
    private int pause;
    private static Random rand=new Random();
    private Color color=new Color(0);

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(color);
        Dimension s = getSize();
        g.fillRect(0,0,s.width,s.height);
    }

    public CBox(int pause) {
        this.pause = pause;
    }

    @Override
    public void run() {
        try {
            while(!Thread.interrupted()){
                color=new Color(rand.nextInt(0xFFFFFF));
                repaint();
                TimeUnit.MILLISECONDS.sleep(pause);
            }
        } catch (InterruptedException e) {
            //e.printStackTrace();
        }
    }
}

public class ColorBoxes extends JFrame {
    private int grid=12;
    private int pause=500;
    private static ExecutorService exec= Executors.newCachedThreadPool();

    public void setUp(){
        setLayout(new GridLayout(grid,grid));
        for (int i = 0; i < grid * grid; i++) {
            CBox cb = new CBox(pause);
            add(cb);
            exec.execute(cb);
        }
    }

    public static void main(String[] args) {
        ColorBoxes boxes = new ColorBoxes();
        boxes.setUp();
        SwingConsole.run(boxes,500,400);
    }
}
