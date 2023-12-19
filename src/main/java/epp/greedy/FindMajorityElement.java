package epp.greedy;

import epp.array.ArrayUtils;

public class FindMajorityElement {
    public static void main(String[] args) {
        int[] values  = new int[]{1,4,5,2,1,5,6,7,3,9,8,8,8,8,8,8,8,8,8,8};
        ArrayUtils.shuffle(values);
        ArrayUtils.printArray(values);
       int x =  findMajorityItem(values);
        System.out.println(x  );
    }

    private static int findMajorityItem(int[] values) {
        int last = values[0] ;
        int count = 1;
        for(int i=1;i<values.length;i++){
            if(values[i]==last){
                count++;
            }else{
                count--;
                if(count==0){
                    count=1;
                    last = values[i];
                }
            }
        }
        return last;
    }

}
