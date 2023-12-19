package epp.binarySearch;

public class ComputeIntegerSqrt {
    public static void main(String[] args) {
        int value = 288;
        int sqrt = getSqrt(value);
        System.out.println(sqrt);
    }

    private static int getSqrt(int value) {
        int low = 0;
        int high = value;
        while (low<high){
            System.out.println(low+" "+high);
            int mid = low+(high-low)/2;
            int square = mid * mid;
            if(square == value){
                return mid;
            }else if(square<value){
                low = mid+1;
            }else{
                high = mid-1;
            }
        }
        return low;
    }
}
