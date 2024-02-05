package hackerrank.easy.linkedList;

public class GetNodeValue {

   static class SinglyLinkedListNode {
          int data;
          SinglyLinkedListNode next;
       }

    public static int getNode(SinglyLinkedListNode llist, int positionFromTail) {
        // Write your code here

        SinglyLinkedListNode fp = llist;
        SinglyLinkedListNode bp = llist;
        while (positionFromTail>0){
            fp = fp.next;
            positionFromTail--;
        }
        while (fp.next!=null){
            fp   = fp.next;
            bp = bp.next;
        }
        return bp.data;
    }
}
