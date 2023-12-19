package epp.binarysearchtree;

import java.util.Arrays;
import java.util.Comparator;

public class TopAverageAcrossThreeTests {

    private static class TestScore implements Comparable<TestScore>{
        private final Comparator<TestScore> testScoreComparator = Comparator.comparing(TestScore::getScore).thenComparing(TestScore::getStudentId);
        int studentId;
        int score;

        public TestScore(int studentId, int score) {
            this.studentId = studentId;
            this.score = score;
        }

        public int getStudentId() {
            return studentId;
        }

        public int getScore() {
            return score;
        }

        @Override
        public int compareTo(TestScore o) {
            return testScoreComparator.compare(this,o);
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("TestScore{");
            sb.append("studentId=").append(studentId);
            sb.append(", score=").append(score);
            sb.append('}');
            return sb.toString();
        }
    }
    public static void main(String[] args) {
        TestScore[] scores = new TestScore[50];
        for(int i=0;i<scores.length;i++){
            scores[i] = new TestScore( (int)(Math.random()*20),50+(int)(Math.random()*50));
        }
        Arrays.sort(scores);
        System.out.println(Arrays.toString(scores));

    }

}
