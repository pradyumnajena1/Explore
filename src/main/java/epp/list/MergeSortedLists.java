package epp.list;

import java.util.Arrays;

public class MergeSortedLists {
    public static void main(String[] args) {
        LinkedList<Integer> list1 = LinkedList.createList(Arrays.asList(2,5,7));
        LinkedList<Integer> list2 = LinkedList.createList(Arrays.asList(3,11));
        LinkedList<Integer> mergedList =   mergeSortedLists(list1,list2);
        System.out.println(mergedList);
    }

    private static LinkedList<Integer> mergeSortedLists(LinkedList<Integer> list1, LinkedList<Integer> list2) {
        LinkedList<Integer> result = new LinkedList<>();
        while (!list1.isEmpty() && !list2.isEmpty()){
             if(list1.peek()<=list2.peek()){
                 result.addNodeAtEnd(list1.removeNodeFromFront());
             }else{
                 result.addNodeAtEnd(list2.removeNodeFromFront());
             }
        }
        while (!list1.isEmpty()){
            result.addNodeAtEnd(list1.removeNodeFromFront());
        }
        while (!list2.isEmpty()){
            result.addNodeAtEnd(list2.removeNodeFromFront());
        }
        return result;
    }
}
