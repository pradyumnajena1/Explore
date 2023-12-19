package hackerrank.easy;

public class SuperReducedString {
    public static void main(String[] args) {
        System.out.println(superReducedString("aab"));
        System.out.println(superReducedString("abba"));
        System.out.println(superReducedString("aaabccddd"));
    }
    public static String superReducedString(String s) {
        // Write your code here
        StringBuilder sb = new StringBuilder(s);
        Integer numChanges=null;
        while (numChanges==null||numChanges>0){
            numChanges=0;
            //System.out.println(sb.toString());
            for(int i=0;i<sb.length()-1;i++){
                if(sb.charAt(i)==sb.charAt(i+1)){
                    sb.deleteCharAt(i);
                    sb.deleteCharAt(i);
                    numChanges++;
                }
            }
        }
          return sb.length()>0? sb.toString():"Empty String";
    }
}
