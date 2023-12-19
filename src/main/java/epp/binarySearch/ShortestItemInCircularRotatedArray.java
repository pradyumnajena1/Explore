package epp.binarySearch;

public class ShortestItemInCircularRotatedArray {
    public static void main(String[] args) {
        int[] array = new int[]{378,478,550,631,103,203,220,234,279,368};
        int index = findShortest(array);
        System.out.println(index);
    }

    private static int findShortest(int[] array) {
        if(array[0]<array[array.length-1]){
            return array[0];
        }
         int index = -1;
        int low = 0;
        int high = array.length-1;
        while (low<=high){
            int mid = low+(high-low)/2;
            if(array[mid]<array[mid-1] && array[mid]<array[mid+1]){
                index =  mid;
                break;
            }else if(array[mid]<array[high]){
                high = mid-1;
            }else {

                low = mid+1;
            }
        }
        return index;
    }
}
