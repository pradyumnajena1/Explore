package org.atlasian;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ballot {

    private final Map<String, Integer> points;


    public Ballot(List<String> votes) {

       this.points = new HashMap<>();
        int point = 3;
        for(int i=0;i< votes.size();i++){
            points.put(votes.get(i), point);
            point--;
        }
    }

    public Map<String,Integer> getPointsMap(){
        return new HashMap<>(points);
    }




}
