package epp.honours;

import epp.binaryTree.BinaryTreeNode;
import epp.binarysearchtree.BSTUtils;
import java.util.concurrent.atomic.AtomicReference;

public class MergeTwoBSTs {
  public static void main(String[] args) {
    BinaryTreeNode<Integer> tree1 = BSTUtils.buildBST(10, 1, 20);
    BinaryTreeNode<Integer> tree2 = BSTUtils.buildBST(10, 5, 25);
    System.out.println(tree1);
    System.out.println(tree2);
    BinaryTreeNode<Integer> tree = mergeTwoBSTs(tree1, tree2);
    System.out.println(tree);
  }

  private static BinaryTreeNode<Integer> mergeTwoBSTs(
      BinaryTreeNode<Integer> tree1, BinaryTreeNode<Integer> tree2) {
    if (tree1 == null) return tree2;
    if (tree2 == null) return tree1;
    BinaryTreeNode<Integer> list1 = BSTToDoubleLinkedList.bstToDoubleLinkedList(tree1);
    BinaryTreeNode<Integer> list2 = BSTToDoubleLinkedList.bstToDoubleLinkedList(tree2);

    BinaryTreeNode<Integer> list = mergeTwoDoubleLinkedLists(list1, list2);

    return createBSTFromDoubleLinkedList(list);
  }

  private static BinaryTreeNode<Integer> mergeTwoDoubleLinkedLists(
      BinaryTreeNode<Integer> list1, BinaryTreeNode<Integer> list2) {
    BinaryTreeNode<Integer> dummy = new BinaryTreeNode<Integer>(0);
    BinaryTreeNode<Integer> current = dummy;
    while (list1 != null && list2 != null) {
      if (list1.data < list2.data) {
        BinaryTreeNode<Integer> temp = list1;
        list1 = list1.right;
        current.right = temp;
      } else {
        BinaryTreeNode<Integer> temp = list2;
        list2 = list2.right;
        current.right = temp;
      }
      current = current.right;
    }
    if (list1 != null) {
      current.right = list1;
    }
    if (list2 != null) {
      current.right = list2;
    }

    return dummy.right;
  }

  private static BinaryTreeNode<Integer> createBSTFromDoubleLinkedList(
      BinaryTreeNode<Integer> list) {
    int length = getLength(list);

    return createBSTFromDoubleLinkedList(list, length);
  }

  private static BinaryTreeNode<Integer> createBSTFromDoubleLinkedList(
      BinaryTreeNode<Integer> list, int length) {
    AtomicReference<BinaryTreeNode<Integer>> head = new AtomicReference<>(list);
    return createBSTFromDoubleLinkedListHelper(list, 0, length - 1, head);
  }

  private static BinaryTreeNode<Integer> createBSTFromDoubleLinkedListHelper(
      BinaryTreeNode<Integer> list,
      int start,
      int end,
      AtomicReference<BinaryTreeNode<Integer>> head) {
    if (start > end) {
      return null;
    }

    int mid = (start + end) / 2;
    BinaryTreeNode<Integer> left = createBSTFromDoubleLinkedListHelper(list, start, mid - 1, head);
    BinaryTreeNode<Integer> current = new BinaryTreeNode<>(head.get().data);
    current.left = (left);
    head.set(head.get().right);
    BinaryTreeNode<Integer> right = createBSTFromDoubleLinkedListHelper(list, mid + 1, end, head);
    current.right = (right);
    return current;
  }

  private static int getLength(BinaryTreeNode<Integer> list) {
    int count = 0;
    while (list != null) {
      count++;
      list = list.right;
    }
    return count;
  }
}
