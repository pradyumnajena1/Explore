package guidetocompetitiveprogramming;

import epp.DisjointUnionSet;
import epp.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * There are n cities numbered
 * 1, 2,...,n. Initially there are no roads between the cities. Then, during m days, each
 * day a new road is built between two cities. Finally, we are given k queries of the form
 * (a, b), and our task is to determine for each query the earliest moment when cities a
 * and b are connected. We can assume that all requested pairs of cities are connected
 * after m days.
 */
public class ParallelBinarySearch {

    public static void main(String[] args) {
        int numCities = 4;
        List<Pair<Integer, Integer>> roads = new ArrayList<>(List.of(new Pair<>(1, 3), new Pair<>(3, 4), new Pair<>(1, 4), new Pair<>(2, 4)));
        List<Pair<Integer, Integer>> queries = new ArrayList<>(List.of(new Pair<>(1, 4), new Pair<>(2, 3)));

        List<Integer> earliestConnectivity = findEarliestConnectivity(numCities, roads, queries);
        System.out.println(earliestConnectivity);
    }

    private static List<Integer> findEarliestConnectivity(int numCities, List<Pair<Integer, Integer>> roads, List<Pair<Integer, Integer>> queries) {
        //return findEarliestConnectivityBruteForce(numCities,roads,queries);
        return findEarliestConnectivityParallelBinarySearch(numCities, roads, queries);
    }

    /**
     * total complexity logm *( k log(n) + mlog(n))
     * @param numCities
     * @param roads
     * @param queries
     * @return
     */
    private static List<Integer> findEarliestConnectivityParallelBinarySearch(int numCities, List<Pair<Integer, Integer>> roads, List<Pair<Integer, Integer>> queries) {
        List<Pair<Integer, Integer>> ranges = new ArrayList<>();
        List<Integer> candidates = new ArrayList<>();
        for (int i = 0; i < queries.size(); i++) {
            ranges.add(new Pair<>(1, roads.size()));
            candidates.add(i);
        }
        findEarliestConnectivityParallelBinarySearch(1, roads.size(), numCities, roads, queries, ranges, candidates);
        return ranges.stream().map(Pair::getSecond).collect(Collectors.toList());


    }

    private static void findEarliestConnectivityParallelBinarySearch(int left, int right, int numCities, List<Pair<Integer, Integer>> roads, List<Pair<Integer, Integer>> queries, List<Pair<Integer, Integer>> ranges, List<Integer> candidates) {

        if (left < right) {
            int mid = (left + right) / 2;
            DisjointUnionSet set = new DisjointUnionSet(numCities + 1);
            for (int i = 0; i <  mid; i++) {
                Pair<Integer, Integer> road = roads.get(i);
                set.union(road.getFirst(), road.getSecond());
            }
            List<Integer> leftCandidates = new ArrayList<>();
            List<Integer> rightCandidates = new ArrayList<>();
            for (Integer candidate : candidates) {


                Pair<Integer, Integer> query = queries.get(candidate);
                Pair<Integer, Integer> range = ranges.get(candidate);
                if (set.find(query.getFirst()) == set.find(query.getSecond())) {
                    ranges.set(candidate, new Pair<>(range.getFirst(), mid));
                    leftCandidates.add(candidate);
                } else {
                    ranges.set(candidate, new Pair<>(mid + 1, range.getSecond()));
                    rightCandidates.add(candidate);
                }
            }
            findEarliestConnectivityParallelBinarySearch(left, mid, numCities, roads, queries, ranges, leftCandidates);
            findEarliestConnectivityParallelBinarySearch(mid+1, right, numCities, roads, queries, ranges, rightCandidates);
        }

    }

    /**
     * for each query find the earliest.
     * adding all roads mlogn
     * finding connectivity O(1)
     * total complexity k * m logn where k is num queries
     *
     * @param numCities
     * @param roads
     * @param queries
     * @return
     */
    private static List<Integer> findEarliestConnectivityBruteForce(int numCities, List<Pair<Integer, Integer>> roads, List<Pair<Integer, Integer>> queries) {
        List<Integer> result = new ArrayList<>();
        for (Pair<Integer, Integer> query : queries) {
            result.add(findEarliestConnectivity(numCities, roads, query));
        }
        return result;
    }

    private static Integer findEarliestConnectivity(int numCities, List<Pair<Integer, Integer>> roads, Pair<Integer, Integer> query) {
        DisjointUnionSet set = new DisjointUnionSet(numCities + 1);
        int index = 1;
        for (Pair<Integer, Integer> road : roads) {
            set.union(road.getFirst(), road.getSecond());
            ;
            if (set.find(query.getFirst()) == set.find(query.getSecond())) {
                break;
            }
            index++;
        }
        return index;
    }

}
