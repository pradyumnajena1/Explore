package hackerrank.easy;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class GradingStudents {
    public static void main(String[] args) {
        System.out.println(gradingStudents(new ArrayList<>(List.of(73, 67, 38, 33))));
    }

    public static List<Integer> gradingStudents(List<Integer> grades) {
        // Write your code here
        TreeSet<Integer> multiplesOfFive = new TreeSet<>();
        for (int i = 8; i <= 20; i++) {
            multiplesOfFive.add(i * 5);
        }

        List<Integer> roundedGrades = new ArrayList<>();
        for (Integer grade : grades) {

            Integer nextMultiple = multiplesOfFive.ceiling(grade);
            if (nextMultiple - grade >= 3) {
                roundedGrades.add(grade);
            } else {
                roundedGrades.add(nextMultiple);
            }
        }
        return roundedGrades;
    }
}
