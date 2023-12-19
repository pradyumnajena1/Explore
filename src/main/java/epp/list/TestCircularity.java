package epp.list;

import java.util.Arrays;

public class TestCircularity {
    public static void main(String[] args) {
        LinkedListNode<Integer> list = LinkedListNode.createList(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        System.out.println(list);
        LinkedListNode<Integer> node_10 = LinkedListNode.getNode(list, 10);
        LinkedListNode<Integer> node_5 = LinkedListNode.getNode(list, 5);
        System.out.println(node_10.data);
        System.out.println(node_5.data);
        node_10.next = node_5;
        LinkedListNode<Integer> circle_start = getStartOfCircle(list);
        System.out.println(circle_start.data);
    }

    private static LinkedListNode<Integer> getStartOfCircle(LinkedListNode<Integer> list) {
        LinkedListNode<Integer> fast_ptr = list;
        LinkedListNode<Integer> slow_ptr = list;

        while (fast_ptr != null) {
            fast_ptr = fast_ptr.next;
            slow_ptr = slow_ptr.next;

            if (fast_ptr.next != null) {
                fast_ptr = fast_ptr.next;
            }
            if (fast_ptr == slow_ptr) {
                break;
            }
        }
        if (fast_ptr == null) {
            return null;
        }

        slow_ptr = list;
        while (fast_ptr != slow_ptr) {
            fast_ptr = fast_ptr.next;
            slow_ptr = slow_ptr.next;
        }

        return fast_ptr;
    }

}
