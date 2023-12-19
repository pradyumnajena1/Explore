package epp.stacknqueue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class MaxSlidingWindow {

    public static void main(String[] args) {
        int[] values = new int[]{17,17,15,3,14,16,9,11};
        List<Integer> maxInAllWindows = getMaxWindowValue(values,3);
        System.out.println(maxInAllWindows);
    }

    private static List<Integer> getMaxWindowValue(int[] values, int windowSize) {
        if(windowSize>values.length){
            throw new IllegalArgumentException("windowSize cant be greater than num values");
        }

        ArrayDeque<Integer> maxCandidates = new ArrayDeque<>();
        for(int i=0;i<windowSize;i++){
            addCandidate(maxCandidates,values[i]);
        }

        List<Integer> maxInAllWindows = new ArrayList<>();
        maxInAllWindows .add(maxCandidates.peekFirst());
        for(int i=windowSize;i<values.length;i++){
            addCandidate(maxCandidates,values[i]);
            int outGoingValue = values[i-windowSize];
            if(maxCandidates.peekFirst()==outGoingValue){
                maxCandidates.pollFirst();
            }
            maxInAllWindows .add(maxCandidates.peekFirst());
        }
        return maxInAllWindows;
    }

    private static void addCandidate(ArrayDeque<Integer> maxCandidates, int value) {
        while (!maxCandidates.isEmpty() && maxCandidates.peekLast()<value){
            maxCandidates.pollLast();
        }
        maxCandidates.offerLast(value);
    }
}
