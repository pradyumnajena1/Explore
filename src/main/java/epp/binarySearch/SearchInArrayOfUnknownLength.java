package epp.binarySearch;

public class SearchInArrayOfUnknownLength {
    public static void main(String[] args) {
        int[] array = new int[]{1,2,3,4,5,6,7,8,9,10,11,12};
        int index = find(array,11);
        System.out.println(index);
    }

    private static int find(int[] array, int value) {
         int low =0;
         int high = getHigh(array,value);
         while(low<=high){
             System.out.println(low+" "+high);
             int mid = (low+high)/2;
             try {
                 if(array[mid]==value){
                     return mid;
                 }else if(array[mid]<value){
                     low = mid+1;
                 }else{
                     high=mid-1;
                 }
             }catch (Exception e){
                 high = mid-1;
             }
         }
         return -1;
       // return ArrayUtils.binarySearch(array,value,0,current);
    }

    private static int getHigh(int[] array, int value) {
        int high = 1;
        try{
            while (array[high]<=value){
                high=high*2;
            }
            return high;
        }catch (Exception e){
            return high;
        }

    }
}
