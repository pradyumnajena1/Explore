package epp;

public class MutablePrimitive<T extends Number> {
      private T value;

    public MutablePrimitive(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
