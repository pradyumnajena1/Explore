package epp.hashmap.revision;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SmallestSubArrayCoveringAllUniqueValues {
    public static void main(String[] args) {
        List<String> paragraph = Arrays.asList("apple", "banana", "apple", "apple", "banana", "orange", "apple", "banana");
        Set<String> keywords = new HashSet<>(Arrays.asList("banana", "orange"));
        Range range =SmallestSubArrayCoveringAllValues.findSmallestSubArrayCover(paragraph, keywords);
        System.out.println(range);
    }
}
