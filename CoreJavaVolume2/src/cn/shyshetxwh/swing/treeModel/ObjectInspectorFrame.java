package cn.shyshetxwh.swing.treeModel;

import javax.swing.*;
import java.awt.*;

public class ObjectInspectorFrame extends JFrame {
    private JTree tree;
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 300;

    public ObjectInspectorFrame()  {
        setSize(DEFAULT_WIDTH,DEFAULT_HEIGHT);
        Variable v = new Variable(getClass(), "this", this);
        ObjectTreeModel model = new ObjectTreeModel();
        model.setRoot(v);

        JTree tree = new JTree(model);
        add(new JScrollPane(tree),BorderLayout.CENTER);
    }
}
