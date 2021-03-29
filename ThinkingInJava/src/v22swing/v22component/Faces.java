package v22swing.v22component;

import cn.shyshetxwh.util.SwingConsole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * FileName: Faces
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/1 0001 8:35
 */
public class Faces extends JFrame {
    private static Icon[] faces;
    private JButton jb,jb2=new JButton("Disable");
    private boolean mad=false;

    public Faces() throws HeadlessException {
        faces=new Icon[]{
                new ImageIcon(getClass().getClassLoader().getResource("Face0.gif")),
                new ImageIcon(getClass().getClassLoader().getResource("Face1.gif")),
                new ImageIcon(getClass().getClassLoader().getResource("Face2.gif")),
                new ImageIcon(getClass().getClassLoader().getResource("Face3.gif")),
                new ImageIcon(getClass().getClassLoader().getResource("Face4.gif")),
        };
        jb=new JButton("JButton",faces[3]);
        setLayout(new FlowLayout());
        jb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mad){
                    jb.setIcon(faces[3]);
                    mad=false;
                }
                else{
                    jb.setIcon(faces[0]);
                    mad=true;
                }
                jb.setVerticalAlignment(JButton.TOP);
                jb.setHorizontalAlignment(JButton.LEFT);
            }
        });
        jb.setRolloverEnabled(true);
        jb.setRolloverIcon(faces[1]);
        jb.setPressedIcon(faces[2]);
        jb.setDisabledIcon(faces[4]);
        jb.setToolTipText("Yow!");
        add(jb);
        jb2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (jb.isEnabled()){
                    jb.setEnabled(false);
                    jb2.setText("Enable");
                }else{
                    jb.setEnabled(true);
                    jb2.setText("Disable");
                }

            }
        });
        add(jb2);
    }

    public static void main(String[] args) {
        SwingConsole.run(new Faces(),250,125);
    }
}
