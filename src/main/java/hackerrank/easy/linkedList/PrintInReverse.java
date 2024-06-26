package hackerrank.easy.linkedList;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.IntStream;

public class PrintInReverse {

    static class SinglyLinkedListNode {
        public int data;
        public SinglyLinkedListNode next;

        public SinglyLinkedListNode(int nodeData) {
            this.data = nodeData;
            this.next = null;
        }
    }

  static   class SinglyLinkedList {
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
        public static void printList(SinglyLinkedListNode node, String sep) {
            while (node != null) {
                System.out.print(node.data);

                node = node.next;

                if (node != null) {
                    System.out.print(sep);
                }
            }
        }
    }

  static   class Result {

        /*
         * Complete the 'reversePrint' function below.
         *
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

        public static void reversePrint(SinglyLinkedListNode llist) {
            // Write your code here
            if(llist==null){
                return;
            }
            reversePrint(llist.next);
            System.out.println(llist.data);

        }

    }

   static public class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));

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

                    Result.reversePrint(llist.head);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

            bufferedReader.close();
        }
    }
}
