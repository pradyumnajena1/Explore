package epp.string.revision;

import java.util.ArrayList;
import java.util.List;

public class ValidIPAddresses {

  public static void main(String[] args) {
    List<String> ips = getValidIpAddresses("19216811");
    System.out.println(ips);
  }

  private static List<String> getValidIpAddresses(String s) {
    List<String> result = new ArrayList<>();
    for (int i = 1; i < 4 && i < s.length(); i++) {
      String firstPart = s.substring(0, i);
      if (isValidPart(firstPart)) {
        for (int j = 1; j < 4 && i + j < s.length(); j++) {
          String secondPart = s.substring(i, i + j);
          if (isValidPart(secondPart)) {

            for (int k = 1; k < 4 && i + j + k < s.length(); k++) {
              String thirdPart = s.substring(i + j, i + j + k);
              String fourthPart = s.substring(i + j + k);
              if (isValidPart(thirdPart) && isValidPart(fourthPart)) {
                result.add(firstPart + "." + secondPart + "." + thirdPart + "." + fourthPart);
              }
            }
          }
        }
      }
    }
    return result;
  }

  private static boolean isValidPart(String part) {
    if (part.length() > 3) {
      return false;
    }
    if (part.startsWith("0") && part.length() > 1) {
      return false;
    }
    int value = Integer.parseInt(part);
    return value <= 255 && value >= 0;
  }
}
