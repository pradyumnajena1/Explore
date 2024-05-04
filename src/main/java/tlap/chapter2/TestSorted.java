package tlap.chapter2;

import epp.array.ArrayUtils;

public class TestSorted {
    public static void main(String[] args) {
        System.out.println(isSorted(ArrayUtils.randomArray(10,1,20)));
        System.out.println(isSorted(ArrayUtils.randomSortedArray(10,1,20)));
    }

    private static boolean isSorted(int[] values) {
        return isSorted(values,0,values.length-1);
    }

    private static boolean isSorted(int[] values, int start, int end) {
        for(int i=start+1;i<=end;i++){
            if(values[i]<values[i-1]){
                return false;
            }
        }
        return true;
    }
}
