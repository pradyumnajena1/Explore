package epp.honours;

/**
 * Men numbered from1 to n are arranged in a circle in clockwise order. Every
 * fcth man is removed, until only one man remains. What is the number of the last
 * man
 */
public class JosephusProblem {

    public static void main(String[] args){
        int n = 5;
        int k = 3;
        int lastMan = findLastMan(n, k)+1;
        System.out.println("The last man is: " + lastMan);
    }

    private static int findLastMan(int n, int k) {
        if(n==1){
            return 0;
        }
        return (findLastMan(n-1,k)+k)%n;
    }
}
