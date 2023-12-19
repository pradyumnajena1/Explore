package hackerrank.medium;

import epp.DisjointUnionSet;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JourneyTomoon {
    public static void main(String[] args) {
        ArrayList<List<Integer>> astronaut = new ArrayList<>();
        astronaut.add(new ArrayList<>(List.of(1,2)));
        astronaut.add(new ArrayList<>(List.of(3,2)));
        System.out.println(journeyToMoon(4, astronaut));

        astronaut = new ArrayList<>();
        astronaut.add(new ArrayList<>(List.of(5,3)));
        astronaut.add(new ArrayList<>(List.of(0,1)));
        astronaut.add(new ArrayList<>(List.of(2,3)));
        astronaut.add(new ArrayList<>(List.of(0,4)));
        System.out.println(journeyToMoon(6, astronaut));


        astronaut = new ArrayList<>();
        astronaut.add(new ArrayList<>(List.of(1,2)));
        astronaut.add(new ArrayList<>(List.of(3,4)));
        System.out.println(journeyToMoon(100000 , astronaut));

    }

    public static long journeyToMoon(int n, List<List<Integer>> astronaut) {
        // Write your code here
        DisjointUnionSet disjointUnionSet = new DisjointUnionSet(n);
        for (List<Integer> pair : astronaut) {
            disjointUnionSet.union(pair.get(0), pair.get(1));
        }
        Set<Integer> forests = new HashSet<>();
        long totalNumPair = ( (long) n * (n-1)) /2;

        for (int i = 0; i < n; i++) {
            int country = disjointUnionSet.find(i);
            if(!forests.contains(country)){
                int size = disjointUnionSet.getSize(country);
                totalNumPair-= ( (long )size*(size-1))/2;
                forests.add(country);

            }
        }
       // System.out.println(forests);
        System.out.println(totalNumPair);

        return   totalNumPair;
    }
    public static int nCr(int n, int r) {
        if (r > n) return 0;
        if (r == 0 || r == n) return 1;
        return nCr(n - 1, r - 1) + nCr(n - 1, r);
    }
}
