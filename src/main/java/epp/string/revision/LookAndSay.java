package epp.string.revision;

public class LookAndSay {
  public static void main(String[] args) {
    String s = lookAndSay(8);
    System.out.println(s);
  }

  private static String lookAndSay(int n) {
    String s = "1";
    for (int i = 1; i < n; i++) {
      s = nextNUmber(s);
    }
    return s;
  }

  private static String nextNUmber(String s) {
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < s.length(); i++) {
      int count = 1;
      while (i + 1 < s.length() && s.charAt(i) == s.charAt(i + 1)) {
        i++;
        count++;
      }
      sb.append(count);
      sb.append(s.charAt(i));
    }
    return sb.toString();
  }
}
