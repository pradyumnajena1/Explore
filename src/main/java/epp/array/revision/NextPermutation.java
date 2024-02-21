package epp.array.revision;

import epp.array.ArrayUtils;

public class NextPermutation {
    public static void main(String[] args) {
        ArrayUtils.printArray(getNextPerm(new int[]{2,3,1,0}));
        ArrayUtils.printArray(getNextPerm(new int[]{3,2,1,0}));
        ArrayUtils.printArray(getNextPerm(new int[]{3,2,1,4}));
        ArrayUtils.printArray(getNextPerm(new int[]{3,2,4,3}));
    }

    private static int[] getNextPerm(int[] perm) {
        int index = perm.length-1;
        while (index>0 && perm[index]<perm[index-1]){
            index--;
        }
        if(index==0){
            return perm;
        }
        //find the next biggest digit
         int j=index;
         while (j<perm.length && perm[j]>perm[index-1]){
             j++;
         }
        ArrayUtils.swap(perm,j-1,index-1);
        ArrayUtils.reverse(perm,index,perm.length-1);
        return perm;
    }
}
