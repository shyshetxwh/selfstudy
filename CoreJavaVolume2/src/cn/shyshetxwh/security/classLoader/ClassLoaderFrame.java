package cn.shyshetxwh.security.classLoader;

import cn.shyshetxwh.Swing.GridBagLayout.GBC;

import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Method;

public class ClassLoaderFrame extends JFrame{
    private JTextField keyField=new JTextField("3",4);
    private JTextField nameField=new JTextField("Calculator",30);
    private static final int DEFAULT_WIDTH=300;
    private static final int DEFAULT_HEIGHT=200;

    public ClassLoaderFrame()  {
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        setLayout(new GridBagLayout());
        add(new JLabel("Class"), new GBC(0, 0).setAnchor(GBC.EAST));
        add(nameField, new GBC(1, 0).setWeight(100, 0).setAnchor(GBC.WEST));
        add(new JLabel("Key"), new GBC(0, 1).setAnchor(GBC.EAST));
        add(keyField, new GBC(1, 1).setWeight(100, 0).setAnchor(GBC.WEST));
        JButton loadButton = new JButton("Load");
        add(loadButton, new GBC(0, 2, 2, 1));
//        loadButton.addActionListener(event->runClass(nameField.getText(),keyField.getText()));
        runClass(nameField.getText(),keyField.getText());
        pack();
    }

    private void runClass(String name, String key) {
        try {
            CryptoClassLoader loader = new CryptoClassLoader(Integer.parseInt(key));
            Class<?> cl = loader.loadClass(name);
            Method m = cl.getMethod("main", String[].class);
            m.invoke(null,(Object)new String[] {});
        } catch (Throwable t) {
            JOptionPane.showMessageDialog(this,t);
        }
    }


}
