package leetcode.hard;

public class RegularExpressionMatching {
    public static void main(String[] args) {
       RegularExpressionMatching rem = new RegularExpressionMatching();
        System.out.println(rem.isMatch("ab", "a*"));
        System.out.println(rem.isMatch("ab", ".*"));
        System.out.println(rem.isMatch("ab", "*"));
    }
    public boolean isMatch(String s, String p) {
        if (s.isEmpty() && p.isEmpty()) {
            return true;
        } else if (s.isEmpty() || p.isEmpty()) {
            return false;
        }
        if (p.length() > 1) {
            String prefix = p.substring(0, 2);
            if (prefix.charAt(0) == '.' && prefix.charAt(1) == '*') {
                return true;
            } else if (prefix.charAt(0) == s.charAt(0) && prefix.charAt(1) == '*') {
                return true;
            } else if (prefix.charAt(0) == '*') {
                for (int i = 0; i < s.length(); i++) {
                    if (isMatch(s.substring(i), p.substring(1))) {
                        return true;
                    }
                }
                return false;

            }else{
                return prefix.charAt(0) == s.charAt(0) && isMatch(s.substring(1),p.substring(1));
            }

        } else {
            if(p.charAt(0)=='*'){

                return true;
            }
            if (p.charAt(0) == '.' || p.charAt(0) == s.charAt(0)) {
                return isMatch(s.substring(1), p.substring(1));
            }
            return false;
        }
    }

}
