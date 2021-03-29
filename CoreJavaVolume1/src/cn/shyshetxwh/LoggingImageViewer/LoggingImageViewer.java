package cn.shyshetxwh.LoggingImageViewer;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoggingImageViewer {
    public static void main(String[] args) {
        //将所有消息记录到文件中
        if(System.getProperty("java.util.logging.config.class")==null&&System.getProperty("java.util.logging.config.file")==null)
        {

            try {
                Logger.getLogger("cn.shyshetxwh.LoggingImageViewer").setLevel(Level.ALL);
                final int LOG_ROTATION_COUNT=10;
                FileHandler handler = new FileHandler("%h/MyLoggingImageViewer.log", 0, LOG_ROTATION_COUNT);
                Logger.getLogger("cn.shyshetxwh.LoggingImageViewer").addHandler(handler);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        EventQueue.invokeLater(()->
        {
            WindowHandle windowHandle = new WindowHandle();
            windowHandle.setLevel(Level.ALL);
            Logger.getLogger("cn.shyshetxwh.LoggingImageViewer").addHandler(windowHandle);

            ImageViewerFrame frame = new ImageViewerFrame();
            frame.setTitle("LoggingImageViewer");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Logger.getLogger("cn.shyshetxwh.LoggingImageViewer").fine("Showing frame");
            frame.setVisible(true);

        });
    }
}
