package epp;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;

public class Multiset<T> {
    private Map<T,Integer> map;

    public Multiset() {
        map = new HashMap<>();
    }

    public Multiset(Multiset<T> multiset) {
        if(multiset!=null){

            map = new HashMap<>(multiset.map);
        }else{
            map = new HashMap<>();
        }

    }

    public void add(T value){
        map.put(value,map.getOrDefault(value,0)+1);
    }
    public void remove(T value){
        Integer count = map.get(value);
        if(count>1){
            map .put(value,count-1);
        }else{
            map.remove(value);
        }
    }
    public boolean contains(T value){
        return map.containsKey(value);
    }

    public Integer getCount(T value){
        return map.get(value);
    }

    @Override
    public String toString() {
        String mapString = map.toString();
        return new StringJoiner(", ", Multiset.class.getSimpleName() + "[", "]")
                .add(mapString.substring(1,mapString.length()-1))
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Multiset<?> multiset = (Multiset<?>) o;

        return Objects.equals(map, multiset.map);
    }

    @Override
    public int hashCode() {
        return map != null ? map.hashCode() : 0;
    }
}
