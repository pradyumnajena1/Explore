package org.example;

public class DoubleLinkList {
    private DoubleLinkListNode sentinel;
    private int size;

    public DoubleLinkList(){
        sentinel = new DoubleLinkListNode();
        sentinel.next = sentinel;
        sentinel.prev= sentinel;
        size=0;
    }
    public void prependItem(int item){
        insertAfter(sentinel,item);
        size++;
    }

    public void appendItem(int item){
        insertBefore(sentinel,item);
        size++;
    }
    public int size(){
        return size;
    }

    private void insertAfter(DoubleLinkListNode node,int item  ){
        DoubleLinkListNode newNode = new DoubleLinkListNode();
        newNode.data= item;
        linkup( node,newNode, node.next);
    }
    private void insertBefore(DoubleLinkListNode node,int item  ){
        DoubleLinkListNode newNode = new DoubleLinkListNode();
        newNode.data= item;
        linkup( node.prev,newNode, node);
    }

    private static void linkup( DoubleLinkListNode prevNode,DoubleLinkListNode newNode, DoubleLinkListNode nextNode) {
        newNode.next = nextNode;
        nextNode.prev = newNode;

        prevNode.next = newNode;
        newNode.prev = prevNode;
    }

    public void print(){
        DoubleLinkListNode current = sentinel.next;
        while (current!= sentinel){
            System.out.print(current.data+" ");
            current=current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DoubleLinkList list = new DoubleLinkList();
        list.print();

        list.appendItem(1);
        list.appendItem(2);
        list.print();
        list.appendItem(3);
        list.appendItem(4);
        list.appendItem(5);
        list.print();
        System.out.println(list.size());

    }
}
