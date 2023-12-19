package epp.stacknqueue.revision;


import epp.array.ArrayUtils;

public class StackImpl<T> {
    public static void main(String[] args) {
        StackImpl<Integer> stack = new StackImpl<>(Integer.class, 10);
    }
    private T[] values;

    public StackImpl(Class<T> clazz, int size) {
        values = (T[]) ArrayUtils.createArray(clazz ,size);
    }
}
