package epp.array;

import java.util.Arrays;

public class PartitionArray {

    public static void main(String[] args) {
        int[] array = ArrayUtils.randomArray(20,1,10);
        System.out.println(Arrays.toString(array));

        partition(array,2);
        System.out.println(Arrays.toString(array));

    }

    public static void partition(int[] array,int index){

        if(index<0 || index>= array.length){
            throw new IllegalArgumentException("Invalid pivot index! "+index);
        }

        int smaller = 0;
        int equal =0;
        int bigger = array.length-1;
        int pivot = array[index];



        while (equal<=bigger){

            if(array[equal]<pivot){
                ArrayUtils.swap(array,equal,smaller);
                smaller++;
                equal++;
            }else if(array[equal]==pivot){

                equal++;
            }else {
                ArrayUtils.swap(array,equal,bigger);
                bigger--;

            }


        }

    }

}
