package epp.binaryTree.revision;

import epp.binaryTree.BinaryTreeNode;

public class TestBalancedTree {
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
    System.out.println(isBalancedTree(root));
  }

  public static boolean isBalancedTree(BinaryTreeNode<Integer> root) {

    HeightWithBalance heightWithBalance = checkBalance(root);

    return heightWithBalance.balanced;
  }

  private static HeightWithBalance checkBalance(BinaryTreeNode<Integer> root) {
    if (root == null) {
      return new HeightWithBalance(true, 0);
    }
    HeightWithBalance leftHeight = checkBalance(root.left);
    if (!leftHeight.balanced) {
      return leftHeight;
    }
    HeightWithBalance rightHeight = checkBalance(root.right);
    if (!rightHeight.balanced) {
      return rightHeight;
    }
    int height = Math.max(leftHeight.height, rightHeight.height) + 1;
    boolean isBalanced = Math.abs(leftHeight.height - rightHeight.height) <= 1;
    return new HeightWithBalance(isBalanced, height);
  }

  private static class HeightWithBalance {
    boolean balanced;
    int height;

    public HeightWithBalance(boolean balanced, int height) {
      this.balanced = balanced;
      this.height = height;
    }
  }
}
