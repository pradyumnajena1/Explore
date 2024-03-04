package meta;

public class SmallestSubArraySumGreaterThank {
    public static void main(String[] args) {
        System.out.println(smallestSubArrayWithSumGreaterThan(new int[]{1, 4, 45, 6, 0, 19},51));
        System.out.println(smallestSubArrayWithSumGreaterThan(new int[]{1, 10, 5, 2, 7},9));
        System.out.println(smallestSubArrayWithSumGreaterThan(new int[]{1, 11, 100, 1, 0, 200, 3, 2, 1, 250},280));
    }

    private static int smallestSubArrayWithSumGreaterThan(int[] values, int k) {
        int left = 0;
        int right = 0;
        int sum = 0;
        int length = Integer.MAX_VALUE;
        while (right <values.length){
            while (right< values.length && sum<=k){
                sum+=values[right];
                right++;
            }
            while (sum>k && left< values.length){
                length = Math.min(length,right-left);
                sum -=  values[left++];
            }
        }
        return length;
    }
}
