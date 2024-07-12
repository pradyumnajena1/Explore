package epp.hashmap.revision;

import java.util.*;

public class FindStudentWithHighestBestOfThreeScores {
  public static void main(String[] args) {
    List<String> scores =
        new ArrayList<>(
            List.of(
                "pradyumna 91",
                "advait 91",
                "pradyumna 88",
                "pradyumna 97",
                "pradyumna 97",
                "advait 97",
                "advait 98",
                "advait 99"));
    System.out.println(findStudentWithHighestBestOfThreeScores(scores));
  }

  private static String findStudentWithHighestBestOfThreeScores(List<String> scores) {

    Map<String, PriorityQueue<Integer>> studentsMap = new HashMap<>();
    for (String line : scores) {
      String[] parts = line.split(" ");
      String student = parts[0];
      int score = Integer.parseInt(parts[1]);
      PriorityQueue<Integer> minHeap = studentsMap.getOrDefault(student, new PriorityQueue<>());
      minHeap.offer(score);
      if (minHeap.size() > 3) {
        minHeap.poll();
      }
      studentsMap.put(student, minHeap);
    }
    String topStudent = null;
    double currentMaxAvergae = 0;
    for (Map.Entry<String, PriorityQueue<Integer>> studentEntry : studentsMap.entrySet()) {
      String student = studentEntry.getKey();
      PriorityQueue<Integer> minHeap = studentEntry.getValue();
      if (minHeap.size() == 3) {
        double sum = getTopThreeScoreSum(minHeap);
        double currentAverage = sum / minHeap.size();
        if (currentAverage > currentMaxAvergae) {
          currentMaxAvergae = currentAverage;
          topStudent = student;
        }
      }
    }

    return topStudent;
  }

  private static double getTopThreeScoreSum(PriorityQueue<Integer> minHeap) {
    Iterator<Integer> iterator = minHeap.iterator();
    double sum = 0;
    while (iterator.hasNext()) {
      sum += iterator.next();
    }
    return sum;
  }
}
