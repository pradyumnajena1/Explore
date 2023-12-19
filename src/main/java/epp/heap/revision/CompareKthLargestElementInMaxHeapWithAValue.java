package epp.heap.revision;

import epp.array.ArrayUtils;
import epp.heap.HeapUtils;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

public class CompareKthLargestElementInMaxHeapWithAValue {
    public static void main(String[] args) {
        int[] values =  new int[]{9,7,8,7,7,8,8,7,7,7,7,8,8,8,8};
        Integer[] integers = Arrays.stream(values).boxed().toArray(Integer[]::new);
        HeapUtils.createMaxHeap(integers);
        ArrayUtils.printArray(integers);
       int result =  compareKthLargestElementInMaxHeapWithAValue(integers,5,7);
        System.out.println(result);
    }

    private static int compareKthLargestElementInMaxHeapWithAValue(Integer[] maxHeapArray, int k, int x) {
        AtomicInteger larger = new AtomicInteger(0);
        AtomicInteger equal = new AtomicInteger(0);
        preOrderTraverseMaxHeap(maxHeapArray,k,x,larger,equal,0);
        System.out.println("larger->"+ larger.get());
        System.out.println("equal->"+equal.get());
        return larger.get()>=k?1: larger.get()+equal.get() >=k?0:-1;
    }

    private static void preOrderTraverseMaxHeap(Integer[] maxHeapArray, int k, int x, AtomicInteger larger, AtomicInteger equal, int idx) {
        if(idx>=maxHeapArray.length ||larger.get()>=k || equal.get()>=k ){
            return;
        }
        if(maxHeapArray[idx]<x){
            return;
        } else if(maxHeapArray[idx]==x){
             equal.incrementAndGet();
        }else{
             larger.incrementAndGet();
        }
        if(2*idx+2 < maxHeapArray.length){
            if(maxHeapArray[2*idx+1]>maxHeapArray[2*idx+2]){
                preOrderTraverseMaxHeap(maxHeapArray,k,x,larger,equal,2*idx+1);
                preOrderTraverseMaxHeap(maxHeapArray,k,x,larger,equal,2*idx+2);
            }else{
                preOrderTraverseMaxHeap(maxHeapArray,k,x,larger,equal,2*idx+2);
                preOrderTraverseMaxHeap(maxHeapArray,k,x,larger,equal,2*idx+1);
            }
        }else{
            preOrderTraverseMaxHeap(maxHeapArray,k,x,larger,equal,2*idx+1);
            preOrderTraverseMaxHeap(maxHeapArray,k,x,larger,equal,2*idx+2);
        }

    }
}
