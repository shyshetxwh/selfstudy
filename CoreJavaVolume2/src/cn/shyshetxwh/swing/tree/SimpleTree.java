package cn.shyshetxwh.swing.tree;

import java.awt.*;
import javax.swing.*;

/**
 * This program shows a simple tree.
 * @version 1.04 2018-05-01
 * @author Cay Horstmann
 */
public class SimpleTree
{
   public static void main(String[] args)
   {
      EventQueue.invokeLater(() ->
         {
            SimpleTreeFrame frame = new SimpleTreeFrame();
            frame.setTitle("SimpleTree");               
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
         });
   }
}
