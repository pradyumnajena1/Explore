package epp.binarySearch.revision;

import java.util.*;

public class BinarySearchBasic {
  public static void main(String[] args) {
    Student rajib = new Student("rajib", 6.8);
    List<Student> students =
        new ArrayList<>(
            List.of(
                new Student("pradyumna", 7.8),
                new Student("prakash", 7.8),
                rajib,
                new Student("mukesh", 9.8),
                new Student("advait", 9.8),
                new Student("vedant", 8.8)));
    Collections.sort(students, Comparator.reverseOrder());
    System.out.println(students);
    System.out.println(searchStudent(students, rajib));
  }

  public static boolean searchStudent(List<Student> students, Student rajib) {
    return Collections.binarySearch(students, rajib, Comparator.reverseOrder()) >= 0;
  }

  private static class Student implements Comparable<Student> {
    String name;
    double gpa;

    public Student(String name, double gpa) {
      this.name = name;
      this.gpa = gpa;
    }

    @Override
    public int compareTo(Student o) {
      int gpaCompare = Double.compare(this.gpa, o.gpa);
      return gpaCompare == 0 ? name.compareTo(o.name) : gpaCompare;
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", Student.class.getSimpleName() + "[", "]")
          .add("name='" + name + "'")
          .add("gpa=" + gpa)
          .toString();
    }
  }
}
