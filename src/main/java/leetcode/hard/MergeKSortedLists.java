package leetcode.hard;

import epp.list.LinkedListNode;

import java.util.PriorityQueue;

public class MergeKSortedLists {
    public static void main(String[] args) {
        LinkedListNode<Integer>[] lists = new LinkedListNode[]{
                new LinkedListNode(1,new LinkedListNode(3,new LinkedListNode(5))),
                new LinkedListNode(2,new LinkedListNode(3,new LinkedListNode(7))),
                new LinkedListNode(1,new LinkedListNode(9,new LinkedListNode(15))),null
        };
        LinkedListNode<Integer> result = mergeSortedLists(lists);
        System.out.println(result);
    }

    private static LinkedListNode<Integer> mergeSortedLists(LinkedListNode<Integer>[] lists) {
        PriorityQueue<PriorityQueueNode> priorityQueue = new PriorityQueue<>();
        for(int i=0;i<lists.length;i++){
            if(lists[i]!=null){
                priorityQueue.offer(new PriorityQueueNode(lists[i].data,i));
                lists[i] = lists[i].next;
            }

        }
        LinkedListNode<Integer> resHead = null;
        LinkedListNode<Integer> resTail = null;
        while (!priorityQueue.isEmpty()){
            PriorityQueueNode poll = priorityQueue.poll();
            LinkedListNode<Integer> newNode = new LinkedListNode<>(poll.value);
            if(resHead==null){
                resHead = resTail = newNode;
            }else{
                resTail.next = newNode;
                resTail = newNode;
            }
            if(lists[poll.listIndex]!=null){

                priorityQueue.offer(new PriorityQueueNode(lists[poll.listIndex].data,poll.listIndex));
                lists[poll.listIndex]=lists[poll.listIndex].next;
            }
        }
        return resHead;
    }
    private static class PriorityQueueNode implements Comparable<PriorityQueueNode>{
        int value;
        int listIndex;

        public PriorityQueueNode(int value, int listIndex) {
            this.value = value;
            this.listIndex = listIndex;
        }

        @Override
        public int compareTo(PriorityQueueNode o) {
            return Integer.compare(value,o.value);
        }
    }
}
