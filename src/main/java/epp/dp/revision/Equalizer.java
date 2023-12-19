package epp.dp.revision;

import java.util.*;
import java.util.stream.Collectors;

public class Equalizer {
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<>();
         int[] values = new int[]{
                 520, 862, 10, 956, 498, 956, 991, 542, 523, 664, 378, 194
         };
         list.addAll(Arrays.stream(values).boxed().collect(Collectors.toList()));
        int equal = equal(list);
        System.out.println(equal);
    }
    public static int equal(List<Integer> list) {
        // Write your code here
        Map<List<Integer>,Integer> cache = new HashMap<>();
        return equalsHelper(list,cache) ;


    }

    private static int equalsHelper(List<Integer> list, Map<List<Integer>, Integer> cache) {
        System.out.println(list);
        if(allEqual(list)){
            return 0;
        }
         list.sort(Integer::compareTo);
        if(cache.containsKey(list)){

            return cache.get(list);
        }
        Optional<Integer> min = list.stream().min(Integer::compareTo);
        Optional<Integer> max = list.stream().max(Integer::compareTo);
        int result;
        if(max.get()-min.get()>=5){
           List<Integer> list5 =   addAllBut(list, list.indexOf(max.get()), 5);
            int step5 =   equalsHelper(list5, cache);

            List<Integer>  list2 =   addAllBut(list, list.indexOf(max.get()), 2);
            int step2 =   equalsHelper(list2, cache);

            List<Integer>  list1 =   addAllBut(list, list.indexOf(max.get()), 1);
            int step1 =   equalsHelper(list1, cache);
            result= Math.min(Math.min(step5, step2), step1) + 1;
        } else if (max.get()-min.get()>=2) {
            List<Integer>  list2 =   addAllBut(list, list.indexOf(max.get()), 2);
            int step2 =   equalsHelper(list2, cache);
            List<Integer> list1 =   addAllBut(list, list.indexOf(max.get()), 1);
            int step1 =   equalsHelper(list1, cache);
            result = Math.min(step2, step1) + 1;
        }else {
            List<Integer> list1 =   addAllBut(list, list.indexOf(max.get()), 1);
            int step1 =   equalsHelper(list1, cache);
            result = step1 + 1;
        }
        cache.put(list,result);
        return result;
    }

    private static List<Integer> addAllBut(List<Integer> list, int excludeIndex, int increment) {
        ArrayList<Integer> values = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            if(i==excludeIndex){
                values.add(list.get(i));
            }else{
                values.add(list.get(i)+increment);
            }
        }
        return values;
    }

    private static boolean allEqual(List<Integer> list) {
        Optional<Integer> min = list.stream().min(Integer::compareTo);
        Optional<Integer> max = list.stream().max(Integer::compareTo);
       return min.get()==max.get();
    }
}
