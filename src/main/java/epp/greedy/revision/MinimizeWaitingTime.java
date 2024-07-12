package epp.greedy.revision;

import epp.ListUtils;

import java.util.Collections;
import java.util.List;

public class MinimizeWaitingTime {
  public static void main(String[] args) {
    List<Integer> executionTimes = ListUtils.randomList(10, 1, 20);
    System.out.println(executionTimes);
    int minWaitingTime = getMinWaitingTime(executionTimes);
    System.out.println(minWaitingTime);
  }

  private static int getMinWaitingTime(List<Integer> executionTimes) {
    Collections.sort(executionTimes);
    int waitTIme = 0;
    int totalWaitTime = 0;
    for (int i = 0; i < executionTimes.size(); i++) {
      totalWaitTime += waitTIme;
      waitTIme += executionTimes.get(i);
    }
    return totalWaitTime;
  }
}
