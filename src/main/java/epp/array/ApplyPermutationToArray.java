package epp.array;

import java.util.Arrays;

public class ApplyPermutationToArray {
    public static void main(String[] args) {
        char[] values = {'a', 'b', 'c', 'd','e','f','g'};
        char[] result = applyPermutation(values,new int[]{2,0,1,3,6,5,4});
        System.out.println(Arrays.toString(values));


    }

    private static char[] applyPermutation(char[] values, int[] permutation) {

        for(int i=0;i<permutation.length;i++){
            if(permutation[i]!=i && permutation[i]>=0){
                int sourceIndex = i;
                int targetIndex = permutation[i];
                char lastValue = values[sourceIndex];

                do{
                   char nextValue = values[targetIndex];
                    values[targetIndex] = lastValue;
                    permutation[sourceIndex] = -1;
                    System.out.println(sourceIndex+" "+targetIndex+" "+lastValue+ " "+Arrays.toString(permutation));

                    lastValue = nextValue;
                    sourceIndex = targetIndex;
                    targetIndex = permutation[sourceIndex];


                }while (sourceIndex!=i);
            }
        }

        return values;
    }
}
