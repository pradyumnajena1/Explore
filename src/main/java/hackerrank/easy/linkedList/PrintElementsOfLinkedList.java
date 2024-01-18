package hackerrank.easy.linkedList;

public class PrintElementsOfLinkedList {

    private static class SinglyLinkedListNode {
        int data;
        SinglyLinkedListNode next;

        public SinglyLinkedListNode(int data) {
            this.data = data;
        }
    }

    static void printLinkedList(SinglyLinkedListNode head) {
        while (head != null) {
            System.out.println(head.data);
            head = head.next;
        }


    }

    static SinglyLinkedListNode insertNodeAtTail(SinglyLinkedListNode head, int data) {
        SinglyLinkedListNode newNode = new SinglyLinkedListNode(data);
        if (head == null) {
            return newNode;
        }
        SinglyLinkedListNode current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
        return head;

    }

    static SinglyLinkedListNode insertNodeAtHead(SinglyLinkedListNode llist, int data) {

        SinglyLinkedListNode newNode = new SinglyLinkedListNode(data);
        newNode.next = llist;
        return newNode;
    }

    static boolean compareLists(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
        while (head1 != null && head2 != null) {
            if (head1.data != head2.data) {
                return false;
            }
            head1 = head1.next;
            head2 = head2.next;
        }
        return head1 == null && head2 == null;

    }

    static SinglyLinkedListNode mergeLists(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }
        SinglyLinkedListNode rh = null;
        SinglyLinkedListNode rt = null;
        while (head1 != null && head2 != null) {
            SinglyLinkedListNode temp = null;
            if (head1.data <= head2.data) {
                temp = head1;
                head1 = head1.next;

            } else {
                temp = head2;
                head2 = head2.next;
            }
            temp.next = null;
            if (rh == null) {
                rh = rt = temp;
            } else {
                rt.next = temp;
                rt = temp;
            }
        }
        if (head1 != null) {

            rt.next = head1;
        }
        if (head2 != null) {
            rt.next = head2;
        }

        return rh;

    }
}
