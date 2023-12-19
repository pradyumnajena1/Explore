package hackerrank.easy.dp;

public class SubStringDiff {
    public static void main(String[] args) {
        System.out.println(substringDiff(0, "abacba", "abcaba"));
    }

    public static int substringDiff(int k, String s1, String s2) {
        return substringDiff(k, s1, 0, s1.length() - 1, s2, 0, s2.length() - 1);

    }

    private static int substringDiff(int k, String s1, int s1Start, int s1End, String s2, int s2Start, int s2End) {

        if (k == 0) {
            int maxCount = 0;
            for (int i = s1Start; i <= s1End; i++) {
                for (int j = s1Start; j <= s2End; j++) {

                    int count = 0;
                    while (i + count <= s1End && j + count <= s2End && s1.charAt(i + count) == s2.charAt(j + count)) {
                        count++;
                    }
                    maxCount = Math.max(count, maxCount);
                }
            }
            return maxCount;

        }
        int maxCount = 0;
        if(s1.charAt(s1Start)==s2.charAt(s2Start) && s1.charAt(s1End)==s2.charAt(s2End)){
            int countFront =substringDiff(k,s1,s1Start+1,s1End,s2,s2Start+1,s2End);
            int countBack = substringDiff(k,s1,s1Start,s1End-1,s2,s2Start,s2End-1);
            maxCount =1+ Math.max(countFront,countBack);

        }else if(s1.charAt(s1Start)==s2.charAt(s2Start)){
            maxCount =1+ Math.max(
                     substringDiff(k,s1,s1Start+1,s1End,s2,s2Start+1,s2End) ,

                    substringDiff(k-1,s1,s1Start,s1End+1,s2,s2Start,s2End+1)
            );
        }else if(s1.charAt(s1End)==s2.charAt(s2End)){
            maxCount =1+ Math.max(
                    substringDiff(k-1,s1,s1Start+1,s1End,s2,s2Start+1,s2End) ,

                    substringDiff(k,s1,s1Start,s1End-1,s2,s2Start,s2End-1)   );
        }else{
                maxCount=  substringDiff(k-2,s1,s1Start+1,s1End,s2,s2Start+1,s2End);
        }

        return 0;
    }
}
