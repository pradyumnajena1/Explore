package epp.binarySearch.revision;

public class ComputeIntegerSquareRoot {
    public static void main(String[] args) {
         int sqrt = computeSquareRoot(300);
        System.out.println(sqrt);
    }

    public static int computeSquareRoot(int value) {
        int start = 1;
        int end = value;
        int sqrt=1;
        while (start<=end){
            int mid = start+(end-start)/2;
            if(mid*mid == value){
                sqrt = mid;
                break;
            } else if (mid*mid<value) {
                start = mid+1;
                sqrt = mid;

            }else{
                end = mid-1;
            }
        }
        return sqrt;
    }
}
