package guidetocompetitiveprogramming;

public class WeirdAlgo {
    public static void main(String[] args) {
        weirdAlgo(3);
        weirdAlgo(138367);
    }
    static void weirdAlgo(long n){
        while (n!=1){
            System.out.print(n+" ");
            n= n%2==1?3*n+1:n/2;
        }
        System.out.println(1);

    }
}
