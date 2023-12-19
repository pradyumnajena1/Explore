package epp.list.revision;

import epp.list.LinkedListNode;

public class RightShiftList {
    public static void main(String[] args) {
        LinkedListNode<Integer> list = LinkedListNode.createList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        list = rightShiftList(list, 11);
        System.out.println(list);

    }

    private static LinkedListNode<Integer> rightShiftList(LinkedListNode<Integer> list, int n) {
        int length = LinkedListNode.getLength(list);
        n = n % length;
        if (n == 0) {
            return list;
        }
        LinkedListNode<Integer> front = list;
        LinkedListNode<Integer> back = list;
        LinkedListNode<Integer> prev = null;
        for (int i = 1; i < n && front != null; i++) {
            front = front.next;
        }

        while (front.next != null) {
            prev = back;
            back = back.next;
            front = front.next;
        }

        prev.next = null;
        front.next = list;

        return back;

    }
}
