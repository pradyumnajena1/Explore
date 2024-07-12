package epp.dp.revision;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountNumWaysToScore {
  public static void main(String[] args) {
    int totalScore = 12;
    int[] points = {2, 3, 7};

    int numWays = countNumWaysToScoreRecursive(totalScore, points);
    System.out.println(numWays);
    numWays = countTotalNumWaysIter(totalScore, points);
    System.out.println(numWays);

    numWays = countTotalNumOfSequences(totalScore, points);
    System.out.println(numWays);

    numWays = countTotalNumOfSequences(6,3, points);
    System.out.println(numWays);
  }

  public static int countNumWaysToScoreRecursive(int totalScore, int[] points) {
    Map<List<Integer>, Integer> cache = new HashMap<>();
    return countNumWaysToScoreRecursive(totalScore, points, 0, cache);
  }

  private static int countNumWaysToScoreRecursive(
      int totalScore, int[] points, int i, Map<List<Integer>, Integer> cache) {
    if (totalScore == 0) {
      return 1;
    }
    if (totalScore < 0 || i == points.length) {
      return 0;
    }

    List<Integer> key = List.of(totalScore, i);
    if (!cache.containsKey(key)) {
      int numWays = 0;
      for (int j = 0; j * points[i] <= totalScore; j++) {
        numWays += countNumWaysToScoreRecursive(totalScore - j * points[i], points, i + 1, cache);
      }
      cache.put(key, numWays);
    }

    return cache.get(key);
  }

  public static int countTotalNumOfSequences(int totalScore, int[] points) {
    Map<Integer, Integer> cache = new HashMap<>();
    return countTotalNumOfSequences(totalScore, points, cache);
  }

  private static int countTotalNumOfSequences(
      int totalScore, int[] points, Map<Integer, Integer> cache) {
    if (totalScore == 0) {
      return 1;
    }
    if (totalScore < 0) {
      return 0;
    }
    if (!cache.containsKey(totalScore)) {
      int numWays = 0;
      for (int i = 0; i < points.length; i++) {
        numWays += countTotalNumOfSequences(totalScore - points[i], points, cache);
      }
      cache.put(totalScore, numWays);
    }

    return cache.get(totalScore);
  }

  public static int countTotalNumOfSequences(int team1Score,int team2Score, int[] points) {
    Map<List<Integer>, Integer> cache = new HashMap<>();
    return countTotalNumOfSequences(team1Score,team2Score, points, cache);
  }

  private static int countTotalNumOfSequences(int team1Score, int team2Score, int[] points, Map<List<Integer>, Integer> cache) {
    if (team1Score == 0 && team2Score == 0) {
      return 1;
    }
    if (team1Score < 0 || team2Score < 0) {
      return 0;
    }
    List<Integer> key = List.of(team1Score, team2Score);
    if(!cache.containsKey(key)) {
      int numWays = 0;
      for (int i = 0; i < points.length; i++) {
        numWays += countTotalNumOfSequences(team1Score - points[i], team2Score, points, cache);
        numWays += countTotalNumOfSequences(team1Score, team2Score - points[i], points, cache);
      }
      cache.put(key, numWays);
    }
    return cache.get(key);
  }

  public static int countTotalNumWaysIter(int totalScore, int[] points) {
    int[][] numWays = new int[points.length][totalScore + 1];
    for (int i = 0; i < points.length; i++) {
      numWays[i][0] = 1;
      for (int j = 1; j <= totalScore; j++) {
        int numWaysWithout = i - 1 >= 0 ? numWays[i - 1][j] : 0;
        int numWaysWith = j >= points[i] ? numWays[i][j - points[i]] : 0;
        numWays[i][j] = numWaysWithout + numWaysWith;
      }
    }
    return numWays[points.length - 1][totalScore];
  }
}
