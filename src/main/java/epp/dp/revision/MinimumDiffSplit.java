package epp.dp.revision;

import epp.Pair;
import epp.array.ArrayUtils;

import java.util.*;

public class MinimumDiffSplit {
    public static void main(String[] args) {
        int[] values ={90,80,30,20};// ArrayUtils.randomArray(10,5,20);
        ArrayUtils.printArray(values);
        Pair<Integer,Integer> minDiffSplit = getMinDiffSplit(values);
        System.out.println(minDiffSplit);
    }

    private static Pair<Integer, Integer> getMinDiffSplit(int[] values) {
        int totalSum = Arrays.stream(values).sum();
        boolean[] sumFeasible = new boolean[totalSum/2+1];
        sumFeasible[0] = true;

        for(int i=0;i<values.length;i++){
             for(int j=totalSum/2;j>=values[i];j--){
                 if(sumFeasible[j-values[i]]){
                     sumFeasible[j] = true;
                 }
             }
               for(int k=0;k<sumFeasible.length;k++){
                   if(sumFeasible[k]){
                       System.out.println(k);
                   }
               }
            System.out.println();
        }
        for(int i=totalSum/2;i>0;i--){
            if(sumFeasible[i]){
                return  new Pair<>(i,totalSum-i) ;
            }
        }
        return null;
    }


}
