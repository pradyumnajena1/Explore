package epp.dp;

public class HundredUnitInHundredRs {
    public static void main(String[] args) {
        boolean possible = getUnits(100,100);
        System.out.println(possible);
    }

    private static boolean getUnits(int amount, int numUnits) {
        for(int numCows=1;numCows<=10;numCows++){
            int cowCost = numCows * 10;
            int goatCost = amount - cowCost;
            int numGoats = goatCost * 8;
            System.out.println(numCows+" "+cowCost + " "+ numGoats + " "+goatCost );
            if( numCows + numGoats == numUnits && cowCost+goatCost==amount){
                System.out.println(numCows);
                return true;
            }
        }
        return false;
    }
}
