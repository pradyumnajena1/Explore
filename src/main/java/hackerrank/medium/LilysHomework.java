package hackerrank.medium;

import epp.array.ArrayUtils;

import java.util.*;
import java.util.stream.IntStream;

public class LilysHomework {
    public static void main(String[] args) {
        ////https://www.linkedin.com/in/avinash-agrawal-aa54b9b4/
        System.out.println(lilysHomework(new ArrayList<>(List.of(2, 5, 3, 1))));
        System.out.println(lilysHomework(new ArrayList<>(List.of(7,15,12,3))));
        System.out.println(lilysHomework(new ArrayList<>(List.of(3,4,2,5,1))));
    }

    public static int lilysHomework(List<Integer> arr) {
        // Write your code here

        int numSwapsForAscending = getNumSwaps(arr, true);
        int numSwapsForDescending = getNumSwaps(arr,  false);
        return Math.min(numSwapsForDescending,numSwapsForAscending);
    }

    private static int getNumSwaps(List<Integer> arr, boolean ascending) {
        if(ascending){
            Comparator<Integer> comparator = Comparator.comparingInt(arr::get);
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(comparator);
            IntStream.range(0,arr.size()).forEach(i->priorityQueue.offer(i));
            int count = 0;
            for(int i=0;i<arr.size()-1;i++){
                Integer minIndex = priorityQueue.poll();
                if(minIndex!=i){
                    count++;
                }
            }
            return count;
        }else{
            Comparator<Integer> comparator = Comparator.comparingInt(arr::get).reversed();
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(comparator);
            IntStream.range(0,arr.size()).forEach(i->priorityQueue.offer(i));
            int count = 0;
            for(int i=arr.size()-1;i>0;i--){
                Integer minIndex = priorityQueue.poll();
                if(minIndex!=i){
                    count++;
                }
            }
            return count;
        }

    }
    static void swap(List<Integer> arr,int i,int j){
        int temp = arr.get(i);
        arr.set(i,arr.get(j));
        arr.set(j,temp);
    }
    public static <T> void reverse(List<T> list) {
        int start = 0;
        int end = list.size() - 1;
        while (start < end) {
            T copy = list.get(start);
            list.set(start, list.get(end));
            list.set(end, copy);
            start++;
            end--;
        }
    }
}
