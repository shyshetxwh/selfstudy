package cn.shyshetxwh.Resource;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ResourceTest {
    public static void main(String[] args) throws IOException {
        Class cls = ResourceTest.class;
        URL url = cls.getResource("about.gif");
        ImageIcon icon = new ImageIcon(url);
        InputStream is = cls.getResourceAsStream("data/about.txt");
        byte[] b=new byte[1024];
        String about=null;
        String title=null;
        int len=0;
        while((len=is.read(b))!=-1)
        {
            about=new String(b,0,len,"UTF-8");
        }

        InputStream is2 = cls.getResourceAsStream("/corejava/title.txt");
        int len1=0;
        byte[] b1=new byte[1024];
        while((len1=is2.read(b1))!=-1)
        {

            title=new String(b,0,len1,StandardCharsets.UTF_8).trim();
            System.out.println(title);
        }

        JOptionPane.showMessageDialog(null,about,title,JOptionPane.INFORMATION_MESSAGE,icon);



    }
}
