package epp.honours;

import epp.DoubleLinkedListNode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class DoublyLinkedListToBST {
  public static void main(String[] args) {
    DoubleLinkedListNode<Integer> node1 = new DoubleLinkedListNode<>(1);
    DoubleLinkedListNode<Integer> node2 = new DoubleLinkedListNode<>(2);
    DoubleLinkedListNode<Integer> node3 = new DoubleLinkedListNode<>(3);
    DoubleLinkedListNode<Integer> node4 = new DoubleLinkedListNode<>(4);
    DoubleLinkedListNode<Integer> node5 = new DoubleLinkedListNode<>(5);
    node1.setNext(node2);
    node2.setNext(node3);
    node3.setNext(node4);
    node4.setNext(node5);

    DoubleLinkedListNode<Integer> list = node1;

    DoubleLinkedListNode<Integer> root = createBSTFromDoubleLinkedList(list);
    HashMap<Integer, List<Integer>> map = new HashMap<>();
    dfs(root, map, 0);
    System.out.println(map);
  }

  private static void dfs(
      DoubleLinkedListNode<Integer> root, HashMap<Integer, List<Integer>> map, int i) {
    if (root == null) {
      return;
    }
    dfs(root.getPrev(), map, i + 1);
    map.computeIfAbsent(i, k -> new ArrayList<>()).add(root.getData());
    dfs(root.getNext(), map, i + 1);
  }

  private static DoubleLinkedListNode<Integer> createBSTFromDoubleLinkedList(
      DoubleLinkedListNode<Integer> list) {
    return createBSTFromDoubleLinkedList(list, getLength(list));
  }

  private static DoubleLinkedListNode<Integer> createBSTFromDoubleLinkedList(
      DoubleLinkedListNode<Integer> list, int length) {
    AtomicReference<DoubleLinkedListNode<Integer>> head = new AtomicReference<>(list);
    return createBSTFromDoubleLinkedListHelper(list, 0, length - 1, head);
  }

  private static DoubleLinkedListNode<Integer> createBSTFromDoubleLinkedListHelper(
      DoubleLinkedListNode<Integer> list,
      int start,
      int end,
      AtomicReference<DoubleLinkedListNode<Integer>> head) {
    if (start > end) {
      return null;
    }
    System.out.println(start + " " + end);
    int mid = (start + end) / 2;
    DoubleLinkedListNode<Integer> left =
        createBSTFromDoubleLinkedListHelper(list, start, mid - 1, head);
    DoubleLinkedListNode<Integer> current = new DoubleLinkedListNode<Integer>(head.get().getData());
    current.setPrev(left);
    head.set(head.get().getNext());
    DoubleLinkedListNode<Integer> right =
        createBSTFromDoubleLinkedListHelper(list, mid + 1, end, head);
    current.setNext(right);
    return current;
  }

  private static int getLength(DoubleLinkedListNode<Integer> list) {
    int count = 0;
    while (list != null) {
      count++;
      list = list.getNext();
    }
    return count;
  }
}
