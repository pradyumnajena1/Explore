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
            int count=0;
            for(int i=0;i<arr.size();i++){
                int minIndex =  findMinIndex(arr,i,arr.size()-1);
                if(minIndex!=i){
                    swap(arr,minIndex,i);
                    count++;
                }
            }

            return count;
        }else{
            int count=0;
            for(int i=0;i<arr.size();i++){
                int maxIndex =  findMaxIndex(arr,i,arr.size()-1);
                if(maxIndex!=i){
                    swap(arr,maxIndex,i);
                    count++;
                }
            }

            return count;
        }

    }

    private static int findMinIndex(List<Integer> arr, int left, int right) {
        int minIndex = left;
        for(int i=left+1;i<=right;i++){
            if(arr.get(i)<arr.get(minIndex)){
                minIndex = i;
            }
        }
        return minIndex;
    }
    private static int findMaxIndex(List<Integer> arr, int left, int right) {
        int maxIndex = left;
        for(int i=left+1;i<=right;i++){
            if(arr.get(i)>arr.get(maxIndex)){
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    static void swap(List<Integer> arr,int i,int j){
        int temp = arr.get(i);
        arr.set(i,arr.get(j));
        arr.set(j,temp);
    }

}
