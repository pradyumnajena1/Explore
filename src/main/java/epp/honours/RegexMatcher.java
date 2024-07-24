package epp.honours;

public class RegexMatcher {

  public static void main(String[] args) {
    String regex = "p.ad*y.u*m.a";
    String s = "pradddddyumna";

    boolean isMatching = isMatching(regex, s);
    System.out.println(isMatching);
  }

  private static boolean isMatching(String regex, String s) {
    // case 1
    if (regex.startsWith("^")) {
      return isMatchingHere(regex.substring(0), s);
    }
    for (int i = 0; i < s.length(); i++) {
      if (isMatchingHere(regex, s.substring(i))) {
        return true;
      }
    }
    return false;
  }

  private static boolean isMatchingHere(String regex, String s) {
    if (regex.length() == 0) {
      return true;
    }
    // case 2
    if (regex.equals("$")) {
      return s.isEmpty();
    }
    //case 3 regex begins with a* or .*
    if (regex.length() >= 2 && regex.charAt(1) == '*') {
      for(int i=0;i<s.length() && (regex.charAt(0) == '.' || regex.charAt(0) == s.charAt(i)) ;i++) {
        if(isMatchingHere(regex.substring(2), s.substring(i+1))){
          return true;
        }
      }
      return isMatchingHere(regex.substring( 2), s);
    }
    //case 4 , regex begins with alphanum
    return !s.isEmpty()
        && (regex.charAt(0) == '.' || regex.charAt(0) == s.charAt(0))
        && isMatchingHere(regex.substring(1), s.substring(1));
  }
}
