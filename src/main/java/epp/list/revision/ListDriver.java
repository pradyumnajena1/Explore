package epp.list.revision;

import epp.ListUtils;
import epp.list.LinkedListNode;

public class ListDriver {

  public static void main(String[] args) {
    LinkedListNode<Integer> list = LinkedListNode.createList(1, 2, 2, 3, 3, 3, 3, 4, 5, 5, 5);
    System.out.println("Original List: " + list);

    // Reverse the list
    list = removeDuplicates(list);
    System.out.println("  List after removing dups: " + list);

   list = LinkedListNode.createList(1, 2, 2, 3, 3, 3, 3, 4, 5, 5, 5);
    System.out.println("Original List: " + list);

    // Reverse the list
    list = removeDuplicatesMoreThanK(list,3);
    System.out.println("  List after removing dups: " + list);
    LinkedListNode<Integer> sum = addNumbers(LinkedListNode.createList(2, 3, 4, 5), LinkedListNode.createList(5, 7, 2, 9));
    System.out.println("Sum of two lists: " + sum);

    sum = addNumbers2(LinkedListNode.createList(2, 3, 4, 5), LinkedListNode.createList(5, 7, 2, 9));
    System.out.println("Sum of two lists: " + sum);
  }

  private static LinkedListNode<Integer> removeDuplicates(LinkedListNode<Integer> list) {
    LinkedListNode<Integer> iterator = list;
    while (iterator != null) {
      LinkedListNode<Integer> nextDistinct = iterator.next;
      while (nextDistinct != null && nextDistinct.data == iterator.data) {
        nextDistinct = nextDistinct.next;
      }
      iterator.next = nextDistinct;
      iterator = nextDistinct;
    }
    return list;
  }

  private static LinkedListNode<Integer> removeDuplicatesMoreThanK(LinkedListNode<Integer> list,int k) {
    LinkedListNode<Integer> dummy  =new LinkedListNode<Integer>(0,list);
    LinkedListNode<Integer> iterator = list;
    LinkedListNode<Integer> prev = dummy;
    while (iterator != null) {
      int count = 1;
      LinkedListNode<Integer> nextDistinct = iterator.next;
      LinkedListNode<Integer> currentPrev = iterator ;
      while (nextDistinct != null && nextDistinct.data == iterator.data) {
        currentPrev = nextDistinct;
        nextDistinct = nextDistinct.next;
        count++;
      }
      if(count >k) {
        prev.next = nextDistinct;
        prev = nextDistinct;
        iterator = nextDistinct;
      }else{
        prev = currentPrev;
        iterator = nextDistinct;
      }
    }
    return dummy.next;
  }

  /**
   * lsb at head
   * @param l1
   * @param l2
   * @return
   */
  private static LinkedListNode<Integer> addNumbers(LinkedListNode<Integer> l1,LinkedListNode<Integer> l2){
    LinkedListNode<Integer> dummy = new LinkedListNode<Integer>(0);
    LinkedListNode<Integer> curr = dummy;
    int carry = 0;
    while(l1!=null || l2!=null || carry!=0){
      int sum = carry;
      if(l1!=null){
        sum += l1.data;
        l1 = l1.next;
      }
      if(l2!=null){
        sum += l2.data;
        l2 = l2.next;
      }
      curr.next = new LinkedListNode<Integer>(sum%10);
      carry = sum/10;
      curr = curr.next;
    }
    return dummy.next;
  }

  /**
   * msb at head
   * @param l1
   * @param l2
   * @return
   */
  private static LinkedListNode<Integer> addNumbers2(
      LinkedListNode<Integer> l1, LinkedListNode<Integer> l2) {
    LinkedListNode<Integer> head1 = LinkedListUtils.reverse(l1);
    LinkedListNode<Integer> head2 = LinkedListUtils.reverse(l2);
    LinkedListNode<Integer> head = addNumbers(head1, head2);
    return LinkedListUtils.reverse(head);
  }
  }
