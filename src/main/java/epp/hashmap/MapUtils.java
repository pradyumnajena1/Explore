package epp.hashmap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MapUtils {
    public static <T> Map<Character, Integer> getFrequencyMap(String word) {
        return getFrequencyMap(word.toCharArray());
    }

    public static Map<Character, Integer> getFrequencyMap(char[] array) {
        Map<Character, Integer> result = new HashMap<>();
        for(char ch:array){
            result.put(ch,result.getOrDefault(ch,0)+1);
        }
        return result;
    }
    public static Map<Integer, Integer> getFrequencyMap(int[] array) {
        Map<Integer, Integer> result = new HashMap<>();
        for(int value:array){
            result.put(value,result.getOrDefault(value,0)+1);
        }
        return result;
    }
    public static Map<String, Integer> getFrequencyMap(String[] array) {
        Map<String, Integer> result = new HashMap<>();
        for(String string:array){
            result.put(string,result.getOrDefault(string,0)+1);
        }
        return result;
    }
    public static int hashcode(String s)
    {
        int result = 1;
        int mult = 31;
        for(char c:s.toCharArray()){
            result = result*mult+c ;
        }
        return result;
    }


    public static Map<Character, Long> getCharFrequency(String value) {

        if(value==null||value.isEmpty()){
            return new HashMap<>();
        }
        List<Character> list = value.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
        return getFrequencies(list);
    }
    public static <T> Map<T,Long> getFrequencies(List<T> values){

        Map<T, Long> collect = values.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return collect;
    }

    public static <T,V> Map<V,Integer> getFrequencies(List<T> values, Function<? super T,? extends V> keySelector){
        Map<V,Integer> frequencyMap = new HashMap<>();
        for(T value:values){
            frequencyMap.put(keySelector.apply(value),frequencyMap.getOrDefault(keySelector.apply(value),0)+1);
        }
        return frequencyMap;
    }
    public static <K,V> String toString(Map<K,V> map){
        String prefix = "[";
        String suffix = "]";
        return toString(map, prefix, suffix);
    }

    public static <K, V> String toString(Map<K, V> map, String prefix, String suffix) {
        StringBuilder stringBuilder = new StringBuilder( );
        stringBuilder.append(prefix);
        for (Map.Entry<K, V> entry : map.entrySet()) {
            stringBuilder.append(System.lineSeparator()+ entry.getKey() +":"+entry.getValue() );
        }
        stringBuilder.append(System.lineSeparator()).append(suffix).append(System.lineSeparator());
        return stringBuilder.toString();
    }
}
