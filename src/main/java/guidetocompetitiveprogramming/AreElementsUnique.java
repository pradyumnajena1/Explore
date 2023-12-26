package guidetocompetitiveprogramming;

import epp.array.ArrayUtils;

import java.util.Arrays;

public class AreElementsUnique {
    public static void main(String[] args) {
        int[] values = ArrayUtils.randomArray(10, 1, 20);
        boolean unique=  areUniqueElements(values);
       ArrayUtils.printArray(values);
        System.out.println(unique);

        values = ArrayUtils.randomArray(10, 1, 20);
        int uniqueElements=  numUniqueElements(values);
        ArrayUtils.printArray(values);
        System.out.println(uniqueElements);
        System.out.println(minimumDiff(values));
    }

    private static boolean areUniqueElements(int[] values) {
        Arrays.sort(values);
        boolean unique = true;
        for(int i=0;i< values.length-1;i++){
            if(values[i]==values[i+1]){
                unique = false;
                break;
            }
        }
        return unique;
    }

    private static int numUniqueElements(int[] values) {
        Arrays.sort(values);
        int unique = 1;
        for(int i=1;i< values.length;i++){
            if(values[i]!=values[i-1]){
              unique++;
            }
        }
        return unique;
    }
    private static int minimumDiff(int[] values) {
        Arrays.sort(values);
        int minDiff = Integer.MAX_VALUE;
        for(int i=1;i< values.length;i++){
            if(Math.abs( values[i]-values[i-1])<minDiff){
               minDiff = values[i]-values[i-1];
            }
        }
        return minDiff;
    }
}
