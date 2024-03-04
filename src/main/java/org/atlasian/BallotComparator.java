package org.atlasian;

import java.util.*;

public class BallotComparator implements Comparator<Map.Entry<String, Long>> {

    private List<Ballot> ballots;
    /**
     * "a"- > [1,2,2]
     */
    private Map<String, List<Integer>> positionFrequency;

    public BallotComparator(List<Ballot> ballots) {
        this.ballots = ballots;
        this.positionFrequency = computePositionFrequency(ballots);
    }

    private Map<String, List<Integer>> computePositionFrequency(List<Ballot> ballots) {
        Map<String, List<Integer>> frequency = new HashMap<>();
        for (Ballot ballot : ballots) {
            Map<String, Integer> pointsMap = ballot.getPointsMap();
            for (Map.Entry<String, Integer> entry : pointsMap.entrySet()) {

                List<Integer> list = frequency.get(entry.getKey());
                {
                    if (list == null) {
                        list = new ArrayList<>(List.of(0, 0, 0));
                    }
                }
                int position = 3 - entry.getValue();
                list.set(position, list.get(position) + 1);
                frequency.put(entry.getKey(), list);
            }


        }
        return frequency;
    }

    @Override
    public int compare(Map.Entry<String, Long> a, Map.Entry<String, Long> b) {
        int compare = Long.compare(a.getValue(), b.getValue());
        if (compare != 0) {
            return -compare;
        }
        List<Integer> freqA = positionFrequency.get(a.getKey());
        List<Integer> freqB = positionFrequency.get(b.getKey());
        return -Integer.compare(freqA.get(0), freqB.get(0));
    }
}
