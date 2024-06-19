package epp.binaryTree.revision;

import epp.binaryTree.BinaryTreeNode;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Define a node in a binary tree to be k-balanced if the difference in the number
 * of nodes in its left and right subtrees is no more than k. Design an algorithm that
 * takes as input a binary tree and positive integer k, and returns a node in the binary
 * tree such that the node is not k-balanced, but all of its descendants are k-balanced.
 * For example, when applied to the binary tree in Figure10.1 on Page150, if k = 3, your
 * algorithm should return Node /
 */
public class CheckKBalanced {
  public static void main(String[] args) {
    BinaryTreeNode<Integer> root =
        new BinaryTreeNode<>(
            4,
            new BinaryTreeNode<>(2, new BinaryTreeNode<>(1), new BinaryTreeNode<>(3)),
            new BinaryTreeNode<>(
                6,
                new BinaryTreeNode<>(5),
                new BinaryTreeNode<>(
                    7,
                    null,
                    new BinaryTreeNode<>(
                        8, null, new BinaryTreeNode<>(9, null, new BinaryTreeNode<>(22))))));
    System.out.println(root);
    BinaryTreeNode<Integer> Kbalanced = checkKBalanced(root, 2);
    System.out.println(Kbalanced);
  }

  private static BinaryTreeNode<Integer> checkKBalanced(BinaryTreeNode<Integer> root, int k) {
    AtomicReference<BalancedWithSize> collector = new AtomicReference<>(null);
    checkKBalanced(root, k, collector);
    return collector.get().node;
  }

  private static BalancedWithSize checkKBalanced(
      BinaryTreeNode<Integer> root, int k, AtomicReference<BalancedWithSize> collector) {
    if (root == null) {
      return new BalancedWithSize(true, 0, null);
    }
    BalancedWithSize left = checkKBalanced(root.left, k, collector);
    BalancedWithSize right = checkKBalanced(root.right, k, collector);
    boolean balanced = false;
    int size = left.size + right.size + 1;
    if (left.balanced && right.balanced) {
      if (Math.abs(left.size - right.size) <= k) {
        balanced = true;
      }
    }
    BalancedWithSize balancedWithSize = new BalancedWithSize(balanced, size, root);
    if (balanced) {
      if (collector.get() == null || collector.get().size < balancedWithSize.size){

        collector.set(balancedWithSize);
      }
    }
    return balancedWithSize;
  }

  private static class BalancedWithSize {
    boolean balanced;
    int size;
    BinaryTreeNode<Integer> node;

    public BalancedWithSize(boolean balanced, int size, BinaryTreeNode<Integer> node) {
      this.balanced = balanced;
      this.size = size;
      this.node = node;
    }
  }
}
