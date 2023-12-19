package epp.dp;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MinEditDistance {
    public static void main(String[] args) {
        String missplelled ="intention";
        String word ="execution";

        int editDistance = getEditDistance(missplelled,word);
        System.out.println(editDistance);
    }
    private static class Key{
        String misspelled;

        String word;


        public Key(String misspelled,   String word ) {
            this.misspelled = misspelled;

            this.word = word;

        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Key{");
            sb.append("misspelled='").append(misspelled).append('\'');
            sb.append(", word='").append(word).append('\'');
            sb.append('}');
            return sb.toString();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Key key = (Key) o;
            return   Objects.equals(misspelled, key.misspelled) && Objects.equals(word, key.word);
        }

        @Override
        public int hashCode() {
            return Objects.hash(misspelled,  word );
        }
    }

    private static int getEditDistance(String missplelled, String word) {
        Map<Key,Integer> cache = new HashMap<>();
        int editDistance = getEditDistance(missplelled, word, cache);
        System.out.println(cache);
        return editDistance;
    }

    private static int getEditDistance(String missplelled,  String word,Map<Key,Integer> cache) {
        if(word.equals(missplelled)){
            return 0;
        }
        if(missplelled.equals("") ){
            return word.length();
        }
        if(word.equals("")){
            return missplelled.length();
        }

        Key key = new Key(missplelled, word);
        if(cache.containsKey(key)){
            return cache.get(key);
        }
        int count = 0;
        if(missplelled.charAt(0)==word.charAt(0)){
            count = getEditDistance(missplelled.substring(1),word.substring(1), cache);
        }else{

            int delete =1+ getEditDistance(missplelled.substring(1), word, cache);
            int replace =1+ getEditDistance(missplelled.substring(1), word.substring(1), cache);
            int insert =1+ getEditDistance(missplelled, word.substring(1), cache);
            count =Math.min( Math.min(
                    //delete
                            delete,
                    //replace
                            replace),
                    //insert
                    insert
            );
        }
        cache.put(key,count);

        return count;
    }
}
