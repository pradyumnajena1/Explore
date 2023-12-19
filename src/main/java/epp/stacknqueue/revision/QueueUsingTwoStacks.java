package epp.stacknqueue.revision;

import java.util.Stack;

public class QueueUsingTwoStacks<T> {
    private Stack<T> stackA;
    private Stack<T> stackB;
   private  int size;
    public QueueUsingTwoStacks() {
        stackA = new Stack<>();
        stackB = new Stack<>();
    }

    public void enqueue(T value){
        stackA.push(value);
         size++;
    }
    public T dequeue(){
        if(size==0){
            throw new IllegalStateException("Empty queue");
        }
        if(stackB.isEmpty()){
            while (!stackA.isEmpty()){
                stackB.push(stackA.pop());
            }

        }
        T pop = stackB.pop();
        size--;
        return pop;
    }

    public static void main(String[] args) {
        QueueUsingTwoStacks<Integer> queueUsingTwoStacks = new QueueUsingTwoStacks<>();
        queueUsingTwoStacks.enqueue(1);
        queueUsingTwoStacks.enqueue(2);
        queueUsingTwoStacks.enqueue(3);
        System.out.println(queueUsingTwoStacks.dequeue());
        queueUsingTwoStacks.enqueue(4);
        System.out.println(queueUsingTwoStacks.dequeue());
    }
}
