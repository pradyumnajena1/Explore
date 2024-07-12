package epp.recursion.revision;

import epp.binaryTree.BinaryTreeNode;
import java.util.ArrayList;
import java.util.List;

public class EnumerateDistinctBinaryTrees {
  public static void main(String[] args) {
    List<BinaryTreeNode<Integer>> trees = enumerateBinaryTrees(3);
    System.out.println(trees);
  }

  private static List<BinaryTreeNode<Integer>> enumerateBinaryTrees(int n) {
    List<BinaryTreeNode<Integer>> result = new ArrayList<>();
    if (n == 0) {
      result.add(null);
      return result;
    }
    for (int numLeftNodes = 0; numLeftNodes < n; numLeftNodes++) {
      int numRightNodes = n - 1 - numLeftNodes;
      List<BinaryTreeNode<Integer>> leftSubtrees = enumerateBinaryTrees(numLeftNodes);
      List<BinaryTreeNode<Integer>> rightSubtrees = enumerateBinaryTrees(numRightNodes);

      for (BinaryTreeNode<Integer> leftSubtree : leftSubtrees) {
        for (BinaryTreeNode<Integer> rightSubtree : rightSubtrees) {
          BinaryTreeNode<Integer> root = new BinaryTreeNode<>(0, leftSubtree, rightSubtree);
          result.add(root);
        }
      }
    }

    return result;
  }
}
