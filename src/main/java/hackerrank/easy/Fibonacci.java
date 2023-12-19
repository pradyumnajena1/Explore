package hackerrank.easy;

public class Fibonacci {
    public static void main(String[] args) {
        System.out.println(fib(100 ));
        System.out.println(fib_dynamic(100 ));
        System.out.println(fib_ultimate( 100 ));
    }
    static long fib(int n){
        long[] cache = new long[n+1];
        for(int i=0;i<=n;i++){
            cache[i] = -1;
        }
        cache[0] = 0;
        cache[1] = 1;
        for(int i=2;i<=n;i++){
            fibHelper(i,cache);
        }
        return cache[n];
    }

    private static long fibHelper(int n, long[] cache) {
        if(cache[n]!=-1){
            return cache[n];
        }
        cache[n] = fibHelper(n-1,cache)+fibHelper(n-2,cache);
        return cache[n];
    }
    public static long fib_ultimate(int n){
        if(n==0)return 0;
        if(n==1)return 1;
        long back1=1;
        long back2=0;
        for(int i=2;i<=n;i++){
            long next = back1+back2;
            back2=back1;
            back1=next;
        }
        return back1;
    }

    public static long fib_dynamic(int n){
        long[] values = new long[n+1];
        values[0]=0;
        values[1]=1;
        for(int i=2;i<=n;i++){
            values[i] = values[i-1]+values[i-2];
        }
        return values[n];
    }
}
