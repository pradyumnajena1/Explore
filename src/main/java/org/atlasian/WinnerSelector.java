package org.atlasian;

import java.util.*;
import java.util.stream.Collectors;

public class WinnerSelector {

    /**
     * Process a list of ballots, and return all candidates sorted in descending
     * order by their total number of points.
     */

    List<String> getResults(List<Ballot> ballots, Comparator<Map.Entry<String, Long>> comparator) {
        Map<String, Long> pointsMap = new HashMap<>();

        for (Ballot aBallot : ballots) {
            Map<String, Integer> votes = aBallot.getPointsMap();
            for (Map.Entry<String, Integer> entry : votes.entrySet()) {
                String name = entry.getKey();
                Integer point = entry.getValue();

                Long currentPoint = pointsMap.getOrDefault(name, 0L);
                pointsMap.put(name, currentPoint + point);
            }

        }

        List<Map.Entry<String, Long>> list = new ArrayList<>(pointsMap.entrySet());
        list.sort(comparator);
        List<String> result = list.stream().map((Map.Entry<String, Long> x) -> x.getKey()).collect(Collectors.toList());
        return result;


    }


}
