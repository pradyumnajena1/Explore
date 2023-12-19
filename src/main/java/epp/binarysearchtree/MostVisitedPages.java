package epp.binarysearchtree;

import epp.binaryTree.BinaryTreeNode;

import java.util.*;

public class MostVisitedPages {
    private static class TreeNode implements Comparable<TreeNode> {
        public static Comparator<TreeNode> comparator =
                Comparator.comparing(TreeNode::getPage).thenComparing(TreeNode::getCount,Comparator.reverseOrder());
        String page;
        int count;

        public TreeNode(String page, int count) {
            this.page = page;
            this.count = count;
        }

        @Override
        public int compareTo(TreeNode o) {
            return Integer.compare(count, o.count)!=0?Integer.compare(count, o.count):page.compareTo(o.page);
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("TreeNode{");
            sb.append("page='").append(page).append('\'');
            sb.append(", count=").append(count);
            sb.append('}');
            return sb.toString();
        }

        public String getPage() {
            return page;
        }

        public int getCount() {
            return count;
        }
    }

    private static class QueueNode {
        String page;
        long timestamp;

        public QueueNode(String page, long timestamp) {
            this.page = page;
            this.timestamp = timestamp;
        }
    }

    private Map<String, BinaryTreeNode<TreeNode>> frequencyMap = new HashMap<>();
    private BinaryTreeNode<TreeNode> root = null;
    private Deque<QueueNode> deque = new ArrayDeque<>();
    private int windowSize = 5 * 60 * 1000;

    public MostVisitedPages(int windowSize) {
        this.windowSize = windowSize;
    }

    public void add(String page, long timestamp) {



        if (frequencyMap.containsKey(page)) {
            BinaryTreeNode<TreeNode> binaryTreeNode = frequencyMap.get(page);
            BinaryTreeNode<TreeNode> newBinaryTreeNode = updateTreeNode(binaryTreeNode, 1);
            frequencyMap.put(page, newBinaryTreeNode);

        } else {
            TreeNode node = new TreeNode(page, 1);
            BinaryTreeNode<TreeNode> binaryTreeNode = addNodeToBST(node);
            System.out.println(root);
            frequencyMap.put(page, binaryTreeNode);
        }
        addToQueue(page, timestamp);

    }

    private BinaryTreeNode<TreeNode> updateTreeNode(BinaryTreeNode<TreeNode> binaryTreeNode, int countAdd) {
        removeBSTNode(binaryTreeNode);
        binaryTreeNode.data.count += countAdd;
        BinaryTreeNode<TreeNode> newBinaryTreeNode = addNodeToBST(binaryTreeNode.data);
        return newBinaryTreeNode;
    }

    private void addToQueue(String page, long timestamp) {
        QueueNode queueNode = new QueueNode(page, timestamp);
        deque.offerLast(queueNode);
        while (deque.size()>0 && deque.peekFirst().timestamp+windowSize<timestamp) {
            QueueNode oldPageNode = deque.pollFirst();
            BinaryTreeNode<TreeNode> binaryTreeNode = frequencyMap.get(oldPageNode.page);

            updateTreeNode(binaryTreeNode, -1);



        }
    }

    private void removeBSTNode(BinaryTreeNode<TreeNode> binaryTreeNode) {
        root = removeBSTNode(root, binaryTreeNode);

    }

    private BinaryTreeNode<TreeNode> removeBSTNode(BinaryTreeNode<TreeNode> root, BinaryTreeNode<TreeNode> binaryTreeNode) {
        if (root.data.compareTo(binaryTreeNode.data) < 0) {
            root.right = removeBSTNode(root.right, binaryTreeNode);
        } else if (root.data.compareTo(binaryTreeNode.data) > 0) {
            root.left = removeBSTNode(root.left, binaryTreeNode);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            root.data = minValue(root.right);
            root.right = removeBSTNode(root.right, binaryTreeNode);
        }
        return root;
    }

    private TreeNode minValue(BinaryTreeNode<TreeNode> root) {
        BinaryTreeNode<TreeNode> current = root;
        while (current.left != null) {
            current = current.left;
        }
        return current.data;
    }

    private BinaryTreeNode<TreeNode> addNodeToBST(TreeNode node) {
        BinaryTreeNode<TreeNode> binaryTreeNode = new BinaryTreeNode<>(node);
        root = addNodeToBST(root, binaryTreeNode);
        return binaryTreeNode;
    }

    private BinaryTreeNode<TreeNode> addNodeToBST(BinaryTreeNode<TreeNode> parent, BinaryTreeNode<TreeNode> node) {
        if (parent == null) {
            return node;
        } else if (parent.data.compareTo(node.data) <= 0) {
            parent.right = addNodeToBST(parent.right, node);

        } else {
            parent.left = addNodeToBST(parent.left, node);
        }
        return parent;

    }

    public List<TreeNode> common(int k) {
        ArrayList<TreeNode> nodeCollector = new ArrayList<>();
        inorderTraverse(root, nodeCollector, k);
        return nodeCollector;
    }

    private void inorderTraverse(BinaryTreeNode<TreeNode> root, ArrayList<TreeNode> nodeCollector, int k) {
        if (root == null) {
            return;
        }
        inorderTraverse(root.right, nodeCollector, k);
        if (nodeCollector.size() == k) {
            return;
        }
        nodeCollector.add(root.data);
        if (nodeCollector.size() == k) {
            return;
        }
        inorderTraverse(root.left, nodeCollector, k);
    }

    public static void main(String[] args) throws InterruptedException {






        MostVisitedPages mostVisitedPages = new MostVisitedPages(5 * 60 * 1000);
        mostVisitedPages.add("home",System.currentTimeMillis());
        mostVisitedPages.add("home",System.currentTimeMillis());
        mostVisitedPages.add("home",System.currentTimeMillis());
        mostVisitedPages.add("error",System.currentTimeMillis());
        mostVisitedPages.add("error",System.currentTimeMillis());
        mostVisitedPages.add("profile",System.currentTimeMillis());
        mostVisitedPages.add("profile",System.currentTimeMillis());
        mostVisitedPages.add("profile",System.currentTimeMillis());
        mostVisitedPages.add("profile",System.currentTimeMillis());
        System.out.println(mostVisitedPages.root);
        System.out.println( mostVisitedPages.common(2));



         loadPages(mostVisitedPages);
        System.out.println( mostVisitedPages.common(2));
    }

    private static void loadPages(MostVisitedPages mostVisitedPages) throws InterruptedException {
        String[] pages = new String[]{"home","error","profile","settings"};
        for(int i=0;i<100;i++){

            mostVisitedPages.add(pages[(int) (Math.random()*pages.length)],System.currentTimeMillis());
            Thread.sleep(500);
        }
    }
}
