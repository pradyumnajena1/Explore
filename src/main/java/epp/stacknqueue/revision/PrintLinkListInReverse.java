package epp.stacknqueue.revision;

import epp.list.LinkedListNode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class PrintLinkListInReverse {
  public static void main(String[] args) {
    LinkedListNode<Integer> list = LinkedListNode.createList(6, 7, 2, 3, 5, 3, 2);
    System.out.println(list);
    printLinkListInReverse(list);
    printLinkListInReverseUsingRecurse(list);
  }

  private static void printLinkListInReverseUsingRecurse(LinkedListNode<Integer> list) {
    if(list==null){
      return;
    }
    printLinkListInReverseUsingRecurse(list.next);
    System.out.println(list.data);
  }
  private static void printLinkListInReverse(LinkedListNode<Integer> list) {
    Deque<Integer> stack = new LinkedList<>();
    while (list != null) {
      stack.push(list.data);
      list = list.next;
    }
    while (!stack.isEmpty()) {
      System.out.println(stack.pop());
    }
  }

}
