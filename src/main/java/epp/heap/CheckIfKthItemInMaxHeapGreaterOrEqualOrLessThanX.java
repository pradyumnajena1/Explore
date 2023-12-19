package epp.heap;

import epp.MutablePrimitive;

public class CheckIfKthItemInMaxHeapGreaterOrEqualOrLessThanX {
    public static void main(String[] args) {
        int[] maxHeapArray = new int[]{27,9,23,6,5,18,21};
        int compare = checkIfKthItemInMaxHeapGreaterOrEqualOrLessThanX(maxHeapArray,5,13);
        System.out.println(compare);
    }

    private static int checkIfKthItemInMaxHeapGreaterOrEqualOrLessThanX(int[] maxHeapArray, int k, int x) {
        MutablePrimitive<Integer> greater = new MutablePrimitive<>(0);
        MutablePrimitive<Integer> equal = new MutablePrimitive<>(0);
        int index = 0;
        preorderTraverse(maxHeapArray,k,x,index, greater,equal);
        System.out.println(greater.getValue());
        System.out.println(equal.getValue());


        return greater.getValue()>k?1:greater.getValue()+equal.getValue()>k?0:-1;
    }

    private static void preorderTraverse(int[] maxHeapArray, int k, int x, int index, MutablePrimitive<Integer> greater,
                                         MutablePrimitive<Integer> equal) {
        if(index>=maxHeapArray.length){
            return;
        }
        if(greater.getValue()+equal.getValue()>k){
            return;
        }
        if(maxHeapArray[index]<x){
            return;
        }

        if(maxHeapArray[index]>x){
            greater.setValue(greater.getValue()+1);
        }
        if(maxHeapArray[index]==x){
            equal.setValue(equal.getValue()+1);
        }
        preorderTraverse(maxHeapArray,k,x,index*2+1,greater,equal);
        preorderTraverse(maxHeapArray,k,x,index*2+2,greater,equal);

    }
}
