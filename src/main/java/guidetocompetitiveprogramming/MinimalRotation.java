package guidetocompetitiveprogramming;

import epp.Pair;

public class MinimalRotation {
    public static void main(String[] args) {
        String s = "ABABA";
       String min =  getMinimalRotation(s);
        System.out.println(min);
    }

    private static String getMinimalRotation(String s) {
        String ss = s+s;
        RollingHash rollingHash = new RollingHash(ss);
        Pair<Integer,Integer> min = new Pair<>(0,s.length()-1);
        for(int i=1;i<s.length();i++){
            min = getMin(min, new Pair<>(i, i + s.length() - 1),rollingHash,ss);
        }
        return ss.substring(min.getFirst(), min.getSecond()+1);
    }

    private static Pair<Integer, Integer> getMin(Pair<Integer, Integer> a, Pair<Integer, Integer> b, RollingHash rollingHash, String ss) {
        System.out.println(ss.substring(a.getFirst(),a.getSecond()+1) +" "+ss.substring(b.getFirst(),b.getSecond()+1));


         int low = 0;
         int high = a.getSecond()-a.getFirst();
         int prefixLength=0;
         while (low<=high){
             int mid = (low+high)/2;
             if(rollingHash.getHash(a.getFirst(),a.getFirst()+mid) == rollingHash.getHash(b.getFirst(),b.getFirst()+mid)){
                 prefixLength=mid;
                 low = mid+1;
             }else{
                 high = mid-1;
             }
         }
        System.out.println(prefixLength);
         if(prefixLength==a.getSecond()-a.getFirst()+1){
             return a;
         }
         if(ss.charAt(a.getFirst() + prefixLength ) < ss.charAt(b.getFirst() + prefixLength )){
             return a;
         }else{
             return b;
         }
    }
}
