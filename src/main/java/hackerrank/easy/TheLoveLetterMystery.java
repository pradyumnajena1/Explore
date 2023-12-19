package hackerrank.easy;

public class TheLoveLetterMystery {
    public static void main(String[] args) {
        System.out.println(theLoveLetterMystery("abc"));
        System.out.println(theLoveLetterMystery("abcba"));
        System.out.println(theLoveLetterMystery("abcd"));
        System.out.println(theLoveLetterMystery("cba"));
    }

    public static int theLoveLetterMystery(String s) {
        // Write your code here
        int cost = 0;
        int start = 0;
        int end  = s.length()-1;
        while (start<end){

            if(s.charAt(start)!=s.charAt(end)){
                cost+= Math.abs( s.charAt(start) - s.charAt(end));
            }
            start++;
            end--;
        }
        return cost;

    }
}
