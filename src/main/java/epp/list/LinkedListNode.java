package epp.list;

import epp.list.revision.TestForCircularity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LinkedListNode<T> {
     public T data;
    public   LinkedListNode<T> next;

    public   LinkedListNode(T data, LinkedListNode<T> next) {
        this.data = data;
        this.next = next;
    }

    public   LinkedListNode(T data) {
        this.data = data;
    }
   public    static <T> int getLength(LinkedListNode<T> list1) {
        int size=0;
        LinkedListNode<T> current = list1;
        while (current!=null){
            current=current.next;
            size++;
        }
        return size;
    }

    public static <T> LinkedListNode<T>[] splitInHalf(LinkedListNode<T> list){
        LinkedListNode<T> fastPtr = list;
        LinkedListNode<T> slowPtr = list;

        while (fastPtr.next!=null){
            fastPtr = fastPtr.next;
            if(fastPtr.next!=null){
                slowPtr = slowPtr.next;
                fastPtr = fastPtr.next;
            }
        }


        LinkedListNode<T> temp = slowPtr.next;
        slowPtr.next = null;
        LinkedListNode<T> first = list;
        LinkedListNode<T> second = temp;
        return new LinkedListNode[]{first,second};
    }
   public static <T> LinkedListNode<T> getNode(LinkedListNode<T> list, T data) {
        LinkedListNode<T> current = list;
        while (current != null && !current.data .equals( data)) {
            current = current.next;
        }
        return current;
    }

    public String   toString( ) {
        LinkedListNode<T> startOfCircle = TestForCircularity.findStartOfCircle(this);
        if(startOfCircle==null){
            return toStringNonCircle(this);
        }else{
            return toStringWithCircle(this,startOfCircle);
        }


      }

    private String toStringWithCircle(LinkedListNode<T> head, LinkedListNode<T> startOfCircle) {
        StringBuilder sb = new StringBuilder();
        sb.append("LinkedList[");

        StringBuilder nodesString = new StringBuilder();
        LinkedListNode<T> current = head;
        int count =0;
        while (current!=startOfCircle){

            nodesString.append(current.data);
            nodesString.append("->");
            current = current.next;
            count++;
        }
        nodesString.append(current.data);
        nodesString.append("(start of circle)->");
        current = current.next;

        while (current!=startOfCircle){

            nodesString.append(current.data);
            nodesString.append("->");
            current = current.next;
            count++;
        }
        nodesString.append("(end of circle)");

        sb.append( "size = "+count+" ");
        sb.append(nodesString);
        sb.append("]");
        return sb.toString();
    }

    private static <T> String toStringNonCircle(LinkedListNode<T> head) {
        StringBuilder sb = new StringBuilder();
        sb.append("LinkedList[");

        StringBuilder nodesString = new StringBuilder();
        LinkedListNode<T> current = head;
        int count =0;
        while (current!=null){

            nodesString.append(current.data);
            nodesString.append("->");
            current = current.next;
            count++;
        }
        nodesString.append("NULL");

        sb.append( "size = "+count+" ");
        sb.append(nodesString);
        sb.append("]");
        return sb.toString();
    }

    public static <T> LinkedListNode<T> createList(  T... values){
        return createList(Arrays.asList(values));
    }
    public static <T> LinkedListNode<T> createList(List<T> values){
        LinkedListNode<T> head = null;
        LinkedListNode<T> current=null;
        LinkedListNode<T> prev = null;

        for(int i=0;i<values.size();i++){
            current = new LinkedListNode<T>(values.get(i));
            if(head==null){
                head =prev= current;
            }else{
                prev.next = current;
                prev = current;
            }
        }
        return head;
    }

}
