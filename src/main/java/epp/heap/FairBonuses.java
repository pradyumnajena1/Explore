package epp.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class FairBonuses {
    public static void main(String[] args) {
        int[] values = new int[]{300, 400, 500, 200};
        int[] tickets = getTickets(values);
        System.out.println(Arrays.toString(tickets));
        int sum = Arrays.stream(tickets).sum();
        System.out.println(sum);

    }

    private static int[] getTickets(int[] values) {
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

}
