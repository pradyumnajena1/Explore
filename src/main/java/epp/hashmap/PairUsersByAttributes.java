package epp.hashmap;

import java.util.*;

public class PairUsersByAttributes {
    public static void main(String[] args) {
        Set<String>[] members = new Set[]{Set.of("sports","tech","music"),Set.of("sports","movies","music"),Set.of(
                "sports","tech","movies"),Set.of("sports","tech","music")};
        Set<Integer> unpaired = getUnpaired(members);
        System.out.println(unpaired);
    }

    private static Set<Integer> getUnpaired(Set<String>[] members) {

        Map<Set<String>, List<Integer>> mappings = new HashMap<>();
        for(int i=0;i<members.length;i++){
            List<Integer> sameAttrMembers = mappings.getOrDefault(members[i], new ArrayList<>());
            if(sameAttrMembers.size()>0){
                sameAttrMembers.remove(0);
            }else{
                sameAttrMembers.add(i);
                mappings.put(members[i],sameAttrMembers);
            }
        };
        System.out.println(mappings);
        Set<Integer> unmatched = new HashSet<>();
        for(Map.Entry<Set<String>, List<Integer>> entry:mappings.entrySet()){
            if(entry.getValue().size()>0){
                unmatched.add(entry.getValue().get(0));
            }
        }
        return unmatched;
    }
}
