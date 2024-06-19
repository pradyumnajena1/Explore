package epp.binaryTree.revision;

import epp.binaryTree.BinaryTreeNode;
import epp.binaryTree.BinaryTreeNodeWithParent;
import epp.binaryTree.BinaryTreeUtils;
import epp.binarysearchtree.BSTUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateTreeFromInOrderAndPreOrderTraversal {
  public static void main(String[] args) {
    BinaryTreeNode<Integer> root = BSTUtils.buildBSTWithUniqueValues(10, 1, 12);
    System.out.println(root);
    List<Integer> inOrderTraversal = BinaryTreeUtils.inOrderTraversal(root);
    List<Integer> preOrderTraversal = BinaryTreeUtils.preOrderTraversal(root);
    BinaryTreeNode<Integer> newRoot =
        createTreeFromInOrderAndPreOrder(inOrderTraversal, preOrderTraversal);
    System.out.println(newRoot);
  }

  private static BinaryTreeNode<Integer> createTreeFromInOrderAndPreOrder(
      List<Integer> inOrderTraversal, List<Integer> preOrderTraversal) {
    Map<Integer, Integer> inorderNodeToIndexMap = new HashMap<>();
    for (int i = 0; i < inOrderTraversal.size(); i++) {
      inorderNodeToIndexMap.put(inOrderTraversal.get(i), i);
    }

    return createTreeFromInOrderAndPreOrder(
        inOrderTraversal,
        0,
        inOrderTraversal.size(),
        preOrderTraversal,
        0,
        preOrderTraversal.size(),
        inorderNodeToIndexMap);
  }

  private static BinaryTreeNode<Integer> createTreeFromInOrderAndPreOrder(
      List<Integer> inOrderTraversal,
      int inOrderStart,
      int inOrderEnd,
      List<Integer> preOrderTraversal,
      int preOrderStart,
      int preOrderEnd,
      Map<Integer, Integer> inorderNodeToIndexMap) {

    if (inOrderEnd <= inOrderStart || preOrderEnd <= preOrderStart) {
      return null;
    }
    int rootInorderIndex = inorderNodeToIndexMap.get(preOrderTraversal.get(preOrderStart));

    int leftSize = rootInorderIndex - inOrderStart;
    return new BinaryTreeNode<>(
        preOrderTraversal.get(preOrderStart),
        createTreeFromInOrderAndPreOrder(
            inOrderTraversal,
            inOrderStart,
            rootInorderIndex,
            preOrderTraversal,
            preOrderStart + 1,
            preOrderStart+1 + leftSize,
            inorderNodeToIndexMap),
        createTreeFromInOrderAndPreOrder(
            inOrderTraversal,
            rootInorderIndex + 1,
            inOrderEnd,
            preOrderTraversal,
            preOrderStart + 1 + leftSize,
            preOrderEnd,
            inorderNodeToIndexMap));
  }
}
