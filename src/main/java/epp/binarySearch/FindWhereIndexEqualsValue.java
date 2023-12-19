package epp.binarySearch;

public class FindWhereIndexEqualsValue {
    public static void main(String[] args) {
        int[] array = new int[]{-2,0,2,3,6,7,9};
        int index = findWhereIndexEqualsValue(array);
        System.out.println(index);
    }

    private static int findWhereIndexEqualsValue(int[] array) {

        int low =0;
        int high =array.length-1;
        while (low<=high){
            int mid = low + (high-low)/2;
            if(array[mid] == mid){
                return mid;
            }else if(array[mid] < mid){
                low = mid+1;
            }else {
                high = mid-1;
            }
        }
        return -1;
    }
}
