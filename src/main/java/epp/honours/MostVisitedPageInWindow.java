package epp.honours;

import epp.binaryTree.BinaryTreeNode;
import epp.binarysearchtree.BSTUtils;
import java.util.*;

public class MostVisitedPageInWindow {

  private int windowSize;
  private Deque<QueueNode> deque = new ArrayDeque<>();
  private Map<String,  TreeNode> pageVisitCount = new HashMap<>();
  private BinaryTreeNode<TreeNode> root;

  public MostVisitedPageInWindow(int windowSize) {
    this.windowSize = windowSize;
  }

  @Override
  public String toString() {
    return new StringJoiner(", ", MostVisitedPageInWindow.class.getSimpleName() + "[", "]")
            .add("deque=" + deque)
            .add("pageVisitCount=" + pageVisitCount)
            .add("root=" + root)
            .toString();
  }

  public static void main(String[] args) {
    //
    MostVisitedPageInWindow mostVisitedPageInWindow = new MostVisitedPageInWindow(15);
    mostVisitedPageInWindow.addPage("home", 1000);
    System.out.println(mostVisitedPageInWindow.root);
    mostVisitedPageInWindow.addPage("index", 1001);
    System.out.println(mostVisitedPageInWindow.root);
    mostVisitedPageInWindow.addPage("home", 1003);
    System.out.println(mostVisitedPageInWindow.root);
    mostVisitedPageInWindow.addPage("index2", 1006);
    System.out.println(mostVisitedPageInWindow.root);
    mostVisitedPageInWindow.addPage("index", 1014);
    System.out.println(mostVisitedPageInWindow.root);


    mostVisitedPageInWindow.addPage("index2", 1018);
    System.out.println(mostVisitedPageInWindow.root);
    mostVisitedPageInWindow.addPage("home", 1019);
    System.out.println(mostVisitedPageInWindow.root);

    System.out.println(mostVisitedPageInWindow.getMostVisitedPage());
  }

  public void addPage(String page, long timestamp) {
    while (!deque.isEmpty() && deque.peekFirst().timestamp <= timestamp - windowSize) {
      QueueNode remove = deque.pollFirst();
      TreeNode treeNode = pageVisitCount.get(remove.page);
      decrementPageVisitCount(treeNode);
    }
    deque.offerLast(new QueueNode(page, timestamp));
    if (pageVisitCount.containsKey(page)) {
      TreeNode treeNode = pageVisitCount.get(page);
      incrementPageVisitCount(treeNode);
    } else {
       TreeNode treeNode =  new TreeNode(page, 1);
      insertNode(treeNode);
    }
  }

  private void insertNode(TreeNode treeNode) {
    pageVisitCount.put(treeNode.page, treeNode);
    root = BSTUtils.insertNode(root, treeNode);
  }

  private void removeNode(TreeNode node) {
    root = BSTUtils.deleteNode(root, node);
    pageVisitCount.remove(node.page);
  }

  private void incrementPageVisitCount( TreeNode node) {

    removeNode(node);

    node.visitCount++;
    insertNode(node);
  }

  private void decrementPageVisitCount( TreeNode node) {

    removeNode(node);

    node.visitCount--;
    if (node.visitCount > 0) {
      insertNode(node);
    }
  }

  public String getMostVisitedPage() {
    if (root == null) {
      return null;
    }
    BinaryTreeNode<TreeNode> current = root;
    while (current.right != null) {
      current = current.right;
    }
    return current.data.page;
  }

  private static class QueueNode {
    String page;
    long timestamp;

    public QueueNode(String page, long timestamp) {
      this.page = page;
      this.timestamp = timestamp;
    }
  }

  public static class TreeNode implements Comparable<TreeNode>,Cloneable {
    String page;
    int visitCount;

    public TreeNode(String page, int visitCount) {
      this.page = page;
      this.visitCount = visitCount;
    }

    @Override
    public int compareTo(TreeNode o) {
      return Integer.compare(this.visitCount, o.visitCount);
    }

    @Override
    public TreeNode clone() throws CloneNotSupportedException {
      return (TreeNode) super.clone();
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      TreeNode treeNode = (TreeNode) o;

      if (visitCount != treeNode.visitCount) return false;
        return Objects.equals(page, treeNode.page);
    }

    @Override
    public int hashCode() {

      return Objects.hash(page, visitCount);
    }

    @Override
    public String toString() {
      return page + " : " + visitCount;
    }
  }
}
