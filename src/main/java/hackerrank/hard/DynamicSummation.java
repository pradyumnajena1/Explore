package hackerrank.hard;

import guidetocompetitiveprogramming.SubTreeSumRangeQueryUpdateProcessor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class DynamicSummation {
    public static List<Integer> solve(int n,List<List<Integer>> edges,List<List<String>> queries){
        List<Integer> result= new ArrayList<>();
        List<List<Integer>> adjList = getAdjList(n, edges);
        int[] nodeValues = new int[n+1];

        for(List<String> query:queries){
            System.out.println(query);
            if(query.get(0).equals("U")){
                int root =Integer.parseInt( query.get(1));
                int node =Integer.parseInt( query.get(2));
                int a =Integer.parseInt( query.get(3));
                int b =Integer.parseInt( query.get(4));
                int delta = getDelta(a,b);
                SubTreeSumRangeQueryUpdateProcessor rangeQueryUpdateProcessor = new SubTreeSumRangeQueryUpdateProcessor(adjList,nodeValues,root);
                rangeQueryUpdateProcessor.updateNode(node,delta);
                nodeValues = rangeQueryUpdateProcessor.getValues();
            }else{
                int root =Integer.parseInt( query.get(1));
                int node =Integer.parseInt( query.get(2));
                int mod =Integer.parseInt( query.get(3));
                SubTreeSumRangeQueryUpdateProcessor rangeQueryUpdateProcessor = new SubTreeSumRangeQueryUpdateProcessor(adjList,nodeValues,root);
                int sum =  rangeQueryUpdateProcessor.getSubTreeSum(node)%mod;
                result.add(sum);
            }
        }
        return result;
    }

    private static int getDelta(int a, int b) {
        return (int) ((Math.pow(a,b) + Math.pow(a+1,b)+Math.pow(b+1,a)));
    }

    private static List<List<Integer>> getAdjList(int n, List<List<Integer>> edges) {
        List<List<Integer>> adjList = new ArrayList<>();
        for(int i = 0; i<= n; i++){
            adjList.add(new ArrayList<>());
        }
        adjList.add(new ArrayList<>());
        for(List<Integer> edge: edges){
            adjList.get(edge.get(0)).add(edge.get(1));
            adjList.get(edge.get(1)).add(edge.get(0));
        }
        return adjList;
    }

    public static void main(String[] args) throws IOException {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<Integer>> edges = new ArrayList<>();
        IntStream.range(0, n-1).forEach(i -> {
            try {
                edges.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt)
                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        int q = Integer.parseInt(bufferedReader.readLine().trim());

        List<List<String>> queries = new ArrayList<>();
        IntStream.range(0, q).forEach(i -> {
            try {
                queries.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))

                                .collect(toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        List<Integer> result = solve(n, edges, queries);
        bufferedWriter.write(
                result.stream()
                        .map(Object::toString)
                        .collect(joining("\n"))
                        + "\n" );
        bufferedReader.close();
        bufferedWriter.close();
    }
}
