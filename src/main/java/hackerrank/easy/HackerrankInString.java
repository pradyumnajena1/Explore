package hackerrank.easy;

public class HackerrankInString {
    public static void main(String[] args) {
        System.out.println(hackerrankInString("hhaacckkerrrrannkk"));
    }
    public static String hackerrankInString(String s) {
        // Write your code here
          if(isSubsequence(s,"hackerrank")){
              return "YES";
          }
          return "NO";
    }

    private static boolean isSubsequence(String s, String subsequnce) {
        if(subsequnce.length()==0){
            return true;
        }
        if(s.length()==0){
            return false;
        }
        if(s.charAt(0)==subsequnce.charAt(0)){
            return isSubsequence(s.substring(1),subsequnce.substring(1));
        }

        return isSubsequence(s.substring(1),subsequnce);
    }
}
