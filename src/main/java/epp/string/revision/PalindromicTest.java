package epp.string.revision;

public class PalindromicTest {

  public static void main(String[] args) {
    boolean isPalindrome = isPalindrome("A man, a plan, a canal, Panama.");
    System.out.println(isPalindrome);
  }

  private static boolean isPalindrome(String s) {
    int i = 0;
    int j = s.length() - 1;
    while (i < j) {
      while (i < j && !Character.isLetterOrDigit(s.charAt(i))) {
        i++;
      }

      while (i < j && !Character.isLetterOrDigit(s.charAt(j))) {
        j--;
      }
      if(Character.toLowerCase(s.charAt(i++))!=Character.toLowerCase(s.charAt(j--))){
        return false;
      }
    }
    return true;
  }
}
