package hackerrank.easy;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class InsertingNodeIntoSortedDoublyLinkedList {
   static class DoublyLinkedListNode {
          int data;
          DoublyLinkedListNode next;
          DoublyLinkedListNode prev;

       public DoublyLinkedListNode(int data) {
           this.data = data;
       }

       public DoublyLinkedListNode(int data, DoublyLinkedListNode next) {
           this.data = data;
           this.next = next;
           next.prev = this;
       }
   }
  static   class DoublyLinkedListPrintHelper {
        public static void printList(DoublyLinkedListNode node, String sep, BufferedWriter bufferedWriter) throws IOException {
            while (node != null) {
                bufferedWriter.write(String.valueOf(node.data));

                node = node.next;

                if (node != null) {
                    bufferedWriter.write(sep);
                }
            }
            bufferedWriter.flush();
        }
    }

    public static DoublyLinkedListNode sortedInsert(DoublyLinkedListNode llist, int data) {
        // Write your code here
        DoublyLinkedListNode newNode = new DoublyLinkedListNode(data);
        if(llist==null){
            return newNode;
        }
        DoublyLinkedListNode prev = null;
        DoublyLinkedListNode current = llist;
        while (current!=null && current.data<=data){
            prev = current;
            current = current.next;
        }
        if(prev!=null){
            prev.next = newNode;
            newNode.prev=prev;
            if(current!=null){
                newNode.next=current;
                current.prev=newNode;
            }
            return llist;
        }else{
            newNode.next=current;
            current.prev = newNode;
            return newNode;
        }

    }

    public static void main(String[] args) throws IOException {
        DoublyLinkedListNode llist = new DoublyLinkedListNode(1,new DoublyLinkedListNode(2,new DoublyLinkedListNode(4)));

        DoublyLinkedListPrintHelper.printList(llist,", ",new BufferedWriter(new OutputStreamWriter(System.out)));
        llist=  sortedInsert(llist,3);
       DoublyLinkedListPrintHelper.printList(llist,", ",new BufferedWriter(new OutputStreamWriter(System.out)));
    }
}
