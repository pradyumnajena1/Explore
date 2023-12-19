package epp.graph.revision;

import java.util.*;

public class TransformOneStringToAnother {
    public static void main(String[] args) {
        String s = "god";
        String t = "dog";
        HashSet<String> dict = new HashSet<>();
        dict.add("dog");
        dict.add("god");
        dict.add("fog");
        dict.add("fod");
        List<String> path = transform(s,t, dict);
        System.out.println(path);
    }

    private static List<String> transform(String s, String t, HashSet<String> dict) {
        Queue<String> bfsQueue = new ArrayDeque<>();
        Set<String> visitedSet = new HashSet<>();
        Map<String,String> parentMap = new HashMap<>();
        boolean success = false;
        bfsQueue.offer(s);
        visitedSet.add(s);
        while (!bfsQueue.isEmpty()){
            String polled = bfsQueue.poll();
            if(polled.equals(t)){
                success = true;
            }
            List<String> neighbours = getNeighbours(polled);
            for(String neighbour:neighbours){
                if(!visitedSet.contains(neighbour) && dict.contains(neighbour)){
                    visitedSet.add(neighbour);
                    parentMap.put(neighbour,polled);
                    bfsQueue.offer(neighbour);
                }
            }

        }
        if(success){
            List<String> path  = new ArrayList<>();
            String current = t;
            do{
                path.add(current);
                current = parentMap.get(current);
            }while (current!=null);
            return path;
        }
        return null;
    }

    private static List<String> getNeighbours(String polled) {
        List<String> result = new ArrayList<>();
        char[] chars = polled.toCharArray();
        for (int i = 0; i < chars.length; i++) {

            for(char ch = 'a';ch<'z';ch++){
                if(ch!=chars[i]){
                    String newString = new String(polled.substring(0, i) + ch + polled.substring(i + 1));
                    result.add(newString);
                }
            }
        }
        return result;
    }
}
