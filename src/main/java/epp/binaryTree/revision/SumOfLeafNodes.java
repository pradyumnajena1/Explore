package epp.binaryTree.revision;

import epp.binaryTree.BinaryTreeNode;

public class SumOfLeafNodes {
  public static void main(String[] args) {
    BinaryTreeNode<Integer> root =
        new BinaryTreeNode<>(
            1,
            new BinaryTreeNode<>(
                0,
                new BinaryTreeNode<>(0, new BinaryTreeNode<>(0), new BinaryTreeNode<>(1)),
                new BinaryTreeNode<>(
                    1, null, new BinaryTreeNode<>(1, new BinaryTreeNode<>(0), null))),
            new BinaryTreeNode<>(
                1,
                new BinaryTreeNode<>(
                    0,
                    null,
                    new BinaryTreeNode<>(
                        0,
                        new BinaryTreeNode<>(1, null, new BinaryTreeNode<>(1)),
                        new BinaryTreeNode<>(0))),
                new BinaryTreeNode<>(0, null, new BinaryTreeNode<>(0))));
    System.out.println(root);
    int sum = getLeafSum(root);
    System.out.println(sum);
  }

  private static int getLeafSum(BinaryTreeNode<Integer> root) {
    return getLeafSumHelper(root, 0);
  }

    /**
     * complexity calculation. time complexity O(n), where n is the number of nodes
     * space complexity O(log(n))
     * @param root
     * @param partialPathSum
     * @return
     */
  private static int getLeafSumHelper(BinaryTreeNode<Integer> root, int partialPathSum) {
    if (root == null) {
      return 0;
    }
    partialPathSum = partialPathSum * 2 + root.data;
    //leaf
    if (root.isLeafNode()) {
      return partialPathSum;
    }
    //non leaf
    return getLeafSumHelper(root.left, partialPathSum)
        + getLeafSumHelper(root.right, partialPathSum);
  }
}
