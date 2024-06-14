package epp.string.revision;

public class RunLengthEncode {
  public static void main(String[] args) {
    String s = "aaaabbbcca";
    String runLengthEncoding = runLengthEncoding(s);
    System.out.println(runLengthEncoding);
    String decoded = runLengthDecoding(runLengthEncoding);
    assert s.equals(decoded);
    System.out.println(decoded);
  }

  private static String runLengthDecoding(String encoding) {
    StringBuilder sb = new StringBuilder();
    int count = 0;
    for (int i = 0; i < encoding.length(); i++) {
      if (Character.isDigit(encoding.charAt(i))) {
        count = count * 10 + encoding.charAt(i) - '0';
      } else {
        while (count > 0) {
          sb.append(encoding.charAt(i));
          count--;
        }
      }
    }
    return sb.toString();
  }

  private static String runLengthEncoding(String s) {
    StringBuilder sb = new StringBuilder();

    int count = 1;
    for (int i = 1; i <= s.length(); i++) {
      if(i==s.length() ||  s.charAt(i)!=s.charAt(i-1) ) {
        sb.append(count);
        sb.append(s.charAt(i-1));
        count=1;
      }else{
        count++;
      }
    }
    return sb.toString();
  }
}
