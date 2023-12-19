package epp.list;

public class DoubleLinkedListNode<T> {
   public T data;
   public DoubleLinkedListNode<T> next;
  public DoubleLinkedListNode<T> prev;

    public DoubleLinkedListNode(T data) {
        this.data = data;
    }

    public DoubleLinkedListNode(T data, DoubleLinkedListNode<T> next, DoubleLinkedListNode<T> prev) {
        this.data = data;
        this.next = next;
        this.prev = prev;
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


}
