package hackerrank.medium;

import epp.Pair;
import epp.graph.Edge;

import java.io.*;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class JennysSubtrees {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int n = Integer.parseInt(firstMultipleInput[0]);

        int r = Integer.parseInt(firstMultipleInput[1]);

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

        int result =  jennysSubtrees(n, r, edges);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
    public static int jennysSubtrees(int n, int r, List<List<Integer>> edges) {
        // Write your code here
        Map<Integer,List<Integer>> adjMatrix = new HashMap<>();
        for(List<Integer> edge:edges){
            Integer u = edge.get(0);
            Integer v = edge.get(1);
            List<Integer> list = adjMatrix.getOrDefault(u, new ArrayList<>());
            list.add(v);
            adjMatrix.put(u, list);

            list = adjMatrix.getOrDefault(v, new ArrayList<>());
            list.add(u);
            adjMatrix.put(v, list);

        }
        Set<Integer> sizes = new HashSet<>();
        for(int i=1;i<=n;i++){

            TreeSet<Integer> subTree = getSubTree(n,i,r,adjMatrix);
            int edgeCount = 0;
            for(List<Integer> edge:edges){
                if(subTree.contains(edge.get(0)) && subTree.contains(edge.get(1))){
                    edgeCount++;
                }
            }
            System.out.println(subTree );
            System.out.println(edgeCount);
            sizes.add(subTree.size() );
        }
       return sizes.size();
    }

    private static TreeSet<Integer> getSubTree(int n,int i, int r,Map<Integer,List<Integer>> adjMatrix) {
        TreeSet<Integer> subtree = new TreeSet<>();
        List<List<Integer>> edges = new ArrayList<>();
        Queue<Pair<Integer,Integer>> queue = new ArrayDeque<>();
        Set<Integer> visitedSet = new HashSet<>();
        queue.offer(new Pair<>(i,0));
        visitedSet.add(i);
        while (!queue.isEmpty()){
            Pair<Integer, Integer> polled = queue.poll();
            if(polled.getSecond()<=r){
                subtree.add(polled.getFirst());
            }
            if(polled.getSecond()==r){
                continue;
            }
            List<Integer> neighbours = adjMatrix.get(polled.getFirst());

            for(int neigh:neighbours){
                if(!visitedSet.contains(neigh)){
                    visitedSet.add(neigh);
                    queue.offer(new Pair<>(neigh, polled.getSecond()+1));
                }
            }
        }

        return subtree;
    }
}
