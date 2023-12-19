package epp.string.revision;

public class TestPalindrome {
    public static void main(String[] args) {
        System.out.println(isPalindrome("A man, a plan, a canal, Panama"));
        System.out.println(isPalindrome("Able was I,ere I saw elba!"));
    }

    private static boolean isPalindrome(String s) {
        if(s==null ){
            throw new IllegalArgumentException("s cant be null");
        }
        if(s.length()<2){
            return true;
        }
        int left = 0;
        int right = s.length()-1;
        while (left<right){
            while (!(Character.isAlphabetic(s.charAt(left )) || Character.isDigit(s.charAt(left ))) ){
                left++;
            }
            while (!(Character.isAlphabetic(s.charAt(right )) || Character.isDigit(s.charAt(right )) )){
                right--;
            }
            if(Character.toLowerCase( s.charAt(left))!=Character.toLowerCase(s.charAt(right))){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
