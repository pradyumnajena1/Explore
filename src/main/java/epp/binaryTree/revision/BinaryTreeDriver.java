package epp.binaryTree.revision;

import epp.binaryTree.BinaryTreeNode;
import epp.binaryTree.BinaryTreeNodeWithParent;

import java.util.*;

public class BinaryTreeDriver {

  public static void main(String[] args) {
    BinaryTreeNode<Integer> node1;
    BinaryTreeNode<Integer> node2;

    BinaryTreeNode<Integer> a = new BinaryTreeNode<>(5);
    BinaryTreeNode<Integer> b = new BinaryTreeNode<>(8, null, null);
    BinaryTreeNode<Integer> root =
        new BinaryTreeNode<>(
            4,
            new BinaryTreeNode<>(2, new BinaryTreeNode<>(1), new BinaryTreeNode<>(3)),
            new BinaryTreeNode<>(
                6, a, new BinaryTreeNode<>(7, node2 = new BinaryTreeNode<>(9), b)));
    System.out.println(root);
    node1 = a;

    BinaryTreeNode<Integer> lca = findLCA(root, node1, node2);
    System.out.println(lca);

    BinaryTreeNodeWithParent<Integer> ap = new BinaryTreeNodeWithParent<>(5);
    BinaryTreeNodeWithParent<Integer> bp = new BinaryTreeNodeWithParent<>(8, null, null);
    BinaryTreeNodeWithParent<Integer> rootp =
        new BinaryTreeNodeWithParent<>(
            4,
            new BinaryTreeNodeWithParent<>(
                2, new BinaryTreeNodeWithParent<>(1), new BinaryTreeNodeWithParent<>(3)),
            new BinaryTreeNodeWithParent<>(
                6, ap, new BinaryTreeNodeWithParent<>(7, new BinaryTreeNodeWithParent<>(9), bp)));
    System.out.println(rootp);
    BinaryTreeNodeWithParent<Integer> lcap = findLCAP(rootp, ap, bp);
    System.out.println(lcap);
  }

  private static BinaryTreeNodeWithParent<Integer> findLCAP(
      BinaryTreeNodeWithParent<Integer> root,
      BinaryTreeNodeWithParent<Integer> a,
      BinaryTreeNodeWithParent<Integer> b) {
    int depthA = findDepth(a);
    int depthB = findDepth(b);
    if (depthA < depthB) {
      BinaryTreeNodeWithParent<Integer> temp = a;
      a = b;
      b = temp;
    }
    int diff = Math.abs(depthA - depthB);
    while (diff > 0) {
      a = a.parent;
      diff--;
    }
    while (a != b) {
      a = a.parent;
      b = b.parent;
    }

    return a;
  }

  private static int findDepth(BinaryTreeNodeWithParent<Integer> a) {
    BinaryTreeNodeWithParent<Integer> iter = a;
    int depth = 0;
    while (iter != null) {
      depth++;
      iter = iter.parent;
    }
    return depth;
  }

  private static BinaryTreeNode<Integer> findLCA(
      BinaryTreeNode<Integer> root, BinaryTreeNode<Integer> node1, BinaryTreeNode<Integer> node2) {
    CountWithLCA countWithLCA = findLCAHelper(root, node1, node2);
    return countWithLCA.lca;
  }

  private static CountWithLCA findLCAHelper(
      BinaryTreeNode<Integer> root, BinaryTreeNode<Integer> node1, BinaryTreeNode<Integer> node2) {

    if (root == null) {
      return new CountWithLCA(0, null);
    }

    CountWithLCA left = findLCAHelper(root.left, node1, node2);
    if (left.numNodes == 2) {
      return left;
    }

    CountWithLCA right = findLCAHelper(root.right, node1, node2);
    if (right.numNodes == 2) {
      return right;
    }
    int count = right.numNodes + left.numNodes + (root == node1 ? 1 : 0) + (root == node2 ? 1 : 0);
    CountWithLCA result =
        count == 2 ? new CountWithLCA(count, root) : new CountWithLCA(count, null);

    return result;
  }

  public static <T extends Comparable<T>> List<BinaryTreeNode<T>> inorder(BinaryTreeNode<T> root) {
    List<BinaryTreeNode<T>> result = new ArrayList<>();
    Deque<BinaryTreeNode<T>> stack = new LinkedList<>();
    BinaryTreeNode<T> current = root;
    while (current != null || !stack.isEmpty() ) {

      if (current != null) {
        stack.push(current);
        current = current.left;
      } else {
        current = stack.pop();
        result.add(current);
        current = current.right;
      }
    }
    return result;
  }

  public static <T extends Comparable<T>> List<BinaryTreeNode<T>> preorder(BinaryTreeNode<T> root) {
    List<BinaryTreeNode<T>> result = new ArrayList<>();
    Deque<BinaryTreeNode<T>> stack = new LinkedList<>();
    stack.offerLast(root);
    while (  !stack.isEmpty() ) {
         BinaryTreeNode<T> node = stack.pollLast();
         result.add(node);
         if (node.right!= null) {
            stack.offerLast(node.right);
         }
         if (node.left!= null) {
            stack.offerLast(node.left);
         }

    }
    return result;
  }

  private static class CountWithLCA {
    int numNodes;
    BinaryTreeNode<Integer> lca;

    CountWithLCA(int numNodes, BinaryTreeNode<Integer> lca) {
      this.numNodes = numNodes;
      this.lca = lca;
    }
  }
}
