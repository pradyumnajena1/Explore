package epp.sorting.revision;

import epp.ListUtils;
import epp.array.ArrayUtils;
import epp.hashmap.MapUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CountingSort {
    public static void main(String[] args) {
        Map<Integer,Integer> test = new HashMap<>();
        test.put(2,2);
        test.put(1,2);
        System.out.println(test);
        List<Person> personList = new ArrayList<>();
        int[] salaries = ArrayUtils.randomArray(100,200000,200100);
        for(int salary:salaries){
            personList.add(new Person(salary));
        }
        printPersons(personList);
    }

    private static void printPersons(List<Person> personList) {
        Map<Integer, Integer> value_to_count = new TreeMap<>( MapUtils.getFrequencies(personList,(Person p)->p.salary));
        System.out.println(value_to_count);
        Map<Integer, Integer> value_to_offset = new TreeMap<>();
        int offset = 0;
        for(int value:value_to_count.keySet()){
            value_to_offset.put(value,offset);
            offset+= value_to_count.get(value);
        }
        System.out.println(value_to_offset);
        while (value_to_offset.size()>0){

            Map.Entry<Integer, Integer> from = value_to_offset.entrySet().iterator().next();

            int fromIndex = from.getValue();

            int toKey = personList.get(fromIndex).salary;
            int toIndex =  value_to_offset.get(toKey);

            ListUtils.swap(personList, fromIndex, toIndex);
            value_to_count.put(toKey,value_to_count.get(toKey)-1);
            if(value_to_count.get(toKey)>0){
                value_to_offset.put(toKey,value_to_offset.get(toKey)+1);
            }else{
                value_to_offset.remove(toKey);
            }

        }
        System.out.println(personList);


    }

    private static class Person{
        int salary;

        public Person(int salary) {
            this.salary = salary;
        }

        public int getSalary() {
            return salary;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Person.class.getSimpleName() + "[", "]")
                    .add("salary=" + salary)
                    .toString();
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
    }
}
