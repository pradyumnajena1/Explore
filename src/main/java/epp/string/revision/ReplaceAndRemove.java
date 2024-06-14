package epp.string.revision;

public class ReplaceAndRemove {
    public static void main(String[] args){
        char[] charArray = "abac ".toCharArray();
        replaceAndRemove(charArray,4,'b','a',"dd");
    System.out.println(new String(charArray));
    }

    public static void replaceAndRemove(char[] chars,int size,char delete,char replace,String replaceWith){
        int writeIndex = 0;
        int replaceCount = 0;
        int deleteCount = 0;
        for(int i=0;i<size;i++){
            if(chars[i] == replace){
                replaceCount++;
            }
            if(chars[i]!=delete){
                chars[writeIndex++] = chars[i];
            }else{
                deleteCount++;
            }
        }
        //copy
        int newLength = size -deleteCount + replaceCount*(replaceWith.length()-1);
        int readIndex = writeIndex-1;
          writeIndex = newLength-1;
        for(int i = readIndex;i>=0;i--){
            if(chars[i]==replace){
                for(int j=replaceWith.length()-1;j>=0;j--){
                    chars[writeIndex--] = replaceWith.charAt(j);
                }
            }else{
                chars[writeIndex--] = chars[i];
            }
        }
    }
}
