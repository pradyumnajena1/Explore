package epp.recursion.revision;

import epp.binaryTree.BinaryTreeNode;
import epp.binarysearchtree.BSTUtils;

/** longest path from a node to another in a given tree */
public class DiameterOfATree {
  public static void main(String[] args) {
    BinaryTreeNode<Integer> root = BSTUtils.buildBST(10, 1, 20);
    System.out.println(root);
    int diameterOfTree = diameterOfTree(root);
    System.out.println(diameterOfTree);
  }

  private static int diameterOfTree(BinaryTreeNode<Integer> root) {
    return   getHeightAndDiameterOfTree(root).diameter;
  }

  private static HeightAndDiameter getHeightAndDiameterOfTree(BinaryTreeNode<Integer> root) {
    // height is num of edges
    if (root == null) {
      return new HeightAndDiameter(-1, -1);
    }
    HeightAndDiameter left = getHeightAndDiameterOfTree(root.left);
    HeightAndDiameter right = getHeightAndDiameterOfTree(root.right);
    int height = Math.max(left.height+1, right.height+1)  ;
    int diameter =
        left.height+1 + right.height + 1;
    diameter = Math.max(diameter, Math.max(left.diameter, right.diameter));
    return new HeightAndDiameter(height, diameter);
  }

  private static class HeightAndDiameter {
    int height;
    int diameter;

    public HeightAndDiameter(int height, int diameter) {
      this.height = height;
      this.diameter = diameter;
    }
  }
}
