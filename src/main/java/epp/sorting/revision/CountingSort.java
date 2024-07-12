package epp.sorting.revision;

import epp.array.ArrayUtils;
import epp.hashmap.MapUtils;
import java.util.*;

public class CountingSort {
  public static void main(String[] args) {
    Map<Integer, Integer> test = new HashMap<>();
    test.put(2, 2);
    test.put(1, 2);
    System.out.println(test);
    List<Person> personList = new ArrayList<>();
    int[] salaries = ArrayUtils.randomArray(10, 100, 105);
    for (int salary : salaries) {
      personList.add(new Person("abc_" + salary, salary));
    }
    printPersons(personList);
  }

  private static void printPersons(List<Person> personList) {
    Map<Integer, Integer> value_to_count =
        new TreeMap<>(MapUtils.getFrequencies(personList, (Person p) -> p.salary));

    Map<Integer, Integer> value_to_offset = new HashMap<>();
    int offset = 0;
    for (int value : value_to_count.keySet()) {
      value_to_offset.put(value, offset);
      offset += value_to_count.get(value);
    }
    System.out.println(value_to_count);
    System.out.println(value_to_offset);
    while (value_to_offset.size() > 0) {

      Map.Entry<Integer, Integer> from = value_to_offset.entrySet().iterator().next();

      int fromIndex = from.getValue();
      int toKey = personList.get(fromIndex).salary;
      int toIndex = value_to_offset.get(toKey);

      Collections.swap(personList, fromIndex, toIndex);
      Integer count = value_to_count.get(toKey) - 1;
      value_to_count.put(toKey, count);
      if (count > 0) {
        value_to_offset.put(toKey, toIndex + 1);
      } else {
        value_to_offset.remove(toKey);
      }
    }
    System.out.println(personList);
  }

  private static class Person {
    String name;
    int salary;

    public Person(String name, int salary) {
      this.name = name;
      this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;

      Person person = (Person) o;

      return salary == person.salary;
    }

    @Override
    public int hashCode() {
      return salary;
    }

    @Override
    public String toString() {
      return new StringJoiner(", ", Person.class.getSimpleName() + "[", "]")
              .add("name='" + name + "'")
              .add("salary=" + salary)
              .toString();
    }
  }
}
