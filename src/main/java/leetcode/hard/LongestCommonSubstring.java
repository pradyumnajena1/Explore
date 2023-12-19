package leetcode.hard;

public class LongestCommonSubstring {
    public static void main(String[] args) {

        System.out.println(longestCommonSubstring("pqrabcdxyz", "xyzabcd"));
    }

    public static String longestCommonSubstring(String str1, String str2) {
        int[][] lcSuffix = new int[str1.length()+1][str2.length()+1];
        for(int i=1;i<=str1.length();i++){
            for(int j=1;j<=str2.length();j++){
                if(str1.charAt(i-1)==str2.charAt(j-1)){
                    lcSuffix[i][j] = lcSuffix[i-1][j-1]+1;
                }else{
                    lcSuffix[i][j] = 0;
                }
            }
        }
        int max = 0;
        String result = "";
        for (int i = 0; i < str1.length(); i++) {
            for (int j = 0; j < str2.length(); j++) {

                int length = lcSuffix[i+1][j+1];
                if (max < length) {
                    max = length;
                    result = str1.substring( i-length+1,i+1);
                }
            }

        }
        return result;
    }


}
