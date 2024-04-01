package amaze;

public class MinStack {
    public static void main(String[] args) {
        MinStackImpl minStack = new MinStackImpl();
        minStack.push(2);
        minStack.push(5);
        minStack.push(3);
        minStack.push(6);
        minStack.push(1);

        System.out.println(minStack.min());
        minStack.pop();
        System.out.println(minStack.min());
    }
}
