package hackerrank.easy;

import java.math.BigInteger;

public class ExtraLongFactorials {
    public static void main(String[] args) {
        System.out.println(extraLongFactorialsHelper(30));
    }
    public static void extraLongFactorials(int n) {
        // Write your code here
      BigInteger bigInteger =  extraLongFactorialsHelper(n);
        System.out.println(bigInteger.toString(10));
    }

    private static BigInteger extraLongFactorialsHelper(int n) {
        if(n==1){
            return BigInteger.ONE;
        }
        BigInteger multiply = extraLongFactorialsHelper(n - 1).multiply(BigInteger.valueOf(n));
        return multiply;
    }

}
