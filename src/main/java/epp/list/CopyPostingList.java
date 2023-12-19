package epp.list;

public class CopyPostingList {

    public static void main(String[] args) {
        PostingListNode n1 = getPostingList();
        System.out.println(n1);
        PostingListNode clone = clonePostingList(n1);
        System.out.println(clone);
    }

    private static PostingListNode getPostingList() {
        PostingListNode n1 = new PostingListNode(1);
        PostingListNode n2 = new PostingListNode(2);
        PostingListNode n3 = new PostingListNode(3);
        PostingListNode n4 = new PostingListNode(4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = null;

        n1.jump = n3;
        n2.jump = n4;
        n3.jump = n2;
        n4.jump = n4;
        return n1;
    }

    private static PostingListNode clonePostingList(PostingListNode head) {
        PostingListNode current = head;
        PostingListNode prev = null;
        PostingListNode next = null;
        while (current != null) {
            next = current.next;

            PostingListNode newNode = new PostingListNode(current.data);
            newNode.next = current.next;
            newNode.jump = current.jump;
            current.next = newNode;


            prev = current;
            current = next;
        }

        System.out.println(head);
        current = head;
        prev = null;
        next = null;
        while (current != null) {
            next = current.next.next;

            if (prev != null) {
                prev.next.jump = prev.jump.next;
            }

            prev = current;
            current = next;

        }
        System.out.println(head);

        current = head;
        prev = null;
        next = null;
        PostingListNode newHead = null;
        while (current != null) {
            next = current.next.next;


            PostingListNode temp = current.next;
            current.next = temp.next;
            temp.next = current.next;
            if (newHead == null) {
                newHead = temp;
            }


            prev = current;
            current = next;

        }

        return newHead;
    }
}
