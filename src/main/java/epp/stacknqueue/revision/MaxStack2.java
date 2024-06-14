package epp.stacknqueue.revision;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class MaxStack2<T extends Comparable<T>> {
    private Deque<T> values;
    private Deque<T> maxValues;
    public MaxStack2(){
    values = new LinkedList<>();
    maxValues = new LinkedList<>();
    }
    public void push(T data){
        if(isEmpty()){
            values.push(data);
            maxValues.push(data);
        }else{
            T currentMax = maxValues.peek();
            if(data.compareTo(currentMax)>=0){
                values.push(data);
                maxValues.push(data);
            }else{
                values.push(data);
            }
        }
    }

    public T pop(){
        if(isEmpty()){
            throw new IllegalStateException("empty stack");
        }else{
            T pop = values.pop();
            if(pop.compareTo(maxValues.peek())==0){
                maxValues.pop();
            }
            return pop;
        }
    }

    public boolean isEmpty() {
        return values.isEmpty();
    }

    public T max(){
      return   maxValues.peek();
    }

    public static void main(String[] args) {
        MaxStack2<Integer> maxStack = new MaxStack2<>();
        maxStack.push(3);
        maxStack.push(1);
        maxStack.push(4);
        System.out.println(maxStack.max());
        maxStack.pop();
        System.out.println(maxStack.max());

    }

}
