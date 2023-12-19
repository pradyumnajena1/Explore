package epp.array;

import java.util.Arrays;

public class IncrementBigInt {
    public static void main(String[] args) {
        int[] newValue = incrementBigInt(new int[]{9,9,9,9});
        System.out.println(Arrays.toString(newValue));
    }

    private static int[] incrementBigInt(int[] bigIntValue) {
        int[] newValue = new int[bigIntValue.length+1];
        int carry =1;
        int writeIndex = newValue.length-1;
        for(int readIndex=bigIntValue.length-1;readIndex>=0;readIndex--){

            int sum = carry + bigIntValue[readIndex];
            newValue[writeIndex] = sum % 10;
            carry = sum / 10;
            writeIndex--;
        }
        if(carry==1){
            newValue[writeIndex] = carry;
        }
        return newValue;
    }
}
