package epp.array.revision;

import epp.array.ArrayUtils;

public class NextPermutation {
    public static void main(String[] args) {
        ArrayUtils.printArray(getNextPerm(new int[]{2,3,1,0}));
        ArrayUtils.printArray(getNextPerm(new int[]{3,2,1,0}));
        ArrayUtils.printArray(getNextPerm(new int[]{3,2,0,1}));
    }

    private static int[] getNextPerm(int[] perm) {
        int index = perm.length-1;
        while (index>0 && perm[index]<perm[index-1]){
            index--;
        }
        if(index==0){
            return perm;
        }
        ArrayUtils.swap(perm,index,index-1);
        ArrayUtils.reverse(perm,index+1,perm.length-1);
        return perm;
    }
}
