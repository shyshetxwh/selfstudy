package cn.shyshetxwh.LoggingImageViewer;

import javax.swing.*;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.LogRecord;
import java.util.logging.StreamHandler;

/**
 * 定义一个窗口显示日志
 */
public class WindowHandle extends StreamHandler{
    private JFrame frame;

    public WindowHandle()
    {
        frame=new JFrame();
        JTextArea output = new JTextArea();
        frame.setSize(200,200);
        output.setEditable(false);
        frame.add(new JScrollPane(output));
        frame.setFocusableWindowState(false);
        frame.setVisible(true);
        setOutputStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {

            }

            @Override
            public void write(byte[] b,int off,int len) throws IOException {
                output.append(new String(b,off,len));
            }
        });

    }

    public void publish(LogRecord record)
    {
        if(!frame.isVisible())
        {
            return;
        }
        super.publish(record);
        flush();
    }
}
