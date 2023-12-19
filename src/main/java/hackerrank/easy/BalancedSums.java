package hackerrank.easy;

import java.util.ArrayList;
import java.util.List;

public class BalancedSums {
    public static void main(String[] args) {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.addAll(List.of(5,6,8,11));
        System.out.println(balancedSums(arr));
    }

    public static String balancedSums(List<Integer> arr) {
        // Write your code here
        List<Integer> cumSumLeft = new ArrayList<>();
        List<Integer> cumSumRight = new ArrayList<>();
        int sum =0;
        for(int i=0;i<arr.size();i++){
            sum+=arr.get(i);
            cumSumLeft.add(sum);
        }

        sum =0;
        for(int i=arr.size()-1;i>=0;i--){
            sum+=arr.get(i);
            cumSumRight.add(sum);
        }
        System.out.println(cumSumLeft);
        System.out.println(cumSumRight);
        for(int i=0;i<arr.size();i++){
            int leftSum = i>0?cumSumLeft.get(i-1):0;
            int rightSum = i<=arr.size()-2?cumSumRight.get(arr.size()-2-i):0;
            if(leftSum==rightSum){
                return "YES";
            }
        }
        return "NO";

    }
}
