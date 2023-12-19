package epp.list;

import java.util.Arrays;

public class SubListReverse {
    public static void main(String[] args) {
        LinkedListNode<Integer> list = LinkedListNode.createList(Arrays.asList(1,2,3,4,5,6,7));
        LinkedListNode<Integer> newList = subListReverse(list,1,7);
        System.out.println(newList);
    }

    private static LinkedListNode<Integer> subListReverse(LinkedListNode<Integer> list, int start, int finish) {
        LinkedListNode<Integer> prev = null;
        LinkedListNode<Integer> current = list;
        LinkedListNode<Integer> next = null;

        LinkedListNode<Integer> start_prev = null;
        LinkedListNode<Integer> startNode = null;

        int count =1;
        while(count<=finish){
            next = current.next;

            if(count==start){
                start_prev = prev;
                startNode = current;

                //break list
                if(start_prev!=null){
                    start_prev.next = null;
                }
                current.next = null;
            }
            if(count>start && count<finish){
                current.next = prev;
            }

            if(count==finish){
                current.next=prev;
                //join lists
                startNode.next = next;
                if(start_prev!=null){
                    start_prev.next = current;
                }
            }
            prev = current;
            current=next;
            count++;
        }


        return start_prev!=null? list:prev;
    }
}
