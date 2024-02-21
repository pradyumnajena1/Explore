package practicepramp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 Q1. Longest Increasing Subsequence
 Unsolved
 feature icon
 Get your doubts resolved blazing fast with Chat GPT Help
 Check Chat GPT
 feature icon
 Using hints is now penalty free
 Use Hint
 Problem Description
 Find the longest increasing subsequence of a given array of integers, A.

 In other words, find a subsequence of array in which the subsequence's elements are in strictly increasing order, and in which the subsequence is as long as possible.

 In this case, return the length of the longest increasing subsequence.

 Input 1:

 A = [1, 2, 1, 5]
 Input 2:

 A = [0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15]
 3

 Output 2:

 6
 */
public class LongestIncreasingSubSequence {

    static int longestIncreasingSubSequence(List<Integer> values){
        List<Integer> sequence = new ArrayList<>();
        int longest = 0;
        for(int i=0;i<values.size();i++){
            if(sequence.size()==0 || sequence.get(sequence.size()-1)<values.get(i)){
                sequence.add(values.get(i));
                longest = Math.max(longest,sequence.size());
            }else{
               int index =  Collections.binarySearch(sequence,values.get(i));
               if(index<0){
                   index = -index-1;
               }
               sequence.set(index,values.get(i));
            }
        }
        return longest;

    }

    public static void main(String[] args) {
        System.out.println(longestIncreasingSubSequence(new ArrayList<>(List.of(1, 2, 1, 5))));
        System.out.println(longestIncreasingSubSequence(new ArrayList<>(List.of(0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13, 3, 11, 7, 15))));
    }




}
