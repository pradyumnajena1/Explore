package epp.list.revision;

import epp.list.LinkedListNode;

public class RemoveKthLastNode {
  public static void main(String[] args) {
    LinkedListNode<Integer> list = LinkedListNode.createList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
    System.out.println(list);
    list = deleteKthLastNode(list, 3);
    System.out.println(list);
  }

  private static LinkedListNode<Integer> deleteKthLastNode(LinkedListNode<Integer> list, int n) {
    LinkedListNode<Integer> dummyHead = new LinkedListNode<>(0, list);
    LinkedListNode<Integer> first = dummyHead.next;
    while (n-- > 0) {
      first = first.next;
    }
    LinkedListNode<Integer> second = dummyHead.next;
    while (first.next != null) {
      first = first.next;
      second = second.next;
    }
    second.next = second.next.next;
    return dummyHead.next;
  }
}
