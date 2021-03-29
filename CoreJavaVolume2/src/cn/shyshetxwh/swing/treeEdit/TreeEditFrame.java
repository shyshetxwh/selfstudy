package cn.shyshetxwh.swing.treeEdit;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;

public class TreeEditFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 200;
    private  DefaultTreeModel model;
    private JTree tree;

    public TreeEditFrame() {
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        //构建树模型
        TreeNode root=makeSampleTree();
        model = new DefaultTreeModel(root);
        tree = new JTree(model);
        tree.setEditable(true);

        add(new JScrollPane(tree), BorderLayout.CENTER);

        makeButtons();
    }


    private void makeButtons() {
        JPanel panel = new JPanel();
        JButton add_siblingButton = new JButton("Add Sibling");
        add_siblingButton.addActionListener(event->{
            DefaultMutableTreeNode selectedNode= (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            if (selectedNode==null)return;
            DefaultMutableTreeNode parent= (DefaultMutableTreeNode) selectedNode.getParent();
            if (parent==null)return;
            DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("New");
            int index = parent.getIndex(selectedNode);
            model.insertNodeInto(newNode,parent,index+1);
            //展示这个新节点
            TreeNode[] nodes = model.getPathToRoot(newNode);
            TreePath path = new TreePath(nodes);
            tree.scrollPathToVisible(path);
        });
        panel.add(add_siblingButton);

        JButton add_childButton = new JButton("Add Child");
        add_childButton.addActionListener(event ->
        {
            DefaultMutableTreeNode selectedNode= (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            if (selectedNode==null)return;
            DefaultMutableTreeNode newNode = new DefaultMutableTreeNode("New");
            model.insertNodeInto(newNode,selectedNode,selectedNode.getChildCount());
            //展示这个新节点
            TreeNode[] nodes = model.getPathToRoot(newNode);
            TreePath path = new TreePath(nodes);
            tree.scrollPathToVisible(path);
        });
        panel.add(add_childButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(event->{
            DefaultMutableTreeNode selectedNode= (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
            if (selectedNode!=null&&selectedNode.getParent()!=null)model.removeNodeFromParent(selectedNode);
        });
        panel.add(deleteButton);
        add(panel,BorderLayout.SOUTH);
    }

    private TreeNode makeSampleTree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("World");
        DefaultMutableTreeNode country = new DefaultMutableTreeNode("USA");
        root.add(country);
        DefaultMutableTreeNode state = new DefaultMutableTreeNode("California");
        country.add(state);
        DefaultMutableTreeNode city = new DefaultMutableTreeNode("San Jose");
        state.add(city);
        city = new DefaultMutableTreeNode("San Diego");
        state.add(city);
        state = new DefaultMutableTreeNode("Michigan");
        country.add(state);
        city = new DefaultMutableTreeNode("Ann Arbor");
        state.add(city);
        country = new DefaultMutableTreeNode("Germany");
        root.add(country);
        state = new DefaultMutableTreeNode("Schleswig-Holstein");
        country.add(state);
        city = new DefaultMutableTreeNode("Kiel");
        state.add(city);
        return root;
    }
}
