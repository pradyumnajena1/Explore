package guidetocompetitiveprogramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class FindOccurencesUsingSuffixArray {
    public static void main(String[] args) {
        String s  = "ABAACBAB";
        System.out.println(findOccurencess(s,"BA"));
        System.out.println(findOccurencess(s,"BAB"));
        System.out.println(findOccurencess(s,"BAc"));

    }

    private static List<Integer> findOccurencess(String s, String pattern) {
        int[] suffixArray = SuffixArray.buildSuffixArray(s);
        int low = 0;
        int high = suffixArray.length-1;
         for(int i=0;i<pattern.length();i++){
             low = findFirstIndex(suffixArray,low,high,s,pattern ,i);
             System.out.println( "lowIndex "+ low);
              if(low==-1){
                  return new ArrayList<>();
              }
             high = findLastIndex(suffixArray,low,high,s,pattern ,i);
             System.out.println("highIndex"+ high);
             if(high==-1){
                 return new ArrayList<>();
             }
         }
        return Arrays.stream(suffixArray,low ,high +1).boxed().collect(Collectors.toList());
    }

    private static int findLastIndex(int[] suffixArray,  int low, int high, String s, String pattern, int index) {
        Integer result=null ;
        while (low <=high ){
            int mid = (low +high )/2;
            char sc =suffixArray[mid] + index<s.length()?  s.charAt(suffixArray[mid] + index):0;
            char pc = pattern.charAt(index);
            if(sc == pc){
                result = mid;
                low=(  mid+1);
            } else if (sc < pc) {
                low=(mid+1);
            }else{
                high=( mid-1);
            }
        }
        return result!=null?result:-1;
    }

    private static int findFirstIndex(int[] suffixArray, int low, int high, String s, String pattern, int index) {
        Integer result=null ;
        while (low <=high ){
            int mid = (low +high )/2;
            char sc =suffixArray[mid] + index<s.length()?  s.charAt(suffixArray[mid] + index):0;
            char pc = pattern.charAt(index);
            if(sc == pc){
                result = mid;
                high=( mid-1);
            } else if (sc < pc) {
                low=(mid+1);
            }else{
                high=(mid-1);
            }
        }
        return result!=null?result:-1;
    }
}
