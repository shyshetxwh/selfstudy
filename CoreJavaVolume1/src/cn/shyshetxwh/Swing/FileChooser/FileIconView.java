package cn.shyshetxwh.Swing.FileChooser;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileView;
import java.io.File;

public class FileIconView extends FileView {
    private FileFilter filter;
    private Icon icon;
    public FileIconView(FileFilter aFilter, Icon anIcon)
    {
        this.filter=aFilter;
        this.icon=anIcon;
    }
    public Icon getIcon(File f)
    {
        if (!f.isDirectory() && filter.accept(f)) return icon;
        else
            return null;
    }
}
