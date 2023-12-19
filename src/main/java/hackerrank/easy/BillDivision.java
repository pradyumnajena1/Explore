package hackerrank.easy;

import java.util.List;

public class BillDivision {
    public static void main(String[] args) {

    }
    public static void bonAppetit(List<Integer> bill, int k, int b) {
        // Write your code here
        int sum = bill.stream().mapToInt(Integer::intValue).sum();
        int actualBill = sum / 2 - bill.get(k) / 2;
        if(actualBill==b){
            System.out.println("Bon Appetit");
        }else{
            System.out.println(b-actualBill);
        }
    }
}
