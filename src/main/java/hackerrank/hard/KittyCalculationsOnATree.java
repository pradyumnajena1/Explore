package hackerrank.hard;

import guidetocompetitiveprogramming.CentroidDecomposition;
import guidetocompetitiveprogramming.DistanceBetweenNodesQueryProcessor;
import guidetocompetitiveprogramming.LCAQueryProcessor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class KittyCalculationsOnATree {

    private static class Solution {

        public void calculateOnTree(int n, List<List<Integer>> edges, List<List<Integer>> queries) {
            List<List<Integer>> adjList = new ArrayList<>();
            for (int i = 0; i <= n; i++) {
                adjList.add(new ArrayList<>());
            }
            for (List<Integer> edge : edges) {
                adjList.get(edge.get(0)).add(edge.get(1));
                adjList.get(edge.get(1)).add(edge.get(0));
            }
             CentroidDecomposition centroidDecomposition = new CentroidDecomposition(adjList.size()+1, adjList);
            LCAQueryProcessor queryProcessor = new LCAQueryProcessor(centroidDecomposition.getCentroidTree(), centroidDecomposition.getRootCentroid());
            DistanceBetweenNodesQueryProcessor distanceBetweenNodesQueryProcessor = new DistanceBetweenNodesQueryProcessor(adjList );

            int modulo = (int) (Math.pow(10, 9) + 7);
            for (List<Integer> query : queries) { 
                long sum = 0;
                for (int i = 0; i < query.size() - 1; i++) {
                    long prod = query.get(i);
                    long partialSum = 0;
                    for (int j = i + 1; j < query.size(); j++) {
                       int distance = getDistance(queryProcessor,distanceBetweenNodesQueryProcessor,query.get(i),query.get(j));
                        partialSum += (long) query.get(j) * distance;

                    }
                    prod = (prod * partialSum) % modulo;
                    sum = (sum + prod) % modulo;
                }
                System.out.println(  sum);
            }
        }

        public int getDistance(LCAQueryProcessor lcaQueryProcessor,DistanceBetweenNodesQueryProcessor distanceQueryProcessor, int a, int b) {
            int lca = lcaQueryProcessor.getLowestCommonAncestor(a, b);
            int distance = distanceQueryProcessor.getDistance(a,lca)+
                    distanceQueryProcessor.getDistance(b,lca);
            return distance;
        }

    }

    public static void main(String[] args) throws IOException {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

        BufferedReader bufferedReader = new BufferedReader(new FileReader("C:\\Users\\Pradyumna\\IdeaProjects" +
                "\\Explore\\src\\main\\resources\\Input.txt"));
        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int q = Integer.parseInt(firstMultipleInput[1]);

        List<List<Integer>> edges = new ArrayList<>();

        IntStream.range(0, n - 1).forEach(i -> {
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
        List<List<Integer>> querySets = new ArrayList<>();
        IntStream.range(0, q).forEach(i -> {
            try {
                String[] numItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int numItem = Integer.parseInt(firstMultipleInput[0]);
                querySets.add(
                        Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                                .map(Integer::parseInt).limit(numItem)
                                .collect(Collectors.toList())
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        new Solution().calculateOnTree(n, edges, querySets);

    }
}
