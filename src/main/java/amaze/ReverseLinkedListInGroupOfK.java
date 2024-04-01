package amaze;

public class ReverseLinkedListInGroupOfK {

    public static void main(String[] args) {
        LinkedListNode head = new LinkedListNode(1, new LinkedListNode(2, new LinkedListNode(3, new LinkedListNode(4,
                new LinkedListNode(5, new LinkedListNode(6, new LinkedListNode(7, new LinkedListNode(8))))))));
        printList(head);
        head = reverseLinkedList(head, 3);
        printList(head);

        head = new LinkedListNode(1, new LinkedListNode(2, new LinkedListNode(3, new LinkedListNode(4,
                new LinkedListNode(5, new LinkedListNode(6, new LinkedListNode(7, new LinkedListNode(8))))))));
        printList(head);
        head = reverseLinkedList2(head, 3);
        printList(head);

    }

    private static void printList(LinkedListNode head) {
        while (head != null) {
            System.out.println(head.data);
            head = head.next;
        }
    }

    public static LinkedListNode reverseLinkedList2(LinkedListNode head, int k) {
        if(head==null){
            return null;
        }
        LinkedListNode current = head;
        LinkedListNode prev = null;
        LinkedListNode next = null;
        int count=0;
        while (current!=null && count<k ){
            next = current.next;

            current.next = prev;

            prev = current;
            current = next;
            count++;
        }
        if(next!=null){
            head.next = reverseLinkedList2(next,k);
        }
        return prev;
    }

    public static LinkedListNode reverseLinkedList(LinkedListNode head, int k) {
        LinkedListNode resultHead = null;
        LinkedListNode resultTail = null;

        LinkedListNode partHead = null;
        LinkedListNode partTail = null;

        LinkedListNode current = head;
        LinkedListNode prev = null;
        int count = 0;
        while (current != null) {
            LinkedListNode next = current.next;
            prev = current;
            current = next;

            count++;

            if (partHead == null) {
                partHead = partTail = prev;
                partTail.next = null;
            } else {
                prev.next = partHead;
                partHead = prev;
            }

            if (count % k == 0) {
                if (resultHead == null) {
                    resultHead = partHead;
                    resultTail = partTail;
                } else {
                    resultTail.next = partHead;
                    resultTail = partTail;
                    resultTail.next = null;
                }
                partHead = partTail = null;
            }
        }
        if (partHead != null) {
            if (resultHead == null) {
                resultHead = partHead;
                resultTail = partTail;
            } else {
                resultTail.next = partHead;
                resultTail = partTail;
                resultTail.next = null;
            }
            partHead = partTail = null;
        }
        return resultHead;
    }

}
