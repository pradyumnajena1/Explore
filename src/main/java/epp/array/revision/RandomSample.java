package epp.array.revision;

import epp.array.ArrayUtils;

public class RandomSample {
    public static void main(String[] args) {
        int[] randomValues = ArrayUtils.randomUniqueArray (10,1,20);
        ArrayUtils.printArray(randomValues);
        randomSample(randomValues,5);
        ArrayUtils.printArray(randomValues);
    }

    private static void randomSample(int[] randomValues, int numSamples) {
        for(int i=0;i<numSamples;i++){
            int selectedIndex =i+(int) (Math.random()*(randomValues.length-i));
            System.out.println(selectedIndex);
            ArrayUtils.swap(randomValues,i,selectedIndex);
        }
    }
}
