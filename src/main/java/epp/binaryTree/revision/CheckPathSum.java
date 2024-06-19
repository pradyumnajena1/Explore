package epp.binaryTree.revision;

import epp.binaryTree.BinaryTreeNode;

import java.util.ArrayList;
import java.util.List;

public class CheckPathSum {
  public static void main(String[] args) {
    BinaryTreeNode<Integer> a = new BinaryTreeNode<>(5);
    BinaryTreeNode<Integer> b = new BinaryTreeNode<>(8, null, null);
    BinaryTreeNode<Integer> root =
        new BinaryTreeNode<>(
            4,
            new BinaryTreeNode<>(2, new BinaryTreeNode<>(1), new BinaryTreeNode<>(3)),
            new BinaryTreeNode<>(6, a, new BinaryTreeNode<>(7, new BinaryTreeNode<>(9), b)));
    System.out.println(root);
    boolean isPresent = checkPathSum(root, 15);
    System.out.println(isPresent);
    List<List<BinaryTreeNode<Integer>>> allPathsWithSum = getAllPathsWithSum(root, 15);
    for (List<BinaryTreeNode<Integer>> path : allPathsWithSum) {
      path.stream().map(x -> x.data +", ").forEach(System.out::print);
      System.out.println();
    }
  }

  private static boolean checkPathSum(BinaryTreeNode<Integer> root, int targetSum) {
    return checkPathSum(root, targetSum, 0);
  }

  private static boolean checkPathSum(
      BinaryTreeNode<Integer> root, int targetSum, int partialPathSum) {
    if (root == null) {
      return false;
    }
    partialPathSum = partialPathSum + root.data;
    // leaf
    if (root.isLeafNode()) {
      return partialPathSum == targetSum;
    }
    // non leaf
    return checkPathSum(root.left, targetSum, partialPathSum)
        || checkPathSum(root.right, targetSum, partialPathSum);
  }

  private static List<List<BinaryTreeNode<Integer>>> getAllPathsWithSum(
      BinaryTreeNode<Integer> root, int targetSum) {
    List<BinaryTreeNode<Integer>> partialPath = new ArrayList<BinaryTreeNode<Integer>>();
    List<List<BinaryTreeNode<Integer>>> resultCollector =
        new ArrayList<List<BinaryTreeNode<Integer>>>();
    getAllPathsWithSumHelper(root, targetSum, partialPath, 0, resultCollector);
    return resultCollector;
  }

  private static void getAllPathsWithSumHelper(
      BinaryTreeNode<Integer> root,
      int targetSum,
      List<BinaryTreeNode<Integer>> partialPath,
      int partialSum,
      List<List<BinaryTreeNode<Integer>>> resultCollector) {
    if (root == null) {
      return;
    }
    partialSum = partialSum + root.data;
    partialPath.add(root);

    if (root.isLeafNode()) {
      if (partialSum == targetSum) {
        resultCollector.add(new ArrayList<>(partialPath));
      }
    }
    getAllPathsWithSumHelper(root.left, targetSum, partialPath, partialSum, resultCollector);
    getAllPathsWithSumHelper(root.right, targetSum, partialPath, partialSum, resultCollector);

    partialPath.remove(partialPath.size() - 1);
  }
}
