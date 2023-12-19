package epp.binarysearchtree;

import epp.array.ArrayUtils;

public class MinDistanceInThreeArrays {
    public static void main(String[] args) {
        int[] a = ArrayUtils.randomSortedArray(10,1,20);
        int[] b = ArrayUtils.randomSortedArray(20,3,40);
        int[] c = ArrayUtils.randomSortedArray(10,6,30);
        ArrayUtils.printArray(a);
        ArrayUtils.printArray(b);
        ArrayUtils.printArray(c);
        int minDistance = findMinDistance(a,b,c);
        System.out.println(minDistance);
        System.out.println(findMinDistance2(a,b,c));
    }
    private static int findMinDistance2(int[] a, int[] b, int[] c){
        int minDistance = Integer.MAX_VALUE;
        for(int i=0;i<a.length;i++){
           int smallB =  getEqualOrSmaller(b,a[i]);
           int smallC =  getEqualOrSmaller(c,a[i]);
            System.out.println(a[i]+" "+smallB+" "+smallC);
           minDistance = Math.min(minDistance,getDistance(a[i],smallB,smallC));


            int bigB =  getEqualOrBigger(b,a[i]);
            int bigC =  getEqualOrBigger(c,a[i]);
            System.out.println(a[i]+" "+bigB+" "+bigC);
            minDistance = Math.min(minDistance,getDistance(a[i],bigB,bigC));

        }
        return minDistance;

    }

    private static int getEqualOrSmaller(int[] array, int value) {
        int low = 0;
        int high = array.length;
        Integer equalOrSmaller = array[0]-1;
        while (low<=high){
            int mid = low + (high-low)/2;
            if(array[mid]==value){
                return value;
            }else if(array[mid]>value){

                high = mid-1;
            }else{
                equalOrSmaller = array[mid];
                low = mid+1;
            }
        }
        return equalOrSmaller;
    }

    private static int getEqualOrBigger(int[] array, int value) {
        int low = 0;
        int high = array.length;
        Integer equalOrBigger = array[array.length-1]+1;
        while (low<=high){
            int mid = low + (high-low)/2;
            if(array[mid]==value){
                return value;
            }else if(array[mid]>value){
                equalOrBigger = array[mid];
                high = mid-1;
            }else{
                low = mid+1;
            }
        }
        return equalOrBigger;
    }

    private static int findMinDistance(int[] a, int[] b, int[] c) {
        int ap = 0;
        int bp = 0;
        int cp = 0;
        int minDistance = Integer.MAX_VALUE;
        while (ap<a.length && bp<b.length&&cp<c.length){
            int distance = getDistance(a[ap], b[bp], c[cp]);
            minDistance = Math.min(distance,minDistance);
            if(distance==minDistance){
                System.out.println(a[ap]+" "+b[bp]+" "+c[cp]);
            }

            int min = Math.min(a[ap],Math.min(b[bp],c[cp]));
            if(min==a[ap]){
                ap++;
            }else if(min == b[bp]){
                bp++;
            }else {
                cp++;
            }
        }
        return minDistance;
    }

    private static int getDistance(int a, int b, int c) {
        return Math.abs(a - b) + Math.abs(b - c) + Math.abs(a - c);
    }
}
