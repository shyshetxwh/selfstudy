package cn.shyshetxwh.swing.tree;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;

public class SimpleTreeFrame extends JFrame {
    public static final int DEFAULT_WIDTH=300;
    public static final int DEFAULT_HEIGHT=200;

    public SimpleTreeFrame()
    {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        //构建树模型
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("World");
        DefaultMutableTreeNode country = new DefaultMutableTreeNode("USA");
        root.add(country);
        DefaultMutableTreeNode state = new DefaultMutableTreeNode("California");
        country.add(state);
        DefaultMutableTreeNode city = new DefaultMutableTreeNode("San Jose");
        state.add(city);
        city = new DefaultMutableTreeNode("Cupertino");
        state.add(city);
        state=new DefaultMutableTreeNode("Michigan");
        country.add(state);
        city = new DefaultMutableTreeNode("Ann Arbor");
        state.add(city);
        country = new DefaultMutableTreeNode("Germany");
        root.add(country);
        state = new DefaultMutableTreeNode("Schleswig-Holstein");
        country.add(state);
        city = new DefaultMutableTreeNode("Kiel");
        state.add(city);

        //创建一个树，并将其放入滚转页面
        JTree tree = new JTree(root);
        //取消父子节点之间的连接线
        tree.putClientProperty("JTree.lineStyle","None");
        //根节点添加门把手
        tree.setShowsRootHandles(true);
        //将根节点隐藏
        tree.setRootVisible(false);
        add(new JScrollPane(tree));
    }
}
