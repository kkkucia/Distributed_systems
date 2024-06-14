package org.example;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.BorderLayout;
import java.util.*;
import java.util.logging.Logger;

public class GuiUtils {
    private static final Logger logger = Logger.getLogger(GuiUtils.class.getName());
    private final ZooKeeper zooKeeper;
    private DefaultTreeModel treeModel;
    private DefaultMutableTreeNode rootNode;
    private JLabel childrenQuantityLabel;

    public GuiUtils(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
        initializeGUI();
    }

    private void initializeGUI() {
        JFrame frame = new JFrame("ZooKeeper Watcher");
        childrenQuantityLabel = new JLabel("Children quantity: 0", SwingConstants.CENTER);
        rootNode = new DefaultMutableTreeNode("ZooKeeper Nodes");
        treeModel = new DefaultTreeModel(rootNode);
        setupGUI(frame);
    }

    private void setupGUI(JFrame frame) {
        frame.setLayout(new BorderLayout());
        frame.add(childrenQuantityLabel, BorderLayout.NORTH);

        JTree tree = new JTree(treeModel);
        frame.add(new JScrollPane(tree), BorderLayout.CENTER);

        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }


    public void updateChildrenCount(int count) {
        childrenQuantityLabel.setText("Children count: " + count);
    }

    public void displayTree(String zNodeName) {
        rootNode.removeAllChildren();
        try {
            populateTree(zNodeName, rootNode);
        } catch (Exception e) {
            logger.warning("Failed to retrieve children nodes from ZooKeeper.");
        }
        treeModel.reload();
    }

    private void populateTree(String zNodeName, DefaultMutableTreeNode parentNode) {
        try{
            List<String> children = zooKeeper.getChildren(zNodeName, false);
            for (String child : children) {
                DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);
                parentNode.add(childNode);
                populateTree(zNodeName + "/" + child, childNode);
            }
        }catch (KeeperException e){
            logger.warning(String.format("No [%s] zNode found.", zNodeName));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
