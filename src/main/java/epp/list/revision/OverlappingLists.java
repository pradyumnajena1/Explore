package epp.list.revision;

import epp.list.LinkedListNode;

public class OverlappingLists {

  public static void main(String[] args) {
    LinkedListNode<Integer> cycleTail = new LinkedListNode<>(6);
    LinkedListNode<Integer> cycleNode5 = new LinkedListNode<>(5, cycleTail);
    LinkedListNode<Integer> cycleNode4 = new LinkedListNode<>(4, cycleNode5);
    LinkedListNode<Integer> cycle = new LinkedListNode<>(3, cycleNode4);
    cycleTail.next = cycle;

    LinkedListNode<Integer> l1 = new LinkedListNode<>(1, new LinkedListNode<>(2, cycleNode4));
    LinkedListNode<Integer> l2 = new LinkedListNode<>(7, new LinkedListNode<>(8, cycleNode5));
    LinkedListNode<Integer> firstCommonNode = overlappingCycles(l1, l2);
    System.out.println(firstCommonNode == null ? null : firstCommonNode.data);
  }

  public static LinkedListNode<Integer> overlappingCycles(
      LinkedListNode<Integer> l1, LinkedListNode<Integer> l2) {

    LinkedListNode<Integer> root1 = TestForCircularity.findStartOfCircle(l1);
    LinkedListNode<Integer> root2 = TestForCircularity.findStartOfCircle(l2);
    if (root1 == null && root2 == null) {
      return TestForCommonNode.findFirstCommonNode(l1, l2);
    }

    if (root1 != null && root2 == null || root1 == null && root2 != null) {
      return null;
    }

    // if both have cycles
    LinkedListNode<Integer> start = root1;
    do {
      start = start.next;
    } while (start != root1 && start != root2);
    if (start == root1) {
      return null;
    }
    // LI and L2 end in the same cycle, locate the overlapping node if they
    // first overlap before cycle starts.
    int stem1Length = getLength(l1, root1);
    int stem2Length = getLength(l2, root2);
    if (stem1Length > stem2Length) {
      l1 = advanceListByK(l1, stem1Length - stem2Length);
    } else {
      l2 = advanceListByK(l2, stem2Length - stem1Length);
    }
    while (l1 != l2 && l1 != root1 && l2 != root2) {
      l1 = l1.next;
      l2 = l2.next;
    }
    if (l1 == l2) {
      return l1;
    }

    return root1;
  }

  private static LinkedListNode<Integer> advanceListByK(LinkedListNode<Integer> head, int count) {
    while (count-- > 0) {
      head = head.next;
    }
    return head;
  }

  private static int getLength(LinkedListNode<Integer> head, LinkedListNode<Integer> node) {
    int length = 0;
    while (head != null && head != node) {
      length++;
      head = head.next;
    }
    return length;
  }
}
