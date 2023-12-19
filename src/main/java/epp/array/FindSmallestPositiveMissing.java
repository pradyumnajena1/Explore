package epp.array;

import java.util.Arrays;

public class FindSmallestPositiveMissing {
    public static void main(String[] args) {
        int[] values = new int[]{5,4,3,2,1};
        System.out.println(Arrays.toString(values));
        int missing = findSmallestPositiveMissing(values);
        System.out.println(Arrays.toString(values));
        System.out.println(missing);
    }

    private static int findSmallestPositiveMissing(int[] values) {

        for(int i=0;i<values.length;i++){
            if(values[i]>0 && values[i] <= values.length && values[i]!=i+1 && values[i]!=values[values[i]-1]){

                int j=i;
                while (values[j]>0 && values[j] <= values.length && values[j]!=j+1 && values[j]!=values[values[j]-1]){
                    ArrayUtils.swap(values,j,values[j]-1);

                }

            }
        }
        for(int i=0;i<values.length;i++){
            if(values[i]!=i+1){
                return i+1;
            }
        }
        return values.length+1;
    }
}
