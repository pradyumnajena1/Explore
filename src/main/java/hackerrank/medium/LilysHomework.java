package hackerrank.medium;

import epp.array.ArrayUtils;

import java.util.*;

public class LilysHomework {
    public static void main(String[] args) {
        System.out.println(lilysHomework(new ArrayList<>(List.of(2, 5, 3, 1))));
        System.out.println(lilysHomework(new ArrayList<>(List.of(7,15,12,3))));
        System.out.println(lilysHomework(new ArrayList<>(List.of(3,4,2,5,1))));
    }

    public static int lilysHomework(List<Integer> arr) {
        // Write your code here
        List<Integer> sorted = new ArrayList<>(arr);
        sorted.sort(Comparator.naturalOrder());
        int numSwapsForAscending = getNumSwaps(arr, sorted,true);
        int numSwapsForDescending = getNumSwaps(arr, sorted,false);
        return Math.min(numSwapsForDescending,numSwapsForAscending);
    }

    private static int getNumSwaps(List<Integer> arr, List<Integer> sorted,boolean ascending) {
        List<Integer> permutation = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {
            Integer value = arr.get(i);
            int index = Collections.binarySearch(sorted, value);
            if(ascending){
                permutation.add(index);
            }else{
                permutation.add(sorted.size()-1 - index);
            }
        }
        System.out.println(permutation);
        Set<Set<Integer>> cycles = new HashSet<>();
        Set<Integer> visited = new HashSet<>();
        for (int i = 0; i < permutation.size(); i++) {
            if (!visited.contains(i)) {
                visited.add(i);
                Set<Integer> cycle = new HashSet<>();
                int start = i;
                do {
                    cycle.add(start);
                    start = permutation.get(start);
                } while (start != i);
                cycles.add(cycle);
            }
        }
        System.out.println(cycles);
        int numSwaps = 0;
        for (Set<Integer> cycle : cycles) {
            numSwaps += cycle.size() - 1;
        }
        return numSwaps;
    }
    public static <T> void reverse(List<T> list) {
        int start = 0;
        int end = list.size() - 1;
        while (start < end) {
            T copy = list.get(start);
            list.set(start, list.get(end));
            list.set(end, copy);
            start++;
            end--;
        }
    }
}
