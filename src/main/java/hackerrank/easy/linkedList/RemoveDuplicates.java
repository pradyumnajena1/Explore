package hackerrank.easy.linkedList;

public class RemoveDuplicates {
    static class SinglyLinkedListNode {
        int data;
        SinglyLinkedListNode next;
    }

    public static SinglyLinkedListNode removeDuplicates(SinglyLinkedListNode llist) {
        // Write your code here
        SinglyLinkedListNode prev = null;
        SinglyLinkedListNode cur = llist;
        SinglyLinkedListNode next = null;
        while (cur != null) {
            next = cur.next;
            if (prev != null && prev.data == cur.data) {

                prev.next = cur.next;
                cur.next = null;
            } else {

                prev = cur;
            }
            cur = next;
        }
        return llist;

    }
}
