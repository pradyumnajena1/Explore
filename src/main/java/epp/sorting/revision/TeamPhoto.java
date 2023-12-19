package epp.sorting.revision;

import epp.array.ArrayUtils;

import java.util.Arrays;

public class TeamPhoto {
    public static void main(String[] args) {
        int[] teamFront = ArrayUtils.randomArray(10,2,15);
        int[] teamBack = ArrayUtils.randomArray(10,3,15);
       boolean possible =  checkTeamPhoto(teamFront,teamBack);
        System.out.println(possible);
    }

    private static boolean checkTeamPhoto(int[] teamFront, int[] teamBack) {
        Arrays.sort(teamFront);
        Arrays.sort(teamBack);
        ArrayUtils.printArray(teamBack);
        ArrayUtils.printArray(teamFront);
        int index = 0;

        while (index<teamFront.length  ){
            if(teamFront[index]>=teamBack[index]){
                return false;
            }
            index++;
        }
        return true;
    }
}
