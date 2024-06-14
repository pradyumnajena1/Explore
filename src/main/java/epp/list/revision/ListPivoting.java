package epp.list.revision;

import epp.list.LinkedListNode;

public class ListPivoting {
  public static void main(String[] args) {
    LinkedListNode<Integer> list = LinkedListNode.createList(6,7,2, 3, 5, 3, 2);
    System.out.println(list);
    list = pivotList(list, 3);
    System.out.println(list);
  }

  private static LinkedListNode<Integer> pivotList(LinkedListNode<Integer> list, int value) {
    LinkedListNode<Integer> smaller = new LinkedListNode<>(0);
    LinkedListNode<Integer> equal = new LinkedListNode<>(0);
    LinkedListNode<Integer> bigger = new LinkedListNode<>(0);

    LinkedListNode<Integer> smallerTail = smaller;
    LinkedListNode<Integer> equalTail = equal;
    LinkedListNode<Integer> biggerTail = bigger;

    LinkedListNode<Integer> listIter = list;

    while (listIter != null) {
      if (listIter.data < value) {
        smallerTail.next = listIter;
        smallerTail = listIter;
      } else if (listIter.data == value) {
        equalTail.next = listIter;
        equalTail = listIter;
      } else {
        biggerTail.next = listIter;
        biggerTail = listIter;
      }

      listIter = listIter.next;
    }
    biggerTail.next = null;
    smallerTail.next = equal.next;
    equalTail.next = bigger.next;

    return smaller.next;
  }
}
