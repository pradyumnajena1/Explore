package epp.binarySearch.revision;

import epp.array.ArrayUtils;

public class FindWhereIndexEqualsValue {
    public static void main(String[] args) {
        int[] values =  new int[]{-2,0,2,3,6,7,9};
        ArrayUtils.printArray(values);
        int index = findWhereIndexEqualsValue(values);
        System.out.println(index);
    }

    public static int findWhereIndexEqualsValue(int[] values) {
        return findWhereIndexEqualsValue(values,0,values.length-1);
    }

    public static int findWhereIndexEqualsValue(int[] values, int start, int end) {
        Integer index = null;
        while (start<=end){
            int mid = start+(end-start)/2;
            if(values[mid]==mid){
                index = mid;
                break;
            }else if(values[mid]>mid){
                end=mid-1;
            }else {
                start=mid+1;
            }
        }
        return index!=null?index:-(start+1);
    }
}
