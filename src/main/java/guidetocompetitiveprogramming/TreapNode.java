package guidetocompetitiveprogramming;

public class TreapNode<T extends Comparable<T>> {
    T value;
    int priority;
    TreapNode<T> left, right;

    public TreapNode(T value) {
        this.value = value;
        this.priority = getPriority();
    }

    public static int getPriority() {
        return (int) (Math.random() * 1000);
    }

    public TreapNode(T value, int priority) {
        this.value = value;
        this.priority = priority;
    }
}
