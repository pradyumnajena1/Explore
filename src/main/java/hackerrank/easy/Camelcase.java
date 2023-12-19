package hackerrank.easy;

public class Camelcase {
    public static void main(String[] args) {
        System.out.println(camelcase("saveChangesTheWorld"));
    }

    public static int camelcase(String s) {
        // Write your code here
        if(s.length()==0){
            return 0;
        }
         int count=1;
         for(int i=1;i<s.length();i++){
             if(s.charAt(i) >=65 && s.charAt(i)<=90){
                 count++;
             }
         }
         return count;
    }
}
