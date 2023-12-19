package epp.binarysearchtree.revision;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class AverageOfTopThreeScores {
    private Map<String, PriorityQueue<Integer>> studentScores;

    public AverageOfTopThreeScores() {
        studentScores = new HashMap<>();
    }

    public static void main(String[] args) {
        AverageOfTopThreeScores scorer = new AverageOfTopThreeScores();
        scorer.addScore("s1", 96);
        scorer.addScore("s1", 36);
        scorer.addScore("s1", 78);
        scorer.addScore("s1", 86);
        scorer.addScore("s2", 95);
        scorer.addScore("s2", 45);
        scorer.addScore("s2", 72);
        scorer.addScore("s3", 99);
        scorer.addScore("s4", 98);
        System.out.println(scorer.getMaxAverage());
    }

    public void addScore(String id, int score) {
        PriorityQueue<Integer> scores = studentScores.getOrDefault(id, new PriorityQueue<>());
        if (scores.size() < 3) {
            scores.offer(score);
        } else {
            if (scores.peek() < score) {
                scores.poll();
                scores.offer(score);
            }

        }
        studentScores.put(id, scores);

    }

    public int getMaxAverage() {
        int maxAverage = Integer.MIN_VALUE;
        for (String id : studentScores.keySet()) {
            PriorityQueue<Integer> scores = studentScores.get(id);
            AtomicInteger sum = new AtomicInteger();
            if (scores.size() == 3) {
                scores.forEach(x -> {
                    sum.set(sum.get() + x);
                });
                maxAverage = Math.max(maxAverage, sum.get() / 3);
            }

        }
        return maxAverage;
    }

}
