package epp.list;

import epp.list.revision.TestForCircularity;
import java.util.Arrays;
import java.util.List;

public class LinkedListNode<T> {
  public T data;
  public LinkedListNode<T> next;

  public LinkedListNode(T data, LinkedListNode<T> next) {
    this.data = data;
    this.next = next;
  }

  public LinkedListNode(T data) {
    this.data = data;
  }

  public static <T> int getLength(LinkedListNode<T> list1) {
    int size = 0;
    LinkedListNode<T> current = list1;
    while (current != null) {
      current = current.next;
      size++;
    }
    return size;
  }

  public static <T> LinkedListNode<T>[] splitInHalf(LinkedListNode<T> list) {

    LinkedListNode<T> fastPtr = list;
    LinkedListNode<T> slowPtr = list;
    LinkedListNode<T> prev = null;

    while (fastPtr!= null && fastPtr.next!= null) {
      prev = slowPtr;
      slowPtr = slowPtr.next;
      fastPtr = fastPtr.next.next;
    }
    prev.next = null;
    return new LinkedListNode[] {list, slowPtr};
  }

  public static <T> LinkedListNode<T> getNode(LinkedListNode<T> list, T data) {
    LinkedListNode<T> current = list;
    while (current != null && !current.data.equals(data)) {
      current = current.next;
    }
    return current;
  }

  public static LinkedListNode<Integer> mergeLists(
      LinkedListNode<Integer> first, LinkedListNode<Integer> second) {
    // Creates a placeholder for the result.
    LinkedListNode<Integer> dummyHead = new LinkedListNode<>(0, null);
    LinkedListNode<Integer> current = dummyHead;
    LinkedListNode<Integer> p1 = first, p2 = second;
    while (p1 != null && p2 != null) {
      if (p1.data <= p2.data) {
        current.next = p1;
        p1 = p1.next;
      } else {
        current.next = p2;
        p2 = p2.next;
      }
      current = current.next;
    }
    // Appends the remaining nodes of p1 or p2.
    current.next = p1 != null ? p1 : p2;
    return dummyHead.next;
  }

  private static <T> String toStringNonCircle(LinkedListNode<T> head) {
    StringBuilder sb = new StringBuilder();
    sb.append("LinkedList[");

    StringBuilder nodesString = new StringBuilder();
    LinkedListNode<T> current = head;
    int count = 0;
    while (current != null) {

      nodesString.append(current.data);
      nodesString.append("->");
      current = current.next;
      count++;
    }
    nodesString.append("NULL");

    sb.append("size = " + count + " ");
    sb.append(nodesString);
    sb.append("]");
    return sb.toString();
  }

  public static <T> LinkedListNode<T> createList(T... values) {
    return createList(Arrays.asList(values));
  }

  public static <T> LinkedListNode<T> createList(List<T> values) {
    LinkedListNode<T> head = null;
    LinkedListNode<T> current = null;
    LinkedListNode<T> prev = null;

    for (int i = 0; i < values.size(); i++) {
      current = new LinkedListNode<T>(values.get(i));
      if (head == null) {
        head = prev = current;
      } else {
        prev.next = current;
        prev = current;
      }
    }
    return head;
  }

  public String toString() {
    LinkedListNode<T> startOfCircle = TestForCircularity.findStartOfCircle(this);
    if (startOfCircle == null) {
      return toStringNonCircle(this);
    } else {
      return toStringWithCircle(this, startOfCircle);
    }
  }

  private String toStringWithCircle(LinkedListNode<T> head, LinkedListNode<T> startOfCircle) {
    StringBuilder sb = new StringBuilder();
    sb.append("LinkedList[");

    StringBuilder nodesString = new StringBuilder();
    LinkedListNode<T> current = head;
    int count = 0;
    while (current != startOfCircle) {

      nodesString.append(current.data);
      nodesString.append("->");
      current = current.next;
      count++;
    }
    nodesString.append(current.data);
    nodesString.append("(start of circle)->");
    current = current.next;

    while (current != startOfCircle) {

      nodesString.append(current.data);
      nodesString.append("->");
      current = current.next;
      count++;
    }
    nodesString.append("(end of circle)");

    sb.append("size = " + count + " ");
    sb.append(nodesString);
    sb.append("]");
    return sb.toString();
  }
}
