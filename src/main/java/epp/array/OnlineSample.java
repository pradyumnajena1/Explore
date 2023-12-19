package epp.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OnlineSample {
    public static void main(String[] args) {
        List<Integer> stream = new ArrayList<>();
        int n = 100;
        for(int i=0;i<n;i++){
            stream.add(i+1);
        }
        int[] randomSample = randomSample(stream,20,5);
        System.out.println(Arrays.toString(randomSample));
    }

    private static int[] randomSample(List<Integer> stream, int numItems, int sampleSize) {
        if(sampleSize>numItems){
            throw new IllegalArgumentException("invalid sample size"+sampleSize);

        }
        int[] sampleData = new int[sampleSize];
        for(int i=0;i<sampleSize;i++){
            sampleData[i] = stream.get(i);
        }
        for(int i=sampleSize;i<numItems;i++){
            int nextValue = stream.get(i);
            int nextIndex =(int) (Math.random() * (i));
            System.out.println(nextIndex);
            if(nextIndex<=sampleSize-1){
                sampleData[nextIndex] = nextValue;
            }
        }
        return sampleData;
    }
}
