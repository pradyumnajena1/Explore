package epp.heap.revision;

import epp.array.ArrayUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class TicketDistribution {
    public static void main(String[] args) {
        int[] linesOfCode =new int[]{300,400,500,200};
        ArrayUtils.printArray(linesOfCode);
        int[] tickets = distributeTickets(linesOfCode);
        ArrayUtils.printArray(tickets);

        tickets = distributeTickets2(linesOfCode);
        ArrayUtils.printArray(tickets);
    }

    private static int[] distributeTickets(int[] values) {
        int[] tickets = new int[values.length];
        Arrays.fill(tickets, 1);
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Comparator.comparingInt(x -> values[x]));
        for(int i=0;i< values.length;i++){
            priorityQueue.offer(i);
        }
        while (!priorityQueue.isEmpty()){
            Integer polled = priorityQueue.poll();
            if(polled>0 && values[polled]>values[polled-1]){
                tickets[polled] = tickets[polled-1]+1;
            }
            if(polled<values.length-1 && values[polled]>values[polled+1]){
                tickets[polled] = Math.max(tickets[polled],tickets[polled+1]+1);
            }
        }
        return tickets;
    }
    private static int[] distributeTickets2(int[] values) {
        int[] tickets = new int[values.length];
        Arrays.fill(tickets, 1);
        for(int i=1;i< values.length;i++){
            if(values[i]>values[i-1]){
                tickets[i] = tickets[i-1]+1;
            }
        }
        for(int i=values.length-2;i>=0;i--){
            if(values[i]>values[i+1]){
                tickets[i] = Math.max( tickets[i],tickets[i+1]+1);
            }
        }
        return tickets;
    }
}
