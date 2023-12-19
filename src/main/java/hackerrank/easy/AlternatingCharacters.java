package hackerrank.easy;

public class AlternatingCharacters {
    public static void main(String[] args) {
        System.out.println(alternatingCharacters("AAAA"));
        System.out.println(alternatingCharacters("BBBBB"));
        System.out.println(alternatingCharacters("ABABABAB"));
        System.out.println(alternatingCharacters("BABABA"));
        System.out.println(alternatingCharacters("AAABBB"));
    }

    public static int alternatingCharacters(String s) {
        // Write your code here
        int deleteCount=0;
        Character prev= null;
        for(int i=0;i<s.length();i++){
            if(prev==null||prev!=s.charAt(i)){
                prev = s.charAt(i);
            }else{
                deleteCount++;

            }
        }
        return deleteCount;
    }


}
