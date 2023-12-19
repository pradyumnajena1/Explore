package epp.string;

import java.util.Arrays;

public class RemoveAndReplace {
    //replace a by dd and remove b
    public static void main(String[] args) {
        char[] chars =  new char[50];
        String s = "aabcdbabd";
        System.arraycopy(s.toCharArray(),0,chars,0,s.length());

        removeAndReplace(chars,s.length());
        System.out.println(Arrays.toString(chars));
    }

    public static void removeAndReplace(char[] chars, int length) {
        int newLength = 0;
        for(int i=0;i<length;i++){
            if(chars[i]=='a'){
                newLength+=2;
            }else if(chars[i]=='b'){

            }else{
                newLength++;
            }
        }
        System.out.println(newLength);
        int readIndex = length-1;
        int writeIndex = newLength-1;
        while (readIndex>=0){
            char aChar = chars[readIndex];
            if(aChar =='a'){
                chars[writeIndex--] = 'd';
                chars[writeIndex--] = 'd';
            }else if(aChar =='b'){

            }else {
                chars[writeIndex--] = aChar;
            }
            readIndex--;
        }

    }
}
