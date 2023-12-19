package epp.array;

import java.util.Arrays;

public class DeleteValueFromArray {
    public static void main(String[] args) {
        int[] values = {5, 3, 7, 11, 2, 3, 13, 5, 7};
        int lastValidValue = deleteValueFromArray(values,3);
        System.out.println(lastValidValue);
        System.out.println(Arrays.toString(values));
    }

    private static int deleteValueFromArray(int[] values, int value) {
        int writePosition=-1;
        for(int i=0;i<values.length;i++){
            if(values[i]!=value){
                values[++writePosition]=values[i];
            }
        }
        for(int i=writePosition+1;i<values.length;i++){
            values[i] =0;
        }
        return values[writePosition];
    }
}
