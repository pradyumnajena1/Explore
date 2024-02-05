package hackerrank.easy.linkedList;

public class HasCycle {

    static class SinglyLinkedListNode {
        int data;
        SinglyLinkedListNode next;
    }

    static boolean hasCycle(SinglyLinkedListNode head) {

        SinglyLinkedListNode fp = head;
        SinglyLinkedListNode sp = head;
        while (fp != null) {

            fp = fp.next;
            if (fp != null) {
                fp = fp.next;
                sp = sp.next;
            }
            if (fp == sp) {
                return true;
            }
        }
        return false;
    }
}
