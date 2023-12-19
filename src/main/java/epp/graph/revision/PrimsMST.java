package epp.graph.revision;

import java.util.*;

public class PrimsMST {
    public static void main(String[] args) {
          printMST(9, new ArrayList<>(List.of(List.of(0,1,4),
                  List.of(0,7,8),
                  List.of(1,7,11),
                  List.of(1,8,2),
                  List.of(7,6,1),
                  List.of(7,8,7),
                  List.of(6,8,6),
                  List.of(8,2,2),
                  List.of(2,3,7),
                  List.of(6,5,2),
                  List.of(2,5,4),
                  List.of(5,3,14),
                  List.of(5,4,10),
                  List.of(3,4,9))));
    }

    public static void printMST(int n, List<List<Integer>> edges){
        Set<Integer> mst = new HashSet<>();
        Map<Integer,Integer> keys = new HashMap<>();
        Map<Integer,Integer> parent = new HashMap<>();
        for(int i=0;i<n;i++){
            keys.put(i,Integer.MAX_VALUE);
        }
        keys.put(0,0);
        parent.put(0,null);


        for(int i=1;i<n-1;i++){
          int minKey =   getMinKey(n,keys,mst);
          mst.add(minKey);
          for(List<Integer> edge:edges){
              Integer source = edge.get(0);
              Integer destination = edge.get(1);
              Integer cost = edge.get(2);
              if(source == minKey && !mst.contains(destination) && cost < keys.get(destination)){
                  keys.put(destination,cost);
                  parent.put(destination,minKey);
              }
          }

        }

        printMST(n,edges,parent);


    }

    private static void printMST(int n, List<List<Integer>> edges, Map<Integer, Integer> parent) {
        for(int i=0;i<n;i++){
            Integer source = parent.get(i);
            int destination = i;

            List<Integer> edge =
                    edges.stream().filter(x -> x.get(0) == source ).filter(x -> x.get(1) == destination).findFirst().orElse(null) ;
            if(edge==null){
                edge =
                        edges.stream().filter(x -> x.get(0) == destination).filter(x -> x.get(1) == source).findFirst().orElse(null);

            }
            System.out.println(source + " " + destination + " "+  (edge!=null? edge.get(2):0) );
        }
    }

    private static int getMinKey(int n,Map<Integer, Integer> keys, Set<Integer> mst) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;
        for(int i=0;i<n;i++){
            if(!mst.contains(i)&& keys.get(i)<min){
                min = keys.get(i);
                minIndex=i;
            }
        }
        return minIndex;
    }
}
