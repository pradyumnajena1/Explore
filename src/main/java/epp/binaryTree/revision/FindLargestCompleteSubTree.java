package epp.binaryTree.revision;

import epp.binaryTree.BinaryTreeNode;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Write a program that returns the size of the largest subtree that is complete
 *
 */
public class FindLargestCompleteSubTree {

  public static void main(String[] args) {

    BinaryTreeNode<Integer> root =
            new BinaryTreeNode<>(
                    4,
                    new BinaryTreeNode<>(2, new BinaryTreeNode<>(1), new BinaryTreeNode<>(3)),
                    new BinaryTreeNode<>(
                            6,
                            new BinaryTreeNode<>(5),
                            new BinaryTreeNode<>(
                                    7, null, new BinaryTreeNode<>(8, null, new BinaryTreeNode<>(9)))));
    System.out.println(root);
    System.out.println(findLargestCompleteSubTree(root));
  }

  private static int findLargestCompleteSubTree(BinaryTreeNode<Integer> root) {
    AtomicInteger max = new AtomicInteger(0);
    findLargestCompleteSubTreeHelper(root, max);
    return max.get();
  }

  private static SizeWithCompleteness findLargestCompleteSubTreeHelper(
      BinaryTreeNode<Integer> root, AtomicInteger max) {
    if (root == null) {
      return new SizeWithCompleteness(true, 0, 0);
    }
    final SizeWithCompleteness leftResult =
        findLargestCompleteSubTreeHelper(root.left, max);

    final SizeWithCompleteness rightResult =
        findLargestCompleteSubTreeHelper(root.right, max);

    boolean isComplete = false;
    int size = 0;
    int height = Math.max(leftResult.height, rightResult.height) + 1;
    if (leftResult.isComplete && rightResult.isComplete) {
      if (leftResult.height == rightResult.height || leftResult.height == rightResult.height + 1) {
        size = leftResult.size + rightResult.size + 1;
        isComplete = true;
      }
    }
    if (isComplete) {
      max.set(Math.max(max.get(), size));
    }
    return new SizeWithCompleteness(isComplete, size, height);
  }

  private static class SizeWithCompleteness {
    boolean isComplete;
    int size;
    int height;

    public SizeWithCompleteness(boolean isComplete, int size, int height) {
      this.isComplete = isComplete;
      this.size = size;
      this.height = height;
    }
  }
}
