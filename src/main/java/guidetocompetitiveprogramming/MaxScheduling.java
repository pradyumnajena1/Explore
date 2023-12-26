package guidetocompetitiveprogramming;

import epp.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MaxScheduling {
    public static void main(String[] args) {
        List<Pair<Integer, Integer>> maxSchedules = maxSchedules(new ArrayList<>(List.of(
                List.of(1, 9),
                List.of(8, 12),
                List.of(11, 13),
                List.of(13, 23)
        )));
        System.out.println(maxSchedules);
    }
    static List<Pair<Integer,Integer>> maxSchedules(List<List<Integer>> schedules){
        List<Pair<Integer,Integer>> pairs = new ArrayList<>();
        List<Pair<Integer,Integer>> maxSchedules = new ArrayList<>();
        for(List<Integer> aSchedule:schedules){
            pairs.add(new Pair<>(aSchedule.get(0), aSchedule.get(1) ));
        }
        pairs.sort(Comparator.comparingInt(Pair::getSecond));
        for(Pair<Integer,Integer> aSchedule:pairs){
            if(maxSchedules.isEmpty() || maxSchedules.get(maxSchedules.size()-1).getSecond()< aSchedule.getFirst()){
                maxSchedules.add(aSchedule);
            }
        }

        return maxSchedules;
    }
}
