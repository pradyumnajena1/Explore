package epp.list;

import java.util.Arrays;

public class ReverseSingleLinkedList {
    public static void main(String[] args) {
        LinkedList<Integer> list = LinkedList.createList(Arrays.asList(1,2,3,4));
        System.out.println(list);
        LinkedList<Integer> resultList = reverseList(list);
        System.out.println(resultList);
    }

    private static LinkedList<Integer> reverseList(LinkedList<Integer> list) {
        LinkedList<Integer> result   = new LinkedList<>();
        while (!list.isEmpty()){
            result.addNodeAtFront(list.removeNodeFromFront());
        }
        return result;
    }
}
