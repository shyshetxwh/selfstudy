package cn.shyshetxwh.swing.print;

import java.awt.*;
import javax.swing.*;

/**
 * This program demonstrates how to print 2D graphics
 * @version 1.14 2018-05-01
 * @author Cay Horstmann
 */
public class PrintTest
{
   public static void main(String[] args)
   {
      EventQueue.invokeLater(() ->
         {
            PrintFrame frame = new PrintFrame();
            frame.setTitle("PrintTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
         });
   }
}
