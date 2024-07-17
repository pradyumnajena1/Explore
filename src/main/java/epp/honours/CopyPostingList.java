package epp.honours;

import epp.list.PostingListNode;

public class CopyPostingList {
  public static void main(String[] args) {
    PostingListNode node1 = new PostingListNode(1);
    PostingListNode node2 = new PostingListNode(2);
    PostingListNode node3 = new PostingListNode(3);
    PostingListNode node4 = new PostingListNode(4);
    PostingListNode node5 = new PostingListNode(5);
    node1.next = node2;
    node2.next = node3;
    node3.next = node4;
    node4.next = node5;

    node1.jump = node4;
    node4.jump = node2;
    node3.jump = node5;
    node5.jump = node1;

    System.out.println(node1);

    PostingListNode copy = copyPostingList(node1);
    System.out.println(copy);
  }

  private static PostingListNode copyPostingList(PostingListNode originalList) {
    if (originalList == null) return null;
    PostingListNode originalIterator = originalList;
    //  create clone without jump and set as next node
    while (originalIterator != null) {
      PostingListNode copyNode = new PostingListNode(originalIterator.data, originalIterator.next);
      originalIterator.next = copyNode;
      originalIterator = copyNode.next;
    }
    System.out.println(originalList);
    // set jump field
    originalIterator = originalList;
    while (originalIterator != null) {
      if (originalIterator.jump != null) {

        originalIterator.next.jump = originalIterator.jump.next;
      }
      originalIterator = originalIterator.next.next;
    }
    System.out.println(originalList);

    originalIterator = originalList;
    PostingListNode copyHead = originalList.next;
    // separate both the list
    while (originalIterator.next != null) {
      PostingListNode temp = originalIterator.next;
      originalIterator.next = temp.next;
      originalIterator = temp;
    }
    System.out.println(originalList);

    return copyHead;
  }
}
