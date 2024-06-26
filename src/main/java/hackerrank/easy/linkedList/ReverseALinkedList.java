package hackerrank.easy.linkedList;

import java.io.*;
import java.util.stream.IntStream;

public class ReverseALinkedList {

  static   class SinglyLinkedListNode {
        public int data;
        public SinglyLinkedListNode next;

        public SinglyLinkedListNode(int nodeData) {
            this.data = nodeData;
            this.next = null;
        }
    }

   static class SinglyLinkedList {
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

  static   class SinglyLinkedListPrintHelper {
        public static void printList(SinglyLinkedListNode node, String sep, BufferedWriter bufferedWriter) throws IOException, IOException {
            while (node != null) {
                bufferedWriter.write(String.valueOf(node.data));

                node = node.next;

                if (node != null) {
                    bufferedWriter.write(sep);
                }
            }
        }
    }

 static    class Result {

        /*
         * Complete the 'reverse' function below.
         *
         * The function is expected to return an INTEGER_SINGLY_LINKED_LIST.
         * The function accepts INTEGER_SINGLY_LINKED_LIST llist as parameter.
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

        public static SinglyLinkedListNode reverse(SinglyLinkedListNode llist) {
            // Write your code here
            SinglyLinkedListNode current = llist;
            SinglyLinkedListNode prev = null;
            while (current!=null){

                SinglyLinkedListNode next = current.next;
                current.next = prev;

                prev = current;
                current = next;
            }
            return prev;

        }

    }

  static   public class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

            int tests = Integer.parseInt(bufferedReader.readLine().trim());

            IntStream.range(0, tests).forEach(testsItr -> {
                try {
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

                    SinglyLinkedListNode llist1 = Result.reverse(llist.head);

                    SinglyLinkedListPrintHelper.printList(llist1, " ", bufferedWriter);
                    bufferedWriter.newLine();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

            bufferedReader.close();
            bufferedWriter.close();
        }
    }

}
