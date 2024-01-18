package hackerrank.medium;

import epp.heap.revision.MedianCalculator;

import java.util.ArrayList;
import java.util.List;

public class FindRunningMedian {

    public static List<Double> runningMedian(List<Integer> a) {
        // Write your code here
        List<Double> result = new ArrayList<>();
        MedianCalculator medianCalculator = new MedianCalculator();
        for(int i:a){
            medianCalculator.add(i);
            Double median = medianCalculator.getMedian();
            result.add(median);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(runningMedian(new ArrayList<>(List.of(12, 4, 5, 3, 8, 7))));
    }
}
