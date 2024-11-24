package clr.heaps;

import epp.array.ArrayUtils;

import java.util.Arrays;
import java.util.Map;

public class MaxHeap<T extends Comparable<T>> {
    private T[] elements;
    private int size;

    public MaxHeap(int capacity) {
        elements = (T[]) new Comparable[capacity+1];
        size = 0;
    }

    public MaxHeap(T[] elements) {
        this.elements = (T[]) new Comparable[elements.length+1];
        size = elements.length;
        System.arraycopy(elements, 0,this.elements,1 ,elements.length);
        buildMaxHeap();
    }

    private void buildMaxHeap() {
        for(int i=elements.length/2; i>=1; i--){
            maxHeapifyDown(i);
        }
    }

    public void offer(T item){
        ensureCapacity(size+1);
        elements[++size] = item;
        maxHeapifyUp(size);
    }
    public T poll(){
        if(size==0){
            throw new IllegalStateException("Heap is empty");
        }
        T max = elements[1];
        elements[1] = elements[size];
        size--;
        maxHeapifyDown(1);
        return max;
    }
    public boolean isEmpty(){
        return size==0;
    }
    public T peek(){
        if(size==0){
            throw new IllegalStateException("Heap is empty");
        }
        return elements[1];
    }

    private void maxHeapifyDown(int index) {
        int leftChild = 2*index;
        int rightChild = 2*index+1;
        int largest = index;
        if(leftChild<=size && elements[leftChild].compareTo(elements[largest])>0){
            largest = leftChild;
        }
        if(rightChild<=size && elements[rightChild].compareTo(elements[largest])>0){
            largest = rightChild;
        }
        if(largest!=index){
            ArrayUtils.swap(elements, index, largest);
            maxHeapifyDown(largest);
        }


    }
    public    void maxHeapIncreaseKey(int index,T value) {
        if(index>size){
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        if(elements[index].compareTo(value)>0){
            throw new IllegalArgumentException("New value is smaller than current value");
        }
        elements[index] = value;
        maxHeapifyUp(index);
    }

    public    void maxHeapDeleteKey(int index ) {
        if(index>size){
            throw new IndexOutOfBoundsException("Index is out of bounds");
        }
        T value = elements[index];
        elements[index] = elements[size];
        size--;
        maxHeapifyDown(index);

    }

    private void maxHeapifyUp(int index) {
        while(index>1 && elements[index/2].compareTo(elements[index])<0){
            ArrayUtils.swap(elements, index,index/2);
            index = index/2;
        }
    }

    private void ensureCapacity(int capacity) {
        if(elements.length<=capacity){
            elements = Arrays.copyOf(elements, capacity*2);

        }
    }

    public static void main(String[] args){

      MaxHeap<Integer> maxHeap = new MaxHeap<>(new Integer[]{1,2,3,4,5,5});
        System.out.println(maxHeap.poll());
        System.out.println(maxHeap.poll());
        System.out.println(maxHeap.poll());
    }

}
