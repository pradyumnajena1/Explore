package epp.string.revision;

import java.util.HashMap;
import java.util.Map;

public class StringUtils {
  public static void main(String[] args) {
    System.out.println(convertToBase("120", 10, 16));
    System.out.println(spreadsheetColumnDecode("AA"));
    System.out.println(spreadsheetColumnDecode("ZZ"));
    System.out.println(spreadsheetColumnDecode("ZZZ"));
    System.out.println(spreadsheetColumnDecode("BB"));
    System.out.println(intToSpreadSheetColumn(54));
    System.out.println(intToSpreadSheetColumn(702));
    System.out.println(intToSpreadSheetColumn(18278));
  }

  public static int spreadsheetColumnDecode(String s) {
    int intValue = 0;
    for (int i = 0; i < s.length(); i++) {
      int digit = 1 + s.charAt(i) - 'A';
      intValue = intValue * 26 + digit;
    }
    return intValue;
  }

  public static String intToSpreadSheetColumn(int column) {
    StringBuilder sb = new StringBuilder();

    if(column<=0){
      return "0";
    }
    while (column != 0) {
      column = column-1;
      int reminder = column%26;
      char digit = (char) ( 'A' + reminder);
      sb.append(digit);
      column = column / 26;
    }
    ;
    sb.reverse();
    return sb.toString();
  }

  public static String convertToBase(String s, int b1, int b2) {
    boolean isNegative = s.startsWith("-");
    int valueAsInt = 0;
    for (int i = isNegative ? 1 : 0; i < s.length(); i++) {
      int digit = Character.isDigit(s.charAt(i)) ? (s.charAt(i) - '0') : (10 + s.charAt(i) - 'A');
      valueAsInt = valueAsInt * b1 + digit;
    }
    return (isNegative ? "-" : "") + (valueAsInt == 0 ? "0" : constructFromBase(valueAsInt, b2));
  }

  public static String constructFromBase(int valueAsInt, int b2) {
    if (valueAsInt == 0) {
      return "";
    }
    int reminder = valueAsInt % b2;
    char lastDigit = (char) (reminder >= 10 ? ('A' + reminder - 10) : (reminder + '0'));
    return constructFromBase(valueAsInt / b2, b2) + lastDigit;
  }

  public static int stringToInt(String s) {
    int result = 0;
    for (int i = s.charAt(0) == '-' ? 1 : 0; i < s.length(); i++) {
      int digit = s.charAt(i);
      result = result * 10 + digit;
    }
    return s.charAt(0) == '-' ? -result : result;
  }

  public String intoToString(int value) {
    boolean isNegative = false;
    if (value < 0) {
      isNegative = true;
      value = -value;
    }
    StringBuilder sb = new StringBuilder();
    do {
      sb.append(value % 10);
      value = value / 10;
    } while (value > 0);

    if (isNegative) {
      sb.append('-');
    }
    sb.reverse();
    return sb.toString();
  }
}
