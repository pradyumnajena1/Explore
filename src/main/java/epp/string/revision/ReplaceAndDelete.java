package epp.string.revision;

import epp.array.ArrayUtils;

public class ReplaceAndDelete {
    public static void main(String[] args) {
        String s="xyabcbac  ";
        char[] charArray = s.toCharArray();
        removeAndReplace(charArray,'b','a',"ddd");
        ArrayUtils.printArray(charArray);
    }

    /**
     * remove each occurrence of remove char and replace each occurrences of replace char by replace string
     * @param array
     * @param remove char to be removed
     * @param replace char to be replaced by replace string
     * @param replaceBy
     */
    private static void removeAndReplace(char[] array, char remove, char replace, String replaceBy) {
        int writeIndex = 0;
        int replaceCount=0;
        int i=0;
        while (array[i++]!= ' '){
            if (array[i]!=remove){
              array[writeIndex++] = array[i];
          }
          if(array[i]==replace){
              replaceCount++;
          }
      }

        int newLength = writeIndex + replaceCount*(replaceBy.length()-1);

        int readIndex  = writeIndex-1;
        writeIndex = newLength-1;
        for(  i=readIndex;i>=0;i--){

            if(array[i] == replace){
                for(int j=replaceBy.length()-1;j>=0;j--){
                    array[writeIndex--] = replaceBy.charAt(j);
                }
            }else{
                array[writeIndex--] = array[i];
            }
        }



    }

}
