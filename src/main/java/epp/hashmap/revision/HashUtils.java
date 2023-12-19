package epp.hashmap.revision;

import epp.hashmap.MapUtils;

import java.util.*;
import java.util.stream.Collectors;

public class HashUtils {
    public static void main(String[] args) {
        int hashcode = MapUtils. hashcode("hello world  ");
        System.out.println( hashcode & 255);
        System.out.println(MapUtils. toString(Map.of("hello", 1, "world", 2)));
    }

}
