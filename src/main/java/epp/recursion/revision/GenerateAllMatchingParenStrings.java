package epp.recursion.revision;

import java.util.HashSet;
import java.util.Set;

public class GenerateAllMatchingParenStrings {
    public static void main(String[] args) {
        Set<String> parens = generateAllMatchingParens(3);
        System.out.println(parens);

        parens = generateAllMatchingParens2(3);
        System.out.println(parens);
    }

    private static Set<String> generateAllMatchingParens(int n) {
        if (n == 1) {
            HashSet<String> result = new HashSet<>();
            result.add("()");
            return result;
        }
        Set<String> parens = generateAllMatchingParens(n - 1);
        Set<String> result = new HashSet<>();
        for (String paren : parens) {
            result.add("()" + paren);
            result.add(paren + "()");
            result.add("(" + paren + ")");
        }

        return result;
    }

    private static Set<String> generateAllMatchingParens2(int n) {
        String partialResult = "";
        Set<String> result = new HashSet<>();
          generateAllMatchingParens2(n, n, partialResult, result);
          return result;
    }

    private static void generateAllMatchingParens2(int openingRemains, int closingRemains, String partialResult
            , Set<String> result) {

        if (openingRemains == 0 && closingRemains == 0) {
            result.add(partialResult);
            return;
        }
        if (openingRemains > 0) {
            generateAllMatchingParens2(openingRemains-1  , closingRemains, partialResult + "(", result);
        }

        if (closingRemains > openingRemains) {
            generateAllMatchingParens2(openingRemains, closingRemains-1  , partialResult + ")", result);
        }

    }
}
