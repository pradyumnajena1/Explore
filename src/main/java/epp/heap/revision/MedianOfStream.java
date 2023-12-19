package epp.heap.revision;

import java.util.Arrays;
import java.util.List;

public class MedianOfStream {
    public static void main(String[] args) {
        List<Integer> values = Arrays.asList(1,5,2,6,7,9,11);
        MedianCalculator calculator = new MedianCalculator();
        for(Integer value:values){

            calculator.add(value);
            System.out.println(calculator.getMedian());
        }

    }

}
