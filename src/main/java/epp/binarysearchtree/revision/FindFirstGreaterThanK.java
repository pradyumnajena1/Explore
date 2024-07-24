package epp.binarysearchtree.revision;

import epp.array.ArrayUtils;
import epp.binaryTree.BinaryTreeNode;
import epp.binarysearchtree.BSTUtils;

public class FindFirstGreaterThanK {
  public static void main(String[] args) {
    int[] values = ArrayUtils.randomSortedArray(10, 1, 5);
    ArrayUtils.printArray(values);
    BinaryTreeNode<Integer> root = BSTUtils.buildBSTFromSortedArray(values);
    System.out.println(root);
    int value = values[7];
    System.out.println(value);
    BinaryTreeNode<Integer> node = findFirstGreaterThanK(root, value);
    System.out.println(node);
    node = findFirstGreaterThanK2(root, value);
    System.out.println(node);

    node = findLastSmallerThanK(root, value);
    System.out.println(node);
    node = findLastSmallerThanK2(root, value);
    System.out.println(node);

    node = findFirstEqualToK(root, value);
    System.out.println(node);
    node = findFirstEqualToK2(root, value);
    System.out.println(node);
  }

  public static BinaryTreeNode<Integer> findFirstGreaterThanK(
      BinaryTreeNode<Integer> root, int value) {
    BinaryTreeNode<Integer> subTree = root;
    BinaryTreeNode<Integer> firstSoFar = null;
    while (subTree != null) {
      if (subTree.data > value) {
        firstSoFar = subTree;
        subTree = subTree.left;
      } else {
        subTree = subTree.right;
      }
    }
    return firstSoFar;
  }
  public static BinaryTreeNode<Integer> findFirstGreaterThanK2(
          BinaryTreeNode<Integer> root, int value) {
    if(root == null){
      return null;
    }
    if(root.data>value){
      BinaryTreeNode<Integer> leftResult = findFirstGreaterThanK2(root.left, value);
      return leftResult==null?root:leftResult;
    }
    return findFirstGreaterThanK2(root.right, value);
  }

  public static BinaryTreeNode<Integer> findFirstEqualToK(
          BinaryTreeNode<Integer> root, int value) {
    BinaryTreeNode<Integer> subTree = root;
    BinaryTreeNode<Integer> firstSoFar = null;
    while (subTree != null) {
      if (subTree.data == value) {
        firstSoFar = subTree;
        subTree = subTree.left;
      } else if(subTree.data <value){
        subTree = subTree.right;
      }else{
        subTree = subTree.left;
      }
    }
    return firstSoFar;
  }
  public static BinaryTreeNode<Integer> findFirstEqualToK2(
          BinaryTreeNode<Integer> root, int value) {
    if(root == null){
      return null;
    }
    if(root.data==value){
      BinaryTreeNode<Integer> leftResult = findFirstEqualToK2(root.left, value);
      return leftResult==null?root:leftResult;
    }
    return findFirstEqualToK2(root.right, value);
  }

  public static BinaryTreeNode<Integer> findLastSmallerThanK(
          BinaryTreeNode<Integer> root, int value) {
    BinaryTreeNode<Integer> subTree = root;
    BinaryTreeNode<Integer> lastSoFar = null;
    while (subTree != null) {
      if (subTree.data < value) {
        lastSoFar = subTree;
        subTree = subTree.right;
      } else {
        subTree = subTree.left;
      }
    }
    return lastSoFar;
  }

  public static BinaryTreeNode<Integer> findLastSmallerThanK2(
          BinaryTreeNode<Integer> root, int value) {
    if(root == null){
      return null;
    }
    if(root.data < value) {
      BinaryTreeNode<Integer> rightResult = findLastSmallerThanK2(root.right, value);
      return  rightResult==null ? root : rightResult;
    }
    return findLastSmallerThanK2(root.left, value);
  }
}
