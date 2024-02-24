package cph;

import epp.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TaskSchedule {
    public static void main(String[] args) {
        List<Pair<Integer,Integer>> tasks = new ArrayList<>(List.of(new Pair<>(1,3),new Pair<>(2,5),new Pair<>(3,9),
                new Pair<>(6,8)));
        List<Pair<Integer,Integer>> maxSchedule = selectMaxTasks(tasks);
        System.out.println(maxSchedule);
    }

    private static List<Pair<Integer, Integer>> selectMaxTasks(List<Pair<Integer, Integer>> tasks) {
        List<Pair<Integer, Integer>> result= new ArrayList<>();
        tasks.sort(Comparator.comparingInt(Pair::getSecond));
        int i=0;
        while (i< tasks.size()){
            result.add(tasks.get(i));
            int j=i+1;
            while (j< tasks.size() && tasks.get(j).getFirst()<= tasks.get(i).getSecond()){
                j++;
            }
            i=j;
        }
        return result;
    }
}
