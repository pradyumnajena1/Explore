package hackerrank.medium;

import java.util.*;

public class GridlandMetro {
    public static void main(String[] args) {
        ArrayList<List<Integer>> tracks = new ArrayList<>();
        tracks.add(new ArrayList<>(List.of(2, 2, 3)));
        tracks.add(new ArrayList<>(List.of(3, 1, 4)));
        tracks.add(new ArrayList<>(List.of(4, 4, 4)));
        System.out.println(gridlandMetro(4, 4, 3, tracks));
    }
    public static int gridlandMetro(int n, int m, int k, List<List<Integer>> tracks) {
        // Write your code here
        Map<Integer,List<List<Integer>>> rowToTrackmap = new HashMap<>();
        for(List<Integer> track:tracks){
            int row = track.get(0);
            List<List<Integer>> lists = rowToTrackmap.getOrDefault(row, new ArrayList<>());
            lists.add(track);
            rowToTrackmap.put(row,lists);
        }
        for(Map.Entry<Integer,List<List<Integer>>> entry:rowToTrackmap.entrySet()){
            entry.getValue().sort(Comparator.comparingInt((List<Integer> x) -> x.get(1)));
        }
        int numLampPosts = 0;
        for(int i=1;i<=n;i++){
            List<List<Integer>> lists = rowToTrackmap.get(i);
            if(lists==null){
                numLampPosts+=m;
                continue;
            }
            for(int j=1;j<=m;j++){
                if(!isTrackAtPoint(i,j,rowToTrackmap)){
                    numLampPosts++;
                }
            }
        }
         return numLampPosts;
    }

    private static boolean isTrackAtPoint(int i, int j, Map<Integer, List<List<Integer>>> rowToTrackmap) {
        List<List<Integer>> sortedTracks = rowToTrackmap.get(i);
        if(sortedTracks==null){
            return false;
        }
        int start = 0;
        int end = sortedTracks.size()-1;
        while (start<=end){
            int mid = (start+end)/2;
            List<Integer> track = sortedTracks.get(mid);
            if(track.get(1)<= j &&j<= track.get(2)){
                return true;
            }else if(track.get(1)> j){
                end =mid-1;
            }else{
                start = mid+1;
            }
        }
        return false;
    }
}
