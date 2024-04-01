package epp.array.revision;

import epp.array.ArrayUtils;

public class ApplyPermutationArray {
    public static void main(String[] args) {
        char[] values = {'3','4','2','5','1'};
        int[] permutation = {2,1,3,0,4 };
        char[] result = applyPermutation(values,permutation);
        ArrayUtils.printArray(result);
    }

    private static char[] applyPermutation(char[] values, int[] permutation) {

       for(int i=0;i<values.length;i++){
           if(permutation[i]>0){
               int index = i;
               char value = values[index];

               do {
                   int nextIndex = permutation[index];
                   char nextValue = values[nextIndex];

                   values[nextIndex] = value;
                   permutation[index] =  permutation[index] -values.length;

                   index = nextIndex;
                   value = nextValue;


               }while (index!=i);
           }

       }

        return values;
    }
}
