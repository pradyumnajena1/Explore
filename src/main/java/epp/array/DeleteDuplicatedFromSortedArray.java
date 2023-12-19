package epp.array;

public class DeleteDuplicatedFromSortedArray {
    public static void main(String[] args) {
        int validLastIndex = deleteDuplicatesFromSortedArray(new int[]{2,3,5,5,7,11,11,11,13});
        System.out.println(validLastIndex);
    }

    private static int deleteDuplicatesFromSortedArray(int[] values) {
        if(values.length<=1){
            return values.length;
        }
        int writeIndex = 0;
        for(int i=1;i<values.length;i++){
            if(values[writeIndex]!=values[i]){
                values[++writeIndex] = values[i];
            }
        }
        for(int i=writeIndex+1;i<values.length;i++){
            values[i]=0;
        }
        return writeIndex+1;
    }
}
