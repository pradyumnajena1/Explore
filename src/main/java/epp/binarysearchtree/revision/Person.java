package epp.binarysearchtree.revision;

import java.util.StringJoiner;

class Person implements Comparable<Person> {
    int age;
    String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public Person(int age) {
        this.age = age;
        this.name = "";
    }

    @Override
    public int compareTo(Person o) {
        return Integer.compare(this.age, o.age);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "P[", "]").add("a=" + age).add("n='" + name + "'").toString();
    }
}
