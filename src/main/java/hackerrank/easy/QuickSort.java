package hackerrank.easy;

import epp.array.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

public class QuickSort {
    public static void main(String[] args) {
        System.out.println(quickSort(new ArrayList<>(List.of(4, 5, 3, 7, 2))));
    }

    public static List<Integer> quickSort(List<Integer> arr) {
        // Write your code here
        int sp=0,ep = 0;
        int bp = arr.size()-1;
        int pivot =  arr.get(0);
        while (ep<=bp){
            if(arr.get(ep)<pivot){
                 swap(arr,sp,ep);
                sp++;
                ep++;

            }else if(arr.get(ep)==pivot){
                ep++;
            }else{
                 swap(arr,ep,bp);
                bp--;
            }
        }
        return arr;
    }
    private static void swap(List<Integer> arr,int i,int j){
        int t = arr.get(i);
        arr.set(i,arr.get(j));
        arr.set(j,t);
    }
}
