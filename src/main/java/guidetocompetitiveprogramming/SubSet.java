package guidetocompetitiveprogramming;

import epp.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class SubSet {
    public static void main(String[] args) {
        List<Integer> set = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        processAllSubSets(set, 3);
        int setI = 0;
        setI |= 1<<1;
        setI |= 1<<3;
        setI |= 1<<4;
        setI |= 1<<8;
        processAllSubsets( setI);
    }
    private static void processAllSubsets(int set){
        int b=0;
        do{
            System.out.println(StringUtils.getBinaryString(b));

        }while ((b=((b-set)&set) )!=0);
    }

    private static void processAllSubSets2(List<Integer> set) {
        for (int i = 0; i < (1 << set.size()); i++) {
            List<Integer> subset = new ArrayList<>();
            for (int j = 0; j < set.size(); j++) {
                if ((i & (1 << j)) != 0) {
                    subset.add(set.get(j));
                }
            }
            System.out.println(subset);
        }
    }

    private static void processAllSubSets(List<Integer> set) {
        List<Integer> subset = new ArrayList<>();
        processAllSubSets(set, 0, subset);
    }

    private static void processAllSubSets(List<Integer> set, int i, List<Integer> subset) {
        if (i == set.size()) {
            System.out.println(subset);
        } else {
            subset.add(set.get(i));
            processAllSubSets(set, i + 1, subset);
            subset.remove(subset.size() - 1);

            processAllSubSets(set, i + 1, subset);

        }
    }

    private static void processAllSubSets(List<Integer> set, int k) {
        List<Integer> subset = new ArrayList<>();
        processAllSubSets(set, k, 0, subset);
    }

    private static void processAllSubSets(List<Integer> set, int k, int i, List<Integer> subset) {

        if (subset.size() == k) {
            System.out.println(subset);
        } else if (subset.size() < k && i < set.size()) {
            subset.add(set.get(i));
            processAllSubSets(set, k, i + 1, subset);
            subset.remove(subset.size() - 1);

            processAllSubSets(set, k, i + 1, subset);

        }
    }
}
