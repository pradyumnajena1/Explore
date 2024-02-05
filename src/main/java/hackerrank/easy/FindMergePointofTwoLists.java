package hackerrank.easy;

public class FindMergePointofTwoLists {
   static class SinglyLinkedListNode {
       public SinglyLinkedListNode(int data) {
           this.data = data;
       }

       public SinglyLinkedListNode(int data, SinglyLinkedListNode next) {
           this.data = data;
           this.next = next;
       }

       int data;
           SinglyLinkedListNode next;
      }
    static int findMergeNode(SinglyLinkedListNode head1, SinglyLinkedListNode head2) {
       int length1 = getLength(head1);
       int length2 = getLength(head2);

       if(length1>length2){
           head1 = advance(head1,length1-length2);
       } else if (length2>length1) {
           head2 = advance(head2,length2-length1);
       }
       while (head1!=head2){
           head1=head1.next;
           head2=head2.next;
       }
      return head1.data;

    }

    private static int getLength(SinglyLinkedListNode head) {
       int count = 0;
       while (head!=null){
           head=head.next;
           count++;

       }
        return count;
    }

    private static SinglyLinkedListNode advance(SinglyLinkedListNode head, int n) {
       while (head!=null && n>0){
           head=head.next;
           n--;
       }
        return head;
    }

    public static void main(String[] args) {
        SinglyLinkedListNode common = new SinglyLinkedListNode(2,new SinglyLinkedListNode(3));
        SinglyLinkedListNode head1 =new SinglyLinkedListNode(0, new SinglyLinkedListNode(1,common));
        SinglyLinkedListNode head2 = new SinglyLinkedListNode(1,common);
        int data = findMergeNode(head1, head2);
        System.out.println(data);
    }
}
