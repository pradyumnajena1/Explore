package leetcode.hard;

import epp.array.ArrayUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class CourseSchedule {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.scheduleCourse(new int[][]{{100, 200}, {200, 1300}, {1000, 1250}, {2000, 3200}}));
        System.out.println(solution.scheduleCourse(new int[][]{{1, 2} }));
        System.out.println(solution.scheduleCourse(new int[][]{{3,2},{4,3} }));
        System.out.println(solution.scheduleCourse(new int[][]{{5,5},{4,6},{2,6} }));
        System.out.println(solution.scheduleCourse(new int[][]{{5,15},{3,19},{6,7},{2,10},{5,16},{8,14},{10,11},
                {2,19} }));



    }

    private static class Solution {
        public int scheduleCourse(int[][] courses) {
            Comparator<int[]> comparator = Comparator.comparing((int[] x) -> x[0] )
                                                     .thenComparing((int[] x) -> x[1]-x[0],Comparator.reverseOrder() );

            Comparator<int[]> comparator2 = Comparator.comparing((int[] x) -> x[1]  )
                    .thenComparing((int[] x) ->  x[0],Comparator.reverseOrder()  );
            PriorityQueue<int[]> priorityQueue =
                    new PriorityQueue<>(comparator2);
            for(int[] course:courses){
                 priorityQueue.offer(course);
            }
          //  Arrays.sort(courses, Comparator.comparing((int[] x) ->x[0]).thenComparing( (int[] y)->y[1]));
           // ArrayUtils.print2DArray(courses);
            int daysPassed = 0;
            int numCoursesChoosen =0;
            while (!priorityQueue.isEmpty()){

                int[] course = priorityQueue.poll();
              //  ArrayUtils.printArray(course);
                if(daysPassed+course[0]<=course[1]){
                    numCoursesChoosen++;
                    daysPassed+=course[0];
                }
            }


            return numCoursesChoosen;
        }
    }
}
