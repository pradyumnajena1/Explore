package epp;

import java.util.Objects;
import java.util.StringJoiner;

public class Triplet <T,U,V>{
    T first;
    U second;
    V third;

    public Triplet(T first, U second, V third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Triplet<?, ?, ?> triplet = (Triplet<?, ?, ?>) o;

        if (!Objects.equals(first, triplet.first)) return false;
        if (!Objects.equals(second, triplet.second)) return false;
        return Objects.equals(third, triplet.third);
    }

    @Override
    public int hashCode() {
        int result = first != null ? first.hashCode() : 0;
        result = 31 * result + (second != null ? second.hashCode() : 0);
        result = 31 * result + (third != null ? third.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Triplet.class.getSimpleName() + "[", "]")
                .add("f=" + first)
                .add("s=" + second)
                .add("t=" + third)
                .toString();
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public U getSecond() {
        return second;
    }

    public void setSecond(U second) {
        this.second = second;
    }

    public V getThird() {
        return third;
    }

    public void setThird(V third) {
        this.third = third;
    }
}
