package epp.list.revision;

import epp.list.LinkedListNode;

public class AddListBasedIntegers {
  public static void main(String[] args) {
    LinkedListNode<Integer> l1 = LinkedListNode.createList(3, 1, 4);
    LinkedListNode<Integer> l2 = LinkedListNode.createList(7, 8, 9);
    LinkedListNode<Integer> sum = addListBasedIntegers(l1, l2);
    System.out.println(sum);

     l1 = LinkedListNode.createList(3, 1, 4);
     l2 = LinkedListNode.createList(7, 8, 9);
      sum = addListBasedIntegersMSBFirst(l1, l2);
    System.out.println(sum);
  }

  public static LinkedListNode<Integer> addListBasedIntegersMSBFirst(
      LinkedListNode<Integer> l1, LinkedListNode<Integer> l2) {
    l1 = ReverseLInkedList.reverseList(l1);
    l2 = ReverseLInkedList.reverseList(l2);
    LinkedListNode<Integer> result = addListBasedIntegers(l1, l2);
    result = ReverseLInkedList.reverseList(result);
    return result;
  }

  public static LinkedListNode<Integer> addListBasedIntegers(
      LinkedListNode<Integer> l1, LinkedListNode<Integer> l2) {
    LinkedListNode<Integer> result = new LinkedListNode<>(0);
    LinkedListNode<Integer> resultTail = result;

    int carry = 0;
    while (l1 != null || l2 != null || carry > 0) {
      int sum = carry;
      if (l1 != null) {
        sum += l1.data;
        l1 = l1.next;
      }
      if (l2 != null) {
        sum += l2.data;
        l2 = l2.next;
      }
      LinkedListNode<Integer> newNode = new LinkedListNode<>(sum % 10);
      carry = sum / 10;
      resultTail.next = newNode;
      resultTail = newNode;
    }
    return result.next;
  }
}
