package epp.list;

import java.util.Arrays;
import java.util.List;

public class LinkedList<T> {

    private LinkedListNode<T> head;
    private LinkedListNode<T> tail;
    private int size = 0;

    public void addNodeAtEnd(T data){
        LinkedListNode<T> newNode = new LinkedListNode<>(data);

        if(isEmpty()){
            head = tail = newNode;
        }else{
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }
    public void addNodeAtEnd(LinkedListNode<T> newNode){

        newNode.next = null;
        if(isEmpty()){
            head = tail = newNode;
        }else{
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }
    public void addNodeAtFront(T data){
        LinkedListNode<T> newNode = new LinkedListNode<>(data);

        if(isEmpty()){
            head = tail = newNode;
        }else{
            newNode.next = head;

            head = newNode;
        }
        size++;
    }

    public void addNodeAtFront(LinkedListNode<T> newNode){
         newNode.next=null;

        if(isEmpty()){
            head = tail = newNode;
        }else{
            newNode.next = head;

            head = newNode;
        }
        size++;
    }

    public LinkedListNode<T> removeNodeFromFront(){

        if(isEmpty()){
            throw new IllegalArgumentException("Empty list!");
        }

        LinkedListNode<T> deletedNode = head;

        if(size==1){
           head= tail = null;
        }else{
            head = head.next;
        }
        deletedNode.next=null;
        size--;
        return deletedNode;
    }

    public T peek(){
        if(isEmpty()){
            throw new IllegalStateException("list is empty");
        }
        return head.data;
    }
    public String toString(){

        return head.toString();
    }

    public String   toString(LinkedListNode<T> linkedListNode) {
        StringBuilder sb = new StringBuilder();
        LinkedListNode<T> current = linkedListNode;
        while (current!=null){

            sb.append(current.data);
            sb.append("->");
            current = current.next;
        }
        return sb.toString();
    }

    public int getSize(){
        return size;
    }
    public boolean isEmpty(){
        return size==0;
    }

    public static<T> LinkedList<T>[] split(LinkedList<T> list){
        LinkedList<T> left = new LinkedList<>();
        LinkedList<T> right = new LinkedList<>();
        int maxSize = list.size/2;
        for(int i=0;i<maxSize;i++){
            left.addNodeAtEnd(list.removeNodeFromFront());
        }


        while (!list.isEmpty()){

            right.addNodeAtEnd(list.removeNodeFromFront());
        }
        return new LinkedList[]{left,right};

    }
    public static<T> LinkedList<T> reverse(LinkedList<T> list){
        LinkedList<T> reversed = new LinkedList<>();
        while(!list.isEmpty()){
            reversed.addNodeAtFront(list.removeNodeFromFront());
        }
        return reversed;

    }
    public static <T> LinkedList<T> mergeLists(LinkedList<T> left,LinkedList<T> right){
        LinkedList<T> mergedList = new LinkedList<>();
        while (!left.isEmpty()&& !right.isEmpty()){
            mergedList.addNodeAtEnd(left.removeNodeFromFront());
            mergedList.addNodeAtEnd(right.removeNodeFromFront());
        }
        while (!left.isEmpty()){
            mergedList.addNodeAtEnd(left.removeNodeFromFront());
        }

        while (!right.isEmpty()){
            mergedList.addNodeAtEnd(right.removeNodeFromFront());
        }
        return mergedList;
    }

    public static <T> LinkedList<T> zipList(LinkedList<T> list){
        LinkedList<T>[] split = LinkedList.split(list);

        LinkedList<T> reverse = LinkedList.reverse(split[1]);

        LinkedList<T> mergedList = LinkedList.mergeLists(split[0], reverse);
        return mergedList;
    }
    public static <T>  LinkedList<T> createList(List<T> values){
        LinkedList<T> list = new LinkedList<>();
        for(T value:values){
            list.addNodeAtEnd(value);
        }
        return list;
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = LinkedList.createList(Arrays.asList(1,2,3,4,5));

        System.out.println(list);

        LinkedList<Integer> mergedList = LinkedList.zipList(list );
        System.out.println(mergedList);

    }
}
