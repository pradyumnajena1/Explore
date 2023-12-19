package epp.string.revision;

public class TextSearch {
    public static void main(String[] args) {
        String text = "Hello World!";
        String searchString = "llo";
        int index  = find(text,searchString);
        System.out.println(index);
    }

    private static int find(String text, String searchString) {
        int searchStringHash = getHashCode(searchString.toCharArray(),0,searchString.length()-1);
        char[] textChars = text.toCharArray();
        int rollingHash = getHashCode(textChars,0,searchString.length()-1);
        if(searchStringHash==rollingHash){
            return 0;
        }
        for(int i=1;i<=text.length()-searchString.length();i++){
              rollingHash = getRollingHash(rollingHash, textChars,i,i+searchString.length()-1);
              if(rollingHash==searchStringHash){
                  return i;
              }
        }
        return -1;
    }

    private static int getRollingHash(int currentHash, char[] charArray, int start, int end) {

        int outgoing =toDigit( charArray[start - 1]  );
        int outgoingValue = outgoing * (int) Math.pow(10, end - start);
        int incoming =toDigit( charArray[end]) ;
        return (currentHash - outgoingValue) * 10 + incoming;
    }

    private static int getHashCode(char[] charArray, int start, int end) {
        int hash = 0;
        for(int i=start;i<=end;i++){
            hash = hash*10+ toDigit(charArray[i]);
        }
        return hash;
    }

    private static int toDigit(char ch) {
        return ch - 'A';
    }
}
