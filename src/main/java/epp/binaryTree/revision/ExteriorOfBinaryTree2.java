package epp.binaryTree.revision;

import epp.binaryTree.BinaryTreeNode;
import java.util.ArrayList;
import java.util.List;

public class ExteriorOfBinaryTree2 {
  public static void main(String[] args) {
    BinaryTreeNode<Character> nodeB =
        new BinaryTreeNode<>(
            'B',
            new BinaryTreeNode<>('C', new BinaryTreeNode<>('D'), new BinaryTreeNode<>('E')),
            new BinaryTreeNode<>(
                'F', null, new BinaryTreeNode<>('G', new BinaryTreeNode<>('H'), null)));
    BinaryTreeNode<Character> nodeI =
        new BinaryTreeNode<>(
            'I',
            new BinaryTreeNode<>(
                'J',
                null,
                new BinaryTreeNode<>(
                    'K',
                    new BinaryTreeNode<>('L', null, new BinaryTreeNode<>('M')),
                    new BinaryTreeNode<>('N'))),
            new BinaryTreeNode<>('O', null, new BinaryTreeNode<>('P')));
    BinaryTreeNode<Character> root = new BinaryTreeNode<>('A', null, nodeI);
    System.out.println(root);
    System.out.println(getExteriorOfBinaryTree(root));
  }

  private static <T extends Comparable<T>> List<T> getExteriorOfBinaryTree(BinaryTreeNode<T> root) {
    List<T> result = new ArrayList<>();
    if (root == null) {
      return result;
    }
    result.add(root.data);
    if (root.isLeafNode()) {
      return result;
    }

    getLeftWall(root.left, result);
    getLeaves(root, result);
    getRightWall(root.right, result);
    return result;
  }

  private static <T extends Comparable<T>> void getLeaves(BinaryTreeNode<T> root, List<T> result) {
    if (root!=null) {
      getLeaves(root.left, result);
      if (root.isLeafNode()) {
        result.add(root.data);
      }
      getLeaves(root.right, result);
    }
  }

  private static <T extends Comparable<T>> void getLeftWall(
      BinaryTreeNode<T> root, List<T> result) {
    while (root != null && !root.isLeafNode()) {

      result.add(root.data);

      if (root.left != null) {
        root = root.left;
      } else {
        root = root.right;
      }
    }
  }

  private static <T extends Comparable<T>> void getRightWall(
      BinaryTreeNode<T> root, List<T> result) {
    List<T> temp = new ArrayList<>();
    while (root != null && !root.isLeafNode()) {
      temp.add(root.data);
      if (root.right != null) {
        root = root.right;
      } else {
        root = root.left;
      }
    }
    for (int i = temp.size() - 1; i >= 0; i--) {
      result.add(temp.get(i));
    }
  }
}
