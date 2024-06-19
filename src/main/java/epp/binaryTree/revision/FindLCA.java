package epp.binaryTree.revision;

import epp.binaryTree.BinaryTreeNode;
import epp.binaryTree.BinaryTreeNodeWithParent;

public class FindLCA {
  public static void main(String[] args) {
    BinaryTreeNode<Integer> a = new BinaryTreeNode<>(5);
    BinaryTreeNode<Integer> b = new BinaryTreeNode<>(8, null, null);
    BinaryTreeNode<Integer> root =
        new BinaryTreeNode<>(
            4,
            new BinaryTreeNode<>(2, new BinaryTreeNode<>(1), new BinaryTreeNode<>(3)),
            new BinaryTreeNode<>(6, a, new BinaryTreeNode<>(7, new BinaryTreeNode<>(9), b)));
    System.out.println(root);
    Status lca = lcaHelper(root, a, b);
    System.out.println(lca.ancestor);
  }

  private static Status lcaHelper(
      BinaryTreeNode<Integer> root, BinaryTreeNode<Integer> a, BinaryTreeNode<Integer> b) {
    if (root == null) {
      return new Status(0, null);
    }
    Status leftResult = lcaHelper(root.left, a, b);
    Status rightResult = lcaHelper(root.right, a, b);
    if (leftResult.numNodesFound == 2) {
      return leftResult;
    }
    if (rightResult.numNodesFound == 2) {
      return rightResult;
    }
    int numNodesFound =
        leftResult.numNodesFound
            + rightResult.numNodesFound
            + (root == a ? 1 : 0)
            + (root == b ? 1 : 0);

    return new Status(numNodesFound, numNodesFound == 2 ? root : null);
  }

  private static class Status {
    int numNodesFound;
    BinaryTreeNode<Integer> ancestor;

    public Status(int numNodesFound, BinaryTreeNode<Integer> ancestor) {
      this.numNodesFound = numNodesFound;
      this.ancestor = ancestor;
    }
  }
}
