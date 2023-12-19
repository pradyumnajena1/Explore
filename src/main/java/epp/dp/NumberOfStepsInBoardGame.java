package epp.dp;

public class NumberOfStepsInBoardGame {
    public static void main(String[] args) {
        int numSteps = getNumStepsInBoardGame(4,2);
        System.out.println(numSteps);
    }

    private static int getNumStepsInBoardGame(int n, int k) {
        if(n<0){
            return 0;
        }
        if(n==0){
            return 1;
        }
        int numways = 0;
         for(int i=1;i<=k;i++){
             numways+=getNumStepsInBoardGame(n-i,k);
         }
        return numways;
    }
}
