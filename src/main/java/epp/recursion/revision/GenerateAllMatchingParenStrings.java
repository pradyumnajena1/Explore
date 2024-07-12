package epp.recursion.revision;

import java.util.HashSet;
import java.util.Set;

public class GenerateAllMatchingParenStrings {
    public static void main(String[] args) {
        Set<String> parens = generateAllMatchingParens(4);
        System.out.println(parens);


    }



    private static Set<String> generateAllMatchingParens(int n) {
        String partialResult = "";
        Set<String> result = new HashSet<>();
          generateAllMatchingParens(n, n, partialResult, result);
          return result;
    }

    private static void generateAllMatchingParens(int openingRemains, int closingRemains, String partialResult
            , Set<String> result) {

        if (openingRemains == 0 && closingRemains == 0) {
            result.add(partialResult);
            return;
        }
        if (openingRemains > 0) {
            generateAllMatchingParens(openingRemains-1  , closingRemains, partialResult + "(", result);
        }

        if (closingRemains > openingRemains) {
            generateAllMatchingParens(openingRemains, closingRemains-1  , partialResult + ")", result);
        }

    }
}
