package hackerrank.easy;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.TreeSet;

public class MinimumLoss {
    public static void main(String[] args) {
        ArrayList<Long> price = new ArrayList<>();
        price.addAll(List.of(20L,15L,8L,2L,12L));
        System.out.println(minimumLoss(price));
    }
    public static int minimumLoss(List<Long> price) {
        // Write your code here
       long minimumLoss = Long.MAX_VALUE;
        TreeSet<Long> tree = new TreeSet<>();
        tree.add(price.get(0));
        for(int i=1;i< price.size();i++){

            Long currentPrice = price.get(i);
            Long higher = tree.higher(currentPrice);
            if(higher!=null){
                long loss =  (higher- currentPrice);
                minimumLoss = Math.min(loss,minimumLoss);
            }
            tree.add(currentPrice);

        }


       return (int) minimumLoss;
    }
}
