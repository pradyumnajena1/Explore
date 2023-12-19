package epp.array.revision;

import epp.array.ArrayUtils;

public class RandomSubset {
    public static void main(String[] args) {
        int[] values = {1,2,3,4,5,6,7,8,9,10};
        int[] subset = getRandomSubset(values,5);
        ArrayUtils.printArray(subset);
    }

    private static int[] getRandomSubset(int[] values, int numElements) {

        for(int i=0;i<numElements;i++){
            int randomIndex = (int) (Math.random()*(values.length-i));
            ArrayUtils.swap(values,randomIndex,values.length-1-i);

        }
        int[] result = new int[numElements];
        System.arraycopy(values,values.length-numElements,result,0,numElements);

        return result;
    }
}
