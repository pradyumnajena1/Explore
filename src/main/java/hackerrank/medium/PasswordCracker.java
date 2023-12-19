package hackerrank.medium;

import java.util.*;

public class PasswordCracker {
    public static void main(String[] args) {
        System.out.println(passwordCracker(new ArrayList<>(List.of("abra", "ka", "dabra")), "abrakadabra"));
        System.out.println(passwordCracker(new ArrayList<>(List.of("ab", "ba")), "aba"));
        System.out.println(passwordCracker(new ArrayList<>(List.of("because", "we", "can", "do", "must", "we", "what")), "wedowhatwemustbecausewecan"));
    }

    public static String passwordCracker(List<String> passwords, String loginAttempt) {
        // Write your code here
        HashSet<String> dict = new HashSet<>(passwords);
        Map<String, List<String>> cache = new HashMap<>();
        List<String> words = passwordCracker(dict, loginAttempt, cache);
        System.out.println(words);
        return words == null ? "WRONG PASSWORD" : String.join(" ", words);
    }

    private static List<String> passwordCracker(HashSet<String> dict, String loginAttempt, Map<String, List<String>> cache) {
        if (dict.contains(loginAttempt)) {
            ArrayList<String> result = new ArrayList<>();
            result.add(loginAttempt);
            return result;

        }
        if (cache.containsKey(loginAttempt)) {
            return cache.get(loginAttempt);
        }
        ArrayList<String> result = null;
        for (int i = 1; i < loginAttempt.length(); i++) {
            String prefix = loginAttempt.substring(0, i);
            if (dict.contains(prefix)) {
                String suffix = loginAttempt.substring(i);

                List<String> partialResult = passwordCracker(dict, suffix, cache);

                if (dict.contains(prefix) && partialResult != null) {
                    result = new ArrayList<>();
                    result.add(prefix);
                    result.addAll(partialResult);
                    cache.put(loginAttempt, result);
                    break;
                }
            }
        }
        cache.put(loginAttempt, result);
        return result;
    }
}
