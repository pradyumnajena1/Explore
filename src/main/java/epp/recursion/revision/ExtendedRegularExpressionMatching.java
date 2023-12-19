package epp.recursion.revision;

public class ExtendedRegularExpressionMatching {
    public static void main(String[] args) {
        String re = "a*bc";
        String s = "abc";
        System.out.println(isMatching(s, re));
    for(int i=0;i<=s.length();i++){
        System.out.println(s.substring(0,i)+ "  " + s.substring(i,s.length()));
    }
    }

    private static boolean isMatching(String s, String re) {
        if (re.startsWith("^") && re.endsWith("$")) {
            return isStrictlyMatching(s, re.substring(1, re.length() - 1));
        }
        if (re.startsWith("^")) {
            for (int i = 0; i <= s.length(); i++) {
                if (isStrictlyMatching(s.substring(0, i), re)) {
                    return true;
                }
            }
        }
        if (re.endsWith("$")) {
            for (int i = s.length(); i >= 0; i--) {
                if (isStrictlyMatching(s.substring(i, s.length()), re)) {
                    return true;
                }
            }
        }
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                String substring = s.substring(i, j);
                System.out.println(substring);
                if (isStrictlyMatching(substring, re)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isStrictlyMatching(String s, String re) {
        System.out.println(s + " "+ re);
        if (re.length() == 0) {
            return true;
        }

        if (s.length() == 0) {
            return false;
        }

        if (re.length() >= 2) {

            if (re.charAt(0) == '.' && re.charAt(1) == '*') {
                //re = .*
                for (int i = 0; i <= s.length(); i++) {
                    String prefix = s.substring(0, i);
                    String suffix = s.substring(i, s.length());
                    if (isStrictlyMatching(suffix, re.substring(2))) {
                        return true;
                    }
                }

            } else if (re.charAt(1) == '*') {
                //re = a*

                for (int i = 0; i <= s.length(); i++) {
                    String prefix = s.substring(0, i);
                    String suffix = s.substring(i, s.length());

                    if (prefix.length() > 0 && !prefix.endsWith(re.substring(0, 1))) {
                        break;
                    }

                    if (isStrictlyMatching(suffix, re.substring(2))) {
                        return true;
                    }
                }

            } else if (re.charAt(1) == '.') {
                //re = a.
                if(s.length()<1){
                    return false;
                }
                return
                        re.charAt(0) == s.charAt(0) && isStrictlyMatching(s.substring(1), re.substring(1));

            } else {
                //re = ab
                if (s.length() < 2) {
                    return false;
                }
                return re.substring(0, 1).equals(s.substring(0, 1)) &&
                        isStrictlyMatching(s.substring(1), re.substring(1));
            }

        } else {
            if (re.charAt(0) == '.') {
                return isStrictlyMatching(s.substring(1), re.substring(1));
            } else {
                return re.charAt(0) == s.charAt(0) && isStrictlyMatching(s.substring(1), re.substring(1));
            }
        }
        return false;
    }
}
