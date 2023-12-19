package hackerrank.easy;

public class HighestValuePalindrome {
    public static void main(String[] args) {
        //  System.out.println(highestValuePalindrome("9349", 2, 1));
        System.out.println(highestValuePalindrome("3943", 2, 2));
        System.out.println(highestValuePalindrome("092282", 2, 3));
        System.out.println(highestValuePalindrome("0011", 2, 1));
        System.out.println(highestValuePalindrome("0110", 2, 0));
    }

    public static String highestValuePalindrome(String s, int n, int k) {
        // Write your code here
        int palindrome = highestValuePalindrome(s, 0, s.length() - 1, k);

        StringBuilder stringBuilder = new StringBuilder(palindrome + "");
        while (stringBuilder.length()<s.length()){
            stringBuilder.insert(0,'0');
        }
        String palindromeString = stringBuilder.toString();
        return palindrome > 0 ? palindromeString : "-1";
    }

    public static int highestValuePalindrome(String s, int start, int end, int k) {
       // System.out.println("s = " + s + ", start = " + start + ", end = " + end + ", k = " + k);
        if (start > end) {
            return 0;
        }
        if (start == end) {
            if (k == 0) {
                return s.charAt(start);
            } else if (k >= 1) {
                return 9;
            }
        }

        if (start + 1 == end) {
            if (k == 0) {
                return s.charAt(start) == s.charAt(end) ? Integer.parseInt(s.substring(start, end + 1)) : 0;
            } else if (k == 1) {
                if (s.charAt(start) > s.charAt(end)) {

                    return Integer.parseInt(s.substring(start, start + 1) + s.substring(start, start + 1));
                } else {
                    return Integer.parseInt(s.substring(end, end + 1) + s.substring(end, end + 1));
                }
            } else if (k >= 2) {
                return 99;
            }
        }

        if (s.charAt(start) == s.charAt(end)) {
            if (s.charAt(start) == '9') {
                int palindrome = highestValuePalindrome(s, start + 1, end - 1, k);
                if (palindrome > 0) {

                    return Integer.parseInt("9" + palindrome + "9");
                }
                return 0;
            } else {
                int palindrome = highestValuePalindrome(s, start + 1, end - 1, k - 2);
                if (palindrome > 0) {
                    return Integer.parseInt("9" + palindrome + "9");
                }
                palindrome = highestValuePalindrome(s, start + 1, end - 1, k);
                if (palindrome > 0) {
                    return Integer.parseInt(s.substring(start, start + 1) + palindrome + s.substring(end, end + 1));
                }
                return 0;

            }

        } else {
            int palindrome = highestValuePalindrome(s, start + 1, end - 1, k - 2);
            if (palindrome > 0) {
                return Integer.parseInt("9" + palindrome + "9");
            }
            palindrome = highestValuePalindrome(s, start + 1, end - 1, k - 1);
            if (palindrome > 0) {
                if(s.charAt(start)>s.charAt(end)){

                    return Integer.parseInt(s.substring(start,start+1)  + palindrome + s.substring(start,start+1));
                }else{
                    return Integer.parseInt(s.substring(end,end+1)  + palindrome + s.substring(end,end+1));

                }

            }
            return 0;

        }

    }
}
