package epp.list.revision;

import epp.list.LinkedListNode;

public class CyclicallyRightShiftList {
  public static void main(String[] args) {
    LinkedListNode<Integer> list = LinkedListNode.createList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    System.out.println(list);
    list = cyclicallyRightShiftList(list, 3);
    System.out.println(list);
  }

  private static LinkedListNode<Integer> cyclicallyRightShiftList(
      LinkedListNode<Integer> list, int k) {
    if (list == null) {
      return null;
    }
    LinkedListNode<Integer> tail = list;
    int n = 1;
    while (tail.next != null) {
      tail = tail.next;
      n++;
    }
    k = k % n;
    tail.next = list;

    int stepsToNextHead = n - k;
    LinkedListNode<Integer> newTail = tail;
    while (stepsToNextHead-- > 0) {
      newTail = newTail.next;
    }
    LinkedListNode<Integer> newHead = newTail.next;
    newTail.next = null;

    return newHead;
  }
}
