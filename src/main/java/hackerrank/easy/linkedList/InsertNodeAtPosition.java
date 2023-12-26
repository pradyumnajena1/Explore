package hackerrank.easy.linkedList;

import java.io.*;
import java.util.stream.IntStream;

public class InsertNodeAtPosition {

  static   class SinglyLinkedListNode {
        public int data;
        public SinglyLinkedListNode next;

        public SinglyLinkedListNode(int nodeData) {
            this.data = nodeData;
            this.next = null;
        }
    }

  static  class SinglyLinkedList {
        public SinglyLinkedListNode head;
        public SinglyLinkedListNode tail;

        public SinglyLinkedList() {
            this.head = null;
            this.tail = null;
        }

        public void insertNode(int nodeData) {
            SinglyLinkedListNode node = new SinglyLinkedListNode(nodeData);

            if (this.head == null) {
                this.head = node;
            } else {
                this.tail.next = node;
            }

            this.tail = node;
        }
    }

   static class SinglyLinkedListPrintHelper {
        public static void printList(SinglyLinkedListNode node, String sep, BufferedWriter bufferedWriter) throws IOException {
            while (node != null) {
                bufferedWriter.write(String.valueOf(node.data));

                node = node.next;

                if (node != null) {
                    bufferedWriter.write(sep);
                }
            }
        }
    }
  static   class Result {

        /*
         * Complete the 'insertNodeAtPosition' function below.
         *
         * The function is expected to return an INTEGER_SINGLY_LINKED_LIST.
         * The function accepts following parameters:
         *  1. INTEGER_SINGLY_LINKED_LIST llist
         *  2. INTEGER data
         *  3. INTEGER position
         */

        /*
         * For your reference:
         *
         * SinglyLinkedListNode {
         *     int data;
         *     SinglyLinkedListNode next;
         * }
         *
         */

        public static SinglyLinkedListNode insertNodeAtPosition(SinglyLinkedListNode llist, int data, int position) {
            // Write your code here
            if(llist==null){
                return new SinglyLinkedListNode(data);
            }
            SinglyLinkedListNode current = llist;
            SinglyLinkedListNode prev= null;
            while (current!=null && position>0){
                prev = current;
                current = current.next;
                position--;
            }
            SinglyLinkedListNode node = new SinglyLinkedListNode(data);
            if(prev==null){
                node.next = current;
                llist = node;


            }else{
                prev.next = node;
                node.next = current;

            }
            return llist;

        }

    }


    public static class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            SinglyLinkedList llist = new SinglyLinkedList();

            int llistCount = Integer.parseInt(bufferedReader.readLine().trim());

            IntStream.range(0, llistCount).forEach(i -> {
                try {
                    int llistItem = Integer.parseInt(bufferedReader.readLine().trim());

                    llist.insertNode(llistItem);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

            int data = Integer.parseInt(bufferedReader.readLine().trim());

            int position = Integer.parseInt(bufferedReader.readLine().trim());

            SinglyLinkedListNode llist_head = Result.insertNodeAtPosition(llist.head, data, position);

            SinglyLinkedListPrintHelper.printList(llist_head, " ", bufferedWriter);
            bufferedWriter.newLine();

            bufferedReader.close();
            bufferedWriter.close();
        }
    }

}
