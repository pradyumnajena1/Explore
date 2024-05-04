package hackerrank.medium;

import epp.Pair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class JackGoesToRapture {
    static class Result {

        /*
         * Complete the 'getCost' function below.
         *
         * The function accepts WEIGHTED_INTEGER_GRAPH g as parameter.
         */

        /*
         * For the weighted graph, <name>:
         *
         * 1. The number of nodes is <name>Nodes.
         * 2. The number of edges is <name>Edges.
         * 3. An edge exists between <name>From[i] and <name>To[i]. The weight of the edge is <name>Weight[i].
         *
         */

        public static void getCost(int gNodes, List<Integer> gFrom, List<Integer> gTo, List<Integer> gWeight) {
            // Print your answer within the function and return nothing
            Map<Integer, List<Pair<Integer, Integer>>> adjList = new HashMap<>();
            for (int i = 0; i < gFrom.size(); i++) {
                int source = gFrom.get(i);
                int dest = gTo.get(i);
                int cost = gWeight.get(i);
                List<Pair<Integer, Integer>> list = adjList.getOrDefault(source, new ArrayList<>());
                list.add(new Pair<>(dest, cost));
                adjList.put(source, list);

                list = adjList.getOrDefault(dest, new ArrayList<>());
                list.add(new Pair<>(source, cost));
                adjList.put(dest, list);
            }
            Set<Integer> visited = new HashSet<>();
            int totalCost = getMinCost(1, gNodes, 0, adjList, visited);
            System.out.println(totalCost);
        }

        private static int getMinCost(int source, int dest, int cumCost, Map<Integer, List<Pair<Integer, Integer>>> adjList, Set<Integer> visited) {
            if (source == dest) {
                return cumCost;
            }
            visited.add(source);
            int minCost = Integer.MAX_VALUE;
            List<Pair<Integer, Integer>> neighbours = adjList.get(source);
            for (Pair<Integer, Integer> neighbour : neighbours) {
                Integer nextNode = neighbour.getFirst();
                if (!visited.contains(nextNode)) {
                    int stepCost = Math.max(0, neighbour.getSecond() - cumCost);
                    minCost = Math.min(minCost, getMinCost(nextNode, dest, stepCost + cumCost, adjList, visited));
                }
            }
            visited.remove(source);
            return minCost;
        }

    }

    public static class Solution {
        public static void main(String[] args) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));

            String[] gNodesEdges = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            int gNodes = Integer.parseInt(gNodesEdges[0]);
            int gEdges = Integer.parseInt(gNodesEdges[1]);

            List<Integer> gFrom = new ArrayList<>();
            List<Integer> gTo = new ArrayList<>();
            List<Integer> gWeight = new ArrayList<>();

            IntStream.range(0, gEdges).forEach(i -> {
                try {
                    String[] gFromToWeight = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                    gFrom.add(Integer.parseInt(gFromToWeight[0]));
                    gTo.add(Integer.parseInt(gFromToWeight[1]));
                    gWeight.add(Integer.parseInt(gFromToWeight[2]));
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

            Result.getCost(gNodes, gFrom, gTo, gWeight);

            bufferedReader.close();
        }
    }

}
