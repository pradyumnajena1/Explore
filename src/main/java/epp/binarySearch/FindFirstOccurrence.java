package epp.binarySearch;

public class FindFirstOccurrence {
    public static void main(String[] args) {
        int[] array = new int[]{-14,-10,2,108,108,243,285,285,285,401};
        int index  = findFirstOccurrence(array,285);
        System.out.println(index);
    }

    public static int findFirstOccurrence(int[] array, int value) {
        int start = 0;
        int end = array.length-1;
        int index = -1;
        while (start<=end){

            int mid = start+(end-start)/2;
            if(array[mid]<value){
                start=mid+1;
            }else if(array[mid]==value){
                index = mid;
                end = mid-1;
            }else{
                end = mid-1;
            }
        }
        return index ;
    }
}
