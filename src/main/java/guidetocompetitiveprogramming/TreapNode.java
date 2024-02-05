package guidetocompetitiveprogramming;

public class TreapNode<T extends Comparable<T>> {
    T value;
    int priority;
    TreapNode<T> left, right;
    int size;

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
    public void updateSize(){
        size = 1 + (left==null?0: left.getSize())+(right==null?0:right.getSize());
    }
    public int getSize(){
        return size;
    }
}
