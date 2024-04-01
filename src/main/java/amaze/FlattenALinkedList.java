package amaze;

public class FlattenALinkedList {

    public static void main(String[] args) {
        LinkedListNode head = new LinkedListNode(5,new LinkedListNode(10,new LinkedListNode(19,
                new LinkedListNode(28))));

        head.bottom = new LinkedListNode(7,null, new LinkedListNode(8,null, new LinkedListNode(30)));
        head.next.bottom = new LinkedListNode(20);
        head.next.next.bottom = new LinkedListNode(22,null, new LinkedListNode(50));
        head.next.next.next.bottom = new LinkedListNode(35, null, new LinkedListNode(40,null, new LinkedListNode(45)));
       LinkedListNode flattenedList = flatten(head);
       printList(flattenedList);
    }

    private static void printList(LinkedListNode head) {
        while (head!=null){
            System.out.println(head.data);
            head = head.bottom;
        }
    }

    private static LinkedListNode flatten(LinkedListNode head) {
        if(head==null || head.next==null){
            return head;
        }
        head.next = flatten(head.next);
        head = merge(head,head.next);
        return head;
    }

    private static LinkedListNode merge(LinkedListNode head1, LinkedListNode head2) {
        if(head1==null){
            return head2;
        }
        if(head2==null){
            return head1;
        }
        LinkedListNode result;
        if(head1.data< head2.data){
            result = head1;
            result.bottom = merge(head1.bottom,head2);
        }else{
            result =  head2 ;
            result.bottom = merge(head1,head2.bottom);
        }

        return result;
    }


    private static class LinkedListNode{
         int data;
         LinkedListNode bottom;
         LinkedListNode next;

         public LinkedListNode(int data) {
             this.data = data;
         }

         public LinkedListNode(int data, LinkedListNode next) {
             this.data = data;
             this.next = next;
         }

        public LinkedListNode(int data,  LinkedListNode next,LinkedListNode bottom) {
            this.data = data;
            this.bottom = bottom;
            this.next = next;
        }
    }
}
