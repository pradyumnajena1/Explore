package hackerrank.medium;

import epp.Pair;
import epp.Triplet;
import epp.array.ArrayUtils;
import guidetocompetitiveprogramming.CentroidDecomposition;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class KunduAndTree {


    private final List<List<Pair<Integer, Boolean>>> originalTree;
    private final CentroidDecomposition centroidDecomposition;
    private final int n;
    private Triplet<Integer,Integer,Integer>[] pathWithColor;

    public   KunduAndTree(int n , List<Triplet<Integer,Integer,Boolean>> edges){
        this.n = n;
        List<List<Integer>> adjList = new ArrayList<>();
        List<List<Pair< Integer,Boolean>>> adjListWithColor = new ArrayList<>();
        for(int i=0;i<=n;i++){
            adjList.add(new ArrayList<>());
            adjListWithColor.add(new ArrayList<>());
        }
        for(Triplet<Integer,Integer,Boolean> edge:edges){
            Integer source = edge.getFirst();
            Integer target = edge.getSecond();
            adjList.get(source).add(target);
            adjList.get(target).add(source);

            Boolean color = edge.getThird();
            adjListWithColor.get(source).add( new Pair<>(target, color));
            adjListWithColor.get(target).add(new Pair<>(source, color));
        }

        this.originalTree = adjListWithColor;
        this.centroidDecomposition = new CentroidDecomposition(n,adjList);
        this.pathWithColor = new Triplet[n+1];
        Arrays.setAll(pathWithColor,x-> new Triplet<>(0,0,0) );

    }

    public int count(){

        int rootCentroid = centroidDecomposition.getRootCentroid();
        dfsOnCentroid(rootCentroid, -1, centroidDecomposition.getCentroidTree() );
        ArrayUtils.printArray(pathWithColor);
        int totalCount = 0;
        for(int i=1;i<=n;i++){
            totalCount+= pathWithColor[i].getFirst() * pathWithColor[i].getSecond();
        }
        return totalCount;
    }

    private   void dfsOnCentroid(int node,int parent, List<List<Integer>> centroidTree ) {
            boolean[] visited =  new boolean[n+1];
            if(parent!=-1){
                visited[parent] = true;
            }
            dfsOnOriginal(node,node,-1,originalTree ,false,visited);

            for(int c:centroidTree.get(node)){
                if(c!=parent ){

                    dfsOnCentroid(c,node,centroidTree );
                }
            }

    }


    private void dfsOnOriginal(int root, int node, int parent, List<List<Pair<Integer, Boolean>>> originalTree , boolean color, boolean[] visited) {
       if(!visited[node]){
           visited[node] = true;
           if(color){
               pathWithColor[root].setSecond(pathWithColor[root].getSecond()+1);
           }

           for(Pair<Integer, Boolean> c:originalTree.get(node)){
               if(!visited[c.getFirst()]){
                   dfsOnOriginal(root, c.getFirst(),node, originalTree,color||c.getSecond(), visited);
               }
           }


       }

    }

    public static void main(String[] args) throws IOException {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
        //BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(bufferedReader.readLine().trim());
        List<Triplet<Integer,Integer,Boolean>> edges = new ArrayList<>();
        IntStream.range(0, n-1).forEach(qItr -> {
            try {
                List<String> collect = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .collect(Collectors.toList());
                int source = Integer.parseInt(collect.get(0));
                int dest = Integer.parseInt(collect.get(1));
                Boolean color =collect.get(2).equals("r")  ;
                edges.add(new Triplet<>(source,dest,color));



            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        KunduAndTree kunduAndTree = new KunduAndTree(n, edges);
        long result = kunduAndTree.count();
        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
