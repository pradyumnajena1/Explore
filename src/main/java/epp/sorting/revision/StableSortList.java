package epp.sorting.revision;

import epp.list.LinkedListNode;

public class StableSortList {
  public static void main(String[] args) {

    LinkedListNode<Integer> temp = LinkedListNode.createList(1, 2 ,3);
    LinkedListNode<Integer>[] linkedListNodes = LinkedListNode.splitInHalf(temp);
    System.out.println(linkedListNodes[0]);
    System.out.println(linkedListNodes[1]);


    LinkedListNode<Integer> list = LinkedListNode.createList(1, 2, 3, 4, 3, 2, 1);
    list = stableSortList(list);
    System.out.println(list);
  }

  private static LinkedListNode<Integer> stableSortList(LinkedListNode<Integer> list) {
    if (list == null || list.next == null) {
      return list;
    }
    LinkedListNode<Integer>[] parts = LinkedListNode.splitInHalf(list);
    return LinkedListNode.mergeLists(stableSortList(parts[0]), stableSortList(parts[1]));
  }
}
