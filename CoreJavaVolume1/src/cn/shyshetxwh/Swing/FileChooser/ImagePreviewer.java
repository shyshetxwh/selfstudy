package cn.shyshetxwh.Swing.FileChooser;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public class ImagePreviewer extends JLabel {
    public ImagePreviewer(JFileChooser fileChooser) {
        setPreferredSize(new Dimension(100,100));
        setBorder(BorderFactory.createEtchedBorder());
        fileChooser.addPropertyChangeListener(event->{
            if(event.getPropertyName()==JFileChooser.SELECTED_FILE_CHANGED_PROPERTY)
            {
                File newFile = (File) event.getNewValue();
                if(newFile==null)
                {
                    setIcon(null);
                    return;
                }
                ImageIcon icon = new ImageIcon(newFile.getPath());
                if (icon.getIconWidth()>getWidth())
                {
                    icon=new ImageIcon(icon.getImage().getScaledInstance(getWidth(),-1,Image.SCALE_DEFAULT));
                }
                setIcon(icon);
            }
        });
    }
}
