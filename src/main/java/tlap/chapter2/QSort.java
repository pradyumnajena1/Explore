package tlap.chapter2;

import epp.array.ArrayUtils;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.StringJoiner;

public class QSort {
    public static void main(String[] args) {
        Student[] students = {
                new Student(1,3.4,"prakash"),
                new Student(13,4.4,"aditya"),
                new Student(5,2.4,"minakshi"),
                new Student(11,3.6,"rahul"),
                new Student(11,4.4,"bikash"),
        };
        Arrays.sort(students, Comparator.comparingInt(a -> a.id));
        ArrayUtils.printArray(students);
        Arrays.sort(students, Comparator.comparing(a -> a.name));
        ArrayUtils.printArray(students);

        Arrays.sort(students, Comparator.comparingDouble(  (Student a) -> a.grade).thenComparingInt(a->a.id));
        ArrayUtils.printArray(students);
    }

    private static class Student{
        int id;
        double grade;
        String name;

        public Student(int id, double grade, String name) {
            this.id = id;
            this.grade = grade;
            this.name = name;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Student.class.getSimpleName() + "[", "]")
                    .add("id=" + id)
                    .add("grade=" + grade)
                    .add("name='" + name + "'")
                    .toString();
        }
    }
}
