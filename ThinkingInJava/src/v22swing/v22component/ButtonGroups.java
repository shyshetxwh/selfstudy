package v22swing.v22component;

import cn.shyshetxwh.util.SwingConsole;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * FileName: ButtonGroups
 * Author:   Administrator+shyshetxwh
 * Date:     2021/1/1 0001 8:18
 */
public class ButtonGroups extends JFrame {
    private static String[] ids={
            "June","Ward","Beaver","Wally","Eddie","Lumpy"
    };
    static JPanel makeBPanel(Class<? extends AbstractButton> kind,String[] ids){
        ButtonGroup bg=new ButtonGroup();
        JPanel jp = new JPanel();
        String title = kind.getName();
        title=title.substring(title.lastIndexOf('.')+1);
        jp.setBorder(new TitledBorder(title));
        for (String id : ids) {
            AbstractButton ab=new JButton("failed");
            try {
                Constructor ctor = kind.getConstructor(String.class);
                ab= (AbstractButton) ctor.newInstance(id);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            bg.add(ab);
            jp.add(ab);
        }
        return jp;
    }

    public ButtonGroups() throws HeadlessException {
        setLayout(new FlowLayout());
        add(makeBPanel(JButton.class,ids));
        add(makeBPanel(JToggleButton.class,ids));
        add(makeBPanel(JCheckBox.class,ids));
        add(makeBPanel(JRadioButton.class,ids));
    }

    public static void main(String[] args) {
        SwingConsole.run(new ButtonGroups(),500,350);
    }
}
