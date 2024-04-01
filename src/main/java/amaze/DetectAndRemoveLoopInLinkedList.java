package amaze;

public class DetectAndRemoveLoopInLinkedList {

    public static void main(String[] args) {
        LinkedListNode head = new LinkedListNode(1);
        head.next = new LinkedListNode(2);
        head.next.next = new LinkedListNode(3);
        head.next.next.next = new LinkedListNode(4);
        head.next.next.next.next = new LinkedListNode(5);
        head.next.next.next.next.next = new LinkedListNode(6);

        head.next.next.next.next.next.next = head.next.next;

        boolean success = detectAndRemoveCycle(head);
        System.out.println(LinkedListNode.getItems(head));

    }

    private static boolean detectAndRemoveCycle(LinkedListNode head) {
        if(head==null){
            return false;
        }
        LinkedListNode fast = head;
        LinkedListNode slow = head;
        do {
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
                slow = slow.next;
            }
        } while (fast != null && fast != slow);

        if (fast == null) {
            return false;
        }
        fast = head;
        while (fast.next != slow.next) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = null;
        return true;
    }
}
