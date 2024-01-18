package guidetocompetitiveprogramming;

public class PatternMatching {
    public static void main(String[] args) {
        String s = "ABCABABCA";
        String pattern = "ABC";
        System.out.println(findFirstIndex(s, pattern));
        System.out.println(findLastIndex(s, pattern));
    }

    public static int findFirstIndex(String s, String pattern) {
        RollingHash rollingHash = new RollingHash(s);
        int patternHas = RollingHash.getHash(pattern);
        for (int i = 0; i + pattern.length() - 1 < s.length(); i++) {
            if (rollingHash.getHash(i, i + pattern.length() - 1) == patternHas) {
                if (s.startsWith(pattern, i)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static int findLastIndex(String s, String pattern) {
        int patternHash = RollingHash.getHash(pattern);
        RollingHash rollingHash = new RollingHash(s);
        for (int i = s.length() - pattern.length(); i >= 0; i--) {

            int hash = rollingHash.getHash(i, i + pattern.length() - 1);
            if (hash == patternHash) {
                if (s.startsWith(pattern, i)) {
                    return i;
                }
            }
        }
        return -1;
    }
}
