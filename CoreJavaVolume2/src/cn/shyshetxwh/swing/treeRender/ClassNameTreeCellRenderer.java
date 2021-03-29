package cn.shyshetxwh.swing.treeRender;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.lang.reflect.Modifier;

public class ClassNameTreeCellRenderer extends DefaultTreeCellRenderer {
    private Font plainFont=null;
    private Font italicFont=null;

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree,value,selected,expanded,leaf,row,hasFocus);
        DefaultMutableTreeNode node= (DefaultMutableTreeNode) value;
        Class<?>c= (Class<?>) node.getUserObject();

        if (plainFont==null)
        {
            plainFont=getFont();
            if (plainFont!=null)italicFont=plainFont.deriveFont(Font.ITALIC);
        }
        //如果类时虚类，则将其字体设置为斜体
        if ((c.getModifiers()& Modifier.ABSTRACT)==0)setFont(plainFont);
        else setFont(italicFont);
        return this;
    }
}
