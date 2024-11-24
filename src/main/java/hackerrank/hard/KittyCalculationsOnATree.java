package hackerrank.hard;

import static java.util.stream.Collectors.toList;

import epp.array.ArrayUtils;
import guidetocompetitiveprogramming.LCAQueryProcessor;
import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class KittyCalculationsOnATree {

  public static void main(String[] args) throws IOException {
    /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */

    BufferedReader bufferedReader = new BufferedReader(new FileReader(System.getenv("INPUT_PATH")));
    String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

    int n = Integer.parseInt(firstMultipleInput[0]);

    int q = Integer.parseInt(firstMultipleInput[1]);

    List<List<Integer>> edges = new ArrayList<>();

    IntStream.range(0, n - 1)
        .forEach(
            i -> {
              try {
                edges.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList()));
              } catch (IOException ex) {
                throw new RuntimeException(ex);
              }
            });
    List<List<Integer>> querySets = new ArrayList<>();
    IntStream.range(0, q)
        .forEach(
            i -> {
              try {
                String[] numItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

                int numItem = Integer.parseInt(firstMultipleInput[0]);
                querySets.add(
                    Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .limit(numItem)
                        .collect(Collectors.toList()));
              } catch (IOException ex) {
                throw new RuntimeException(ex);
              }
            });
    new Solution().calculateOnTree(n, edges, querySets);
  }

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

      LCAQueryProcessor queryProcessor = new LCAQueryProcessor(adjList, 1);


      int modulo = (int) (Math.pow(10, 9) + 7);
      for (List<Integer> query : queries) {
        long sum = 0;
        int[][] distances = new int[n+1][n+1];

        for (int i = 0; i < query.size() - 1; i++) {
          for (int j = i + 1; j < query.size(); j++) {

            int distance = 0;
            if (distances[query.get(i)][query.get(j)]>0) {
               distance = distances[query.get(i)][query.get(j)];
            } else {
              distance = getDistance(queryProcessor, query.get(i), query.get(j));
              distances[query.get(i)][query.get(j)] = distance;

            }
            long partialSum = (long) query.get(i) * query.get(j) * distance;
            sum = (sum + partialSum) % modulo;
          }
        }
        System.out.println(sum);
      }
    }

    public int getDistance(LCAQueryProcessor lcaQueryProcessor, int a, int b) {
      int lca = lcaQueryProcessor.getLowestCommonAncestor(a, b);
      return lcaQueryProcessor.getDepth(a)
          + lcaQueryProcessor.getDepth(b)
          - 2 * lcaQueryProcessor.getDepth(lca);
    }

    private static class RootedTreeAncestor {
      private final int[][] anscestor;
      private final List<List<Integer>> adjList;
      private final int root;

      public RootedTreeAncestor(List<List<Integer>> adjList, int root) {
        this.adjList = adjList;
        this.root = root;
        int numNodes = adjList.size();
        int highestPower = 31 - Integer.numberOfLeadingZeros(numNodes - 1);
        anscestor = new int[highestPower][numNodes];
        dfsAncestor(adjList, root, 0, anscestor[0]);
        for (int row = 1; row < anscestor.length; row++) {
          for (int col = 1; col < anscestor[0].length; col++) {
            anscestor[row][col] = anscestor[row - 1][anscestor[row - 1][col]];
          }
        }
        ArrayUtils.print2DArray(anscestor);
      }

      public int ancestor(int node, int k) {
        int result = node;
        while (k > 0) {
          int row = 31 - Integer.numberOfLeadingZeros(k);
          result = anscestor[row][result];
          k = k - Integer.highestOneBit(k);
        }
        return result;
      }

      private void dfsAncestor(List<List<Integer>> adjList, int root, int parent, int[] ancestor) {
        ancestor[root] = parent;
        for (int n : adjList.get(root)) {
          if (n != parent) {
            dfsAncestor(adjList, n, root, ancestor);
          }
        }
      }
    }

    private static class LCAQueryProcessor {

      private final int root;
      private final List<List<Integer>> adjList;
      private final RootedTreeAncestor rootedTreeAncestor;
      private final int[] nodeIds;
      private final int[] levels;
      private final HashMap<Integer, Integer> indexMap;

      public LCAQueryProcessor(List<List<Integer>> adjList, int root) {
        this.root = root;
        this.adjList = adjList;
        this.rootedTreeAncestor = new RootedTreeAncestor(adjList, root);
        this.nodeIds = new int[adjList.size()];
        this.levels = new int[adjList.size()];
        dfsDepths(adjList, root, 0, 0, new AtomicInteger(0));
        this.indexMap = getIndexMap();
      }

      public int getLowestCommonAncestor(int a, int b) {
        int al = levels[indexMap.get(a)];
        int bl = levels[indexMap.get(b)];
        if (al == bl) {
          return getLowestCommonAncestor(a, b, al);
        } else {
          if (al < bl) {

            int ancestor = rootedTreeAncestor.ancestor(b, bl - al);
            return getLowestCommonAncestor(a, ancestor, al);
          } else {

            int ancestor = rootedTreeAncestor.ancestor(a, al - bl);
            return getLowestCommonAncestor(ancestor, b, bl);
          }
        }
      }

      private int getLowestCommonAncestor(int a, int b, int level) {
        int low = 1;
        int high = level;
        int lowest = 1;
        while (low <= high) {
          int mid = (low + high) / 2;

          if (rootedTreeAncestor.ancestor(a, level - mid)
              == rootedTreeAncestor.ancestor(b, level - mid)) {
            lowest = mid;
            low = mid + 1;
          } else {
            high = mid - 1;
          }
        }
        return rootedTreeAncestor.ancestor(a, level - lowest);
      }

      private HashMap<Integer, Integer> getIndexMap() {
        final HashMap<Integer, Integer> indexMap;
        indexMap = new HashMap<>();
        for (int i = 0; i < nodeIds.length; i++) {
          indexMap.put(nodeIds[i], i);
        }
        return indexMap;
      }

      private void dfsDepths(
          List<List<Integer>> adjList, int root, int parent, int level, AtomicInteger index) {
        int currentIndex = index.incrementAndGet();
        nodeIds[currentIndex] = root;
        levels[currentIndex] = level + 1;
        for (int c : adjList.get(root)) {
          if (c != parent) {
            dfsDepths(adjList, c, root, level + 1, index);
          }
        }
      }

      public int getDepth(int a) {
        Integer index = indexMap.get(a);
        return levels[index] - 1;
      }
    }
  }
}
