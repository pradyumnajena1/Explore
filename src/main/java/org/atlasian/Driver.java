package org.atlasian;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Driver {
    public static void main(String[] args) {
        WinnerSelector winnerSelector = new WinnerSelector();
        List<Ballot> ballots = new ArrayList<>();
        ballots.add(new Ballot(new ArrayList<>(List.of("A","B","C" ))));
        ballots.add(new Ballot(new ArrayList<>(List.of("D","B","A" ))));


        Comparator<Map.Entry<String, Long>> comparator =  new BallotComparator(ballots);
        List<String> results = winnerSelector.getResults(ballots, comparator);
        System.out.println(results);
    }
}
