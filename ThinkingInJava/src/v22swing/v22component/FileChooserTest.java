package v22swing.v22component;

import cn.shyshetxwh.util.SwingConsole;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * FileName: FileChooserTest
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/1 0001 19:41
 */
public class FileChooserTest extends JFrame {
    private JTextField
        fileName=new JTextField(),
        dir=new JTextField();
    private JButton
        open=new JButton("Open"),
        save=new JButton("Save");

    public FileChooserTest() throws HeadlessException {
        JPanel p = new JPanel();
        open.addActionListener(new OpenL());
        p.add(open);
        save.addActionListener(new SaveL());
        p.add(save);
        add(p,BorderLayout.SOUTH);
        dir.setEditable(false);
        fileName.setEditable(false);
        p=new JPanel();
        p.setLayout(new GridLayout(2,1));
        p.add(fileName);
        p.add(dir);
        add(p,BorderLayout.NORTH);

    }

    class OpenL implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser c = new JFileChooser();
            int rVal = c.showOpenDialog(FileChooserTest.this);
            if (rVal==JFileChooser.APPROVE_OPTION){
                fileName.setText(c.getSelectedFile().getName());
                dir.setText(c.getCurrentDirectory().toString());
            }
            if (rVal==JFileChooser.CANCEL_OPTION){
                fileName.setText("You pressed cancel");
                dir.setText("");
            }

        }
    }

    class SaveL implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            JFileChooser c = new JFileChooser();
            int rVal = c.showSaveDialog(FileChooserTest.this);
            if (rVal==JFileChooser.APPROVE_OPTION){
                fileName.setText(c.getSelectedFile().getName());
                dir.setText(c.getCurrentDirectory().toString());
            }
            if (rVal==JFileChooser.CANCEL_OPTION){
                fileName.setText("You pressed cancel");
                dir.setText("");
            }
        }
    }

    public static void main(String[] args) {
        SwingConsole.run(new FileChooserTest(),250,150);
    }
}
