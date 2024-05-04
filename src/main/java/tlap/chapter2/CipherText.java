package tlap.chapter2;

import epp.array.ArrayUtils;

public class CipherText {
    public static void main(String[] args) {
        char[] mapping = getMapping();
        String encodedString = encode("HELLOWORLD", mapping);
        System.out.println(encodedString);
        String decodedString = decode(encodedString, mapping);
        System.out.println(decodedString);
    }

    private static char[] getMapping() {
        char[] charArray = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
        ArrayUtils.shuffle(charArray);
        return charArray;
    }

    private static String encode(String text, char[] mapping) {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<text.length();i++){
            sb.append(mapping[text.charAt(i)-'A']);
        }
        return sb.toString();
    }

    private static String decode(String encoded, char[] mapping) {
        StringBuilder sb = new StringBuilder();
        char[]  decodeMapping = new char[mapping.length];
        for(int i=0;i<mapping.length;i++){
            decodeMapping[mapping[i]-'A'] = (char) ('A'+i);
        }
        for(int i=0;i<encoded.length();i++){
            sb.append(decodeMapping[encoded.charAt(i)-'A']);
        }
        return sb.toString();
    }
}
