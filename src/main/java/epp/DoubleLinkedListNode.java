package epp;

import java.util.StringJoiner;

public class DoubleLinkedListNode<T> {
      private T data;

     private DoubleLinkedListNode<T> prev;
     private DoubleLinkedListNode<T> next;

    public DoubleLinkedListNode(T data) {
        this.data = data;

    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("NULL<-");
     DoubleLinkedListNode<T> current = this;
        while (current!=null){
            sb.append(current.data);
            sb.append(current.next==null?"->": "<=>");
            current = current.next;
        }
        sb.append("NULL");

        return sb.toString();
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public DoubleLinkedListNode<T> getPrev() {
        return prev;
    }

    public void setPrev(DoubleLinkedListNode<T> prev) {
        this.prev = prev;
    }

    public DoubleLinkedListNode<T> getNext() {
        return next;
    }

    public void setNext(DoubleLinkedListNode<T> next) {
        this.next = next;
    }
}
