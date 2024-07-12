package epp.greedy.revision;

import epp.ListUtils;

import java.util.*;

/**
 * We consider the problem of assigning tasks to workers. Each worker mustbeassigned exactly two
 * tasks. Each task takes a fixed amount of time. Tasks are independent, i.e., there are no
 * constraints of the form "Task 4 cannot start before Task 3 is completed." Any task can be
 * assigned to any worker. We want to assign tasks to workers so as to minimize how long it takes
 * before all tasks are completed. Forexample, if there are 6 tasks whose durations are 5, 2, 1,
 * 6,4,4 hours, then an optimum assignment is to give the first two tasks (i.e., the tasks with
 * duration 5 and 2) to one worker, the next two (1 and 6) to another worker, and the last two tasks
 * (4 and 4) to the last worker. For this assignment, all tasks will finish after max(5 + 2, 1 + 6,
 */
public class OptimalTaskAssignment {
  public static void main(String[] args) {
    List<Integer> tasks = ListUtils.randomList(10, 5, 15);
    List<Assignment> assignments = getOptimalAssignment(tasks);
    System.out.println(assignments);
  }

  private static List<Assignment> getOptimalAssignment(List<Integer> tasks) {
    // Your implementation here
    List<Assignment> result = new ArrayList<>();
    Collections.sort(tasks);
    for (int i = 0, j = tasks.size() - 1; i < j; i++, j--) {
      result.add(new Assignment(tasks.get(i), tasks.get(j)));
    }
    return result;
  }

  private static class Assignment {
    private int task1;
    private int task2;

    public Assignment(int task1, int task2) {
      this.task1 = task1;
      this.task2 = task2;
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", Assignment.class.getSimpleName() + "[", "]")
              .add("task1=" + task1)
              .add("task2=" + task2)
              .toString();
    }
  }
}
