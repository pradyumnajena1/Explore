package tlap.chapter1;

public class Problem1 {
    public static void main(String[] args) {

        printDiamond(18);
        printXShape(18);
    }

    public static void printXShape(int numLines){
        for(int i = 1; i<= numLines/2; i++){
            int numHashes = 2*i;
            int numSpaces = (2* (numLines/2) -numHashes)/2;
            printXLine(numSpaces,numHashes);
        }
        for(int i = 1; i<= numLines/2; i++){
            int numHashes = 2* (numLines/2) -2*(i-1);
            int numSpaces = i-1;
            printXLine(numSpaces,numHashes);
        }
    }

    private static void printXLine(int numSpaces, int numHashes) {
        printChars(numHashes/2, ' ');

        printChars(numHashes/2, '#');
        printChars(numSpaces*4, ' ');
        printChars(numHashes/2, '#');
        System.out.println();
    }

    public static void printDiamond(int numLines) {
        for(int i = 1; i<= numLines/2; i++){
            int numHashes = 2*i;
            int numSpaces = (2* (numLines/2) -numHashes)/2;

            printDiamondLine(numSpaces,numHashes);
        }
        for(int i = 1; i<= numLines/2; i++){
            int numHashes = 2* (numLines/2) -2*(i-1);
            int numSpaces = i-1;
            printDiamondLine(numSpaces,numHashes);
        }
    }

    private static void printDiamondLine(int numSpaces, int numHashes) {
        printChars(numSpaces, ' ');
        printChars(numHashes, '#');
        System.out.println();
    }

    private static void printChars(int numChars, Character space) {
        for (int i = 0; i < numChars; i++) {
            System.out.print(space);
        }
    }
}
