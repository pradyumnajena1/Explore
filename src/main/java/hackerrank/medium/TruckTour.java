package hackerrank.medium;

import epp.Pair;

import java.util.ArrayList;
import java.util.List;

public class TruckTour {
    public static void main(String[] args) {
        System.out.println(truckTour(new ArrayList<>(List.of(List.of(1, 5), List.of(10, 3), List.of(3, 4)))));
    }
    public static int truckTour(List<List<Integer>> petrolpumps) {
        // Write your code here

        Pair<Integer,Integer> mincity = new Pair<>(0,0);
        int carry = 0;
        for(int i=1;i<petrolpumps.size();i++){
            List<Integer> previous = petrolpumps.get(i - 1);
            carry = carry + previous.get(0) - previous.get(1);
            if(carry<mincity.getSecond()){
                mincity = new Pair<>(i,carry);
            }
        }
        return mincity.getFirst();



    }
}
