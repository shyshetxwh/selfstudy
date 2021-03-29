package cn.shyshetxwh.swing.treeRender;

import javax.swing.*;
import javax.swing.tree.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Enumeration;

public class ClassTreeFrame extends JFrame{
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 300;

    private DefaultMutableTreeNode root;
    private DefaultTreeModel model;
    private JTree tree;
    private JTextField textField;
    private JTextArea textArea;
    public ClassTreeFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        root = new DefaultMutableTreeNode(Object.class);
        model = new DefaultTreeModel(root);
        tree = new JTree(model);

        addClass(getClass());

        //设置节点图标
        ClassNameTreeCellRenderer renderer = new ClassNameTreeCellRenderer();
        renderer.setClosedIcon(new ImageIcon(getClass().getClassLoader().getResource("red-ball.gif")));
        renderer.setOpenIcon(new ImageIcon(getClass().getClassLoader().getResource("yellow-ball.gif")));
        renderer.setLeafIcon(new ImageIcon(getClass().getClassLoader().getResource("blue-ball.gif")));
        tree.setCellRenderer(renderer);

        //设置树的点击就显示描述
        tree.addTreeSelectionListener(event->{
            TreePath path = tree.getSelectionPath();
            if (path==null)return;
            DefaultMutableTreeNode selectedNode= (DefaultMutableTreeNode) path.getLastPathComponent();
            Class<?>c= (Class<?>) selectedNode.getUserObject();
            String description=getFieldDescription(c);
            textArea.setText(description);
        });
        //设置树的选择模式
        int mode= TreeSelectionModel.SINGLE_TREE_SELECTION;
        tree.getSelectionModel().setSelectionMode(mode);

        textArea=new JTextArea();
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,2));
        panel.add(new JScrollPane(tree));
        panel.add(new JScrollPane(textArea));

        add(panel,BorderLayout.CENTER);

        addTextField();

    }

    private void addTextField() {
        JPanel panel = new JPanel();
        ActionListener addListener=event->{
            try {
                String text = textField.getText();
                addClass(Class.forName(text));
                textField.setText("");
            } catch (ClassNotFoundException e) {
                JOptionPane.showMessageDialog(null,"Class not found");
            }
        };
        textField=new JTextField(20);
        textField.addActionListener(addListener);
        panel.add(textField);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(addListener);
        panel.add(addButton);

        add(panel,BorderLayout.SOUTH);
    }

    private String getFieldDescription(Class<?> c) {
        StringBuilder sb = new StringBuilder();
        Field[] fields = c.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field f=fields[i];
            if ((f.getModifiers()& Modifier.STATIC)!=0)sb.append("Static ");
            sb.append(f.getType().getName());
            sb.append(" ");
            sb.append(f.getName());
            sb.append("\n");
        }
        return sb.toString();
    }

    private DefaultMutableTreeNode addClass(Class<?> c) {
        //跳过非类的部分，即接口或者原始类型
        if (c.isInterface()||c.isPrimitive())return null;
        //如果这个类以及存在了，那么直接返回
        DefaultMutableTreeNode node=findUserObject(c);
        if (node!=null)return node;
        //类不存在，那么就添加先添加它的父类
        Class<?> s = c.getSuperclass();
        DefaultMutableTreeNode parent;
        if (s==null)parent=root;
        else parent=addClass(s);

        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(c);
        model.insertNodeInto(newNode,parent,parent.getChildCount());

        TreePath path = new TreePath(model.getPathToRoot(newNode));
        tree.makeVisible(path);
        //tree.scrollPathToVisible(path);

        return newNode;
    }

    private DefaultMutableTreeNode findUserObject(Object obj) {
        Enumeration<TreeNode> e = (Enumeration<TreeNode>)root.breadthFirstEnumeration();
        while(e.hasMoreElements())
        {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.nextElement();
            if (node.getUserObject().equals(obj))return node;
        }
        return null;
    }
}
