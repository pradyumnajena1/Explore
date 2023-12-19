package epp.binarysearchtree;

import epp.array.ArrayUtils;
import epp.list.DoubleLinkedListNode;

public class DoubleLinkListToBST {
    public static void main(String[] args) {
        int[] sortedArray = ArrayUtils.randomSortedArray(10, 1, 20);
        ArrayUtils.printArray(sortedArray);
        DoubleLinkedListNode<Integer> list = getList(sortedArray);
        System.out.println(list);

        DoubleLinkedListNode<Integer> root  = createTree2(list);

        printTreeInOrder(root);
    }

    private static void printTreeInOrder(DoubleLinkedListNode<Integer> root) {
        if(root==null){
            return;
        }
        printTreeInOrder(root.prev);
        System.out.print(" "+ root.data);
        printTreeInOrder(root.next);
    }

    private static DoubleLinkedListNode<Integer> createTree2(DoubleLinkedListNode<Integer> list) {
        DoubleLinkedListNode<Integer>[] headHolder  = new DoubleLinkedListNode[1];
        headHolder[0] = list;
        int length = getLength(list);
        System.out.println(length);
        DoubleLinkedListNode<Integer> root = createTree2(headHolder,list,0,length);
        return root;
    }

    private static int getLength(DoubleLinkedListNode<Integer> list) {
        int count=0;
        DoubleLinkedListNode<Integer> current = list;
        while (current!=null){
            count++;
            current=current.next;
        }
        return count;
    }

    private static DoubleLinkedListNode<Integer> createTree2(DoubleLinkedListNode<Integer>[] headHolder,
                                                             DoubleLinkedListNode<Integer> list, int start, int end) {

        //System.out.println( start+" "+end+" data->"+ headHolder[0].data);
        if(start>=end){
            return null;
        }

        int mid = (start+end)/2;
        DoubleLinkedListNode<Integer> leftTree =   createTree2(headHolder,list,start,mid);

        DoubleLinkedListNode<Integer> root = headHolder[0];
        headHolder[0] = headHolder[0].next;

        root.prev = leftTree;
        root.next = createTree2(headHolder,list,mid+1,end);
        return root;
    }





    private static DoubleLinkedListNode<Integer> getList(int[] sortedArray) {
        DoubleLinkedListNode<Integer> head=null;
        DoubleLinkedListNode<Integer> tail=null;
        for(int value:sortedArray){
            DoubleLinkedListNode<Integer> newNode = new DoubleLinkedListNode<>(value,null,null);
            if(head == null){
                 head=tail = newNode;

            }else{
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            }
        }


        return head;
    }
}
