package cph;

public class FibonacciNumbers {
    public static void main(String[] args) {
        System.out.println(getFibonacciTerm(30));
        System.out.println(getFibonacciTerm2(30));
    }
    static int getFibonacciTerm(int n){
        if(n<=1){
            return n;
        }

        int prev = 0;
        int cur = 1;
        for(int i=2;i<=n;i++){
            int next = prev+cur;
            prev = cur;
            cur = next;
        }
        return cur;
    }
    static int getFibonacciTerm2(int n){
         return (int) ((Math.pow ((1+Math.sqrt(5)),n) - Math.pow ((1-Math.sqrt(5)),n))/ (Math.pow(2,n)*Math.sqrt(5)));
    }
}
