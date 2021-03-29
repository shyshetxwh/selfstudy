package cn.shyshetxwh.swing.rasterImage;

import java.awt.*;
import javax.swing.*;

/**
 * This program demonstrates how to build up an image from individual pixels.
 * @version 1.15 2018-05-01
 * @author Cay Horstmann
 */
public class RasterImageTest
{
   public static void main(String[] args)
   {
      EventQueue.invokeLater(() ->
         {
            RasterImageFrame frame = new RasterImageFrame();
            frame.setTitle("RasterImageTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
         });
   }
}
