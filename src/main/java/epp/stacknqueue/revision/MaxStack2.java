package epp.stacknqueue.revision;

import java.util.Stack;

public class MaxStack2<T extends Comparable<T>> {
    private Stack<T> values;
    private Stack<T> maxValues;
    public MaxStack2(){
        values = new Stack<>();
        maxValues = new Stack<>();
    }
    public void push(T data){
        if(values.isEmpty()){
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
        if(values.isEmpty()){
            throw new IllegalStateException("empty stack");
        }else{
            T pop = values.pop();
            if(pop.compareTo(maxValues.peek())>=0){
                maxValues.pop();
            }
            return pop;
        }
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
