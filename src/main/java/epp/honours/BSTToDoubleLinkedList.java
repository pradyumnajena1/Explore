package epp.honours;

import epp.binaryTree.BinaryTreeNode;
import epp.binarysearchtree.BSTUtils;

public class BSTToDoubleLinkedList {

  public static void main(String[] args) {
    BinaryTreeNode<Integer> root = BSTUtils.buildBST(10, 1, 20);
    System.out.println(root);
    BinaryTreeNode<Integer> list = bstToDoubleLinkedList(root);
    BinaryTreeNode<Integer> iterator = list ;
    while (iterator != null) {
      System.out.println(iterator.data);
      iterator = iterator.right;
    }
  }

  public static BinaryTreeNode<Integer> bstToDoubleLinkedList(
          BinaryTreeNode<Integer> tree) {
   return bstToDoubleLinkedListHelper(tree).head;
  }
  private static HeadAndTail bstToDoubleLinkedListHelper(
      BinaryTreeNode<Integer> tree) {

    if (tree == null) {
      return new HeadAndTail(null,null);
    }
    HeadAndTail left = bstToDoubleLinkedListHelper(tree.left);
    HeadAndTail right = bstToDoubleLinkedListHelper(tree.right);
    if (left.tail != null) {
      left.tail.right = tree;
    }
    tree.left = left.tail ;

    if (right.head != null) {
      right.head.left = tree;
    }
    tree.right = right.head;

    return new HeadAndTail(left.head!=null?left.head:tree, right.tail!=null?right.tail:tree);
  }

  private static class HeadAndTail{
    BinaryTreeNode<Integer> head,tail;

    public HeadAndTail(BinaryTreeNode<Integer> head, BinaryTreeNode<Integer> tail) {
      this.head = head;
      this.tail = tail;
    }
  }
}
