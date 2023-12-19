package hackerrank.medium;

import epp.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CrosswordPuzzle {
    public static void main(String[] args) {


        ArrayList<String> crossword = new ArrayList<>();
        crossword.add("+-++++++++");
        crossword.add("+-++-+++++");
        crossword.add("+-------++");
        crossword.add("+-++-+++++");
        crossword.add("+-++-+++++");
        crossword.add("+-++-+++++");
        crossword.add("++++-+++++");
        crossword.add("++++-+++++");
        crossword.add("++++++++++");
        crossword.add("----------");
        System.out.println(crosswordPuzzle(crossword, new String("CALIFORNIA;NIGERIA;CANADA;TELAVIV")));
    }

    public static List<String> crosswordPuzzle(List<String> crossword, String words) {
        // Write your code here
        int maxRow = crossword.size() - 1;
        int maxCol = crossword.get(0).length() - 1;
        List<String> names = splitWords(words);
        List<StringBuilder> crosswordSb = toStringBuilder(crossword);
        crosswordPuzzleHelper(crosswordSb, maxRow, maxCol, names);
        ArrayList<String> result = toString(crosswordSb);
        return result;

    }

    private static ArrayList<String> toString(List<StringBuilder> crosswordSb) {
        ArrayList<String> result = new ArrayList<>();
        for (StringBuilder sb : crosswordSb) {
            result.add(sb.toString());
        }
        return result;
    }

    private static List<StringBuilder> toStringBuilder(List<String> crossword) {
        List<StringBuilder> crosswordSb = new ArrayList<>();
        for (String s : crossword) {
            crosswordSb.add(new StringBuilder(s));
        }
        return crosswordSb;
    }

    private static boolean crosswordPuzzleHelper(List<StringBuilder> crosswordSb, int maxRow, int maxCol,
                                                 List<String> names) {
        if (names.size() == 0) {
            return true;
        }
        System.out.println(names);
        Pair<Integer, Integer> emptyCell = getEmptyCell(crosswordSb);
        if (emptyCell == null) {
            return true;

        }
        RegexHolder regexIncludingPosition = getRegexIncludingPosition(crosswordSb, emptyCell.getFirst(), emptyCell.getSecond(),
                maxRow, maxCol);

        System.out.println(regexIncludingPosition);
        String matchingName = null;
        for (String name : names) {
            if (regexIncludingPosition.isMatching(name)) {
                matchingName = name;


                regexIncludingPosition.apply(crosswordSb, matchingName);
                List<String> newNames = new ArrayList<>(names);
                newNames.remove(matchingName);
                printCrossWord(crosswordSb);
                boolean success = crosswordPuzzleHelper(crosswordSb, maxRow, maxCol, newNames);
                if (success) {
                    return true;
                }
                regexIncludingPosition.unapply(crosswordSb);

            }
        }

        return false;


    }

    private static void printCrossWord(List<StringBuilder> crosswordSb) {
        for (int i = 0; i < crosswordSb.size(); i++) {
            System.out.println(crosswordSb.get(i));
        }
    }

    public static Pair<Integer, Integer> getEmptyCell(List<StringBuilder> crosswordSb) {
        for (int i = 0; i < crosswordSb.size(); i++) {
            for (int j = 0; j < crosswordSb.get(i).length(); j++) {
                if (crosswordSb.get(i).charAt(j) == '-') {
                    return new Pair<>(i, j);
                }
            }
        }
        return null;
    }

    private static List<String> splitWords(String words) {
        String[] split = words.split(";");
        List<String> list = Arrays.stream(split).collect(Collectors.toList());
        return list;
    }

    private static RegexHolder getRegexIncludingPosition(List<StringBuilder> crossword, int row, int col, int maxRow,
                                                         int maxCol) {
        int rightCol = col;
        int leftCol = col;
        while (rightCol <= maxCol && crossword.get(row).charAt(rightCol) != '+') {
            rightCol++;
        }
        while (leftCol >= 0 && crossword.get(row).charAt(leftCol) != '+') {
            leftCol--;
        }
        int horizontalLength = rightCol - leftCol + 1;
        int topRow = row;
        int bottomRow = row;
        while (bottomRow <= maxRow && crossword.get(bottomRow).charAt(col) != '+') {
            bottomRow++;
        }
        while (topRow >= 0 && crossword.get(topRow).charAt(col) != '+') {
            topRow--;
        }
        int verticalLength = bottomRow - topRow + 1;
        if (horizontalLength > verticalLength) {
            String rowRegexString = getRowRegexString(crossword, leftCol + 1, rightCol - 1, row);
            rowRegexString = "^" + rowRegexString + "$";
            RowRegexHolder rowRegexHolder = new RowRegexHolder(rowRegexString, leftCol + 1, rightCol - 1, row);
            return rowRegexHolder;
        } else {
            String columnRegexString = getColumnRegexString(crossword, topRow + 1, bottomRow - 1, col);
            columnRegexString = "^" + columnRegexString + "$";
            ColumnRegexHolder columnRegexHolder = new ColumnRegexHolder(columnRegexString, topRow + 1, bottomRow - 1, col);
            return columnRegexHolder;
        }

    }

    private static String getColumnRegexString(List<StringBuilder> crossword, int topRow, int bottomRow, int col) {
        StringBuilder sb = new StringBuilder();
        for (int i = topRow; i <= bottomRow; i++) {
            sb.append(crossword.get(i).charAt(col));
        }
        return sb.toString();
    }

    private static String getRowRegexString(List<StringBuilder> crossword, int left, int right, int row) {

        return crossword.get(row).substring(left, right + 1);
    }

    private static interface RegexHolder {
        boolean isMatching(String string);

        void apply(List<StringBuilder> crossword, String value);

        void unapply(List<StringBuilder> crossword);
    }

    private static class ColumnRegexHolder implements RegexHolder {
        String regex;
        int startRow;
        int endRow;
        int col;


        public ColumnRegexHolder(String regex, int startRow, int endRow, int col) {
            this.regex = regex.replaceAll("-", ".");
            this.startRow = startRow;
            this.endRow = endRow;
            this.col = col;
        }

        @Override
        public boolean isMatching(String string) {
            Pattern pattern = Pattern.compile(this.regex);

            return pattern.matcher(string).matches();
        }

        @Override
        public void apply(List<StringBuilder> crossword, String value) {


            int readIndex = 0;
            for (int i = startRow; i <= endRow; i++) {
                crossword.get(i).setCharAt(col, value.charAt(readIndex++));
            }
        }
        @Override
        public void unapply(List<StringBuilder> crossword) {


            int readIndex = 1;
            for (int i = startRow; i <= endRow; i++) {
                crossword.get(i).setCharAt(col, regex.charAt(readIndex++));
            }
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", ColumnRegexHolder.class.getSimpleName() + "[", "]")
                    .add("regex='" + regex + "'")
                    .add("startRow=" + startRow)
                    .add("endRow=" + endRow)
                    .add("col=" + col)
                    .toString();
        }
    }

    private static class RowRegexHolder implements RegexHolder {
        String regex;
        int startColumn;
        int endCol;
        int row;

        public RowRegexHolder(String regex, int startColumn, int endCol, int row) {
            this.regex = regex.replaceAll("-", ".");
            this.startColumn = startColumn;
            this.endCol = endCol;
            this.row = row;
        }

        @Override
        public boolean isMatching(String string) {
            Pattern pattern = Pattern.compile(this.regex);

            return pattern.matcher(string).matches();
        }

        @Override
        public void apply(List<StringBuilder> crossword, String value) {
            int readIndex = 0;
            for (int i = startColumn; i <= endCol; i++) {
                crossword.get(row).setCharAt(i, value.charAt(readIndex++));
            }
        }
        @Override
        public void unapply(List<StringBuilder> crossword) {


            int readIndex = 1;
            for (int i = startColumn; i <= endCol; i++) {
                crossword.get(row).setCharAt(i, regex.charAt(readIndex++));
            }
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", RowRegexHolder.class.getSimpleName() + "[", "]")
                    .add("regex='" + regex + "'")
                    .add("startColumn=" + startColumn)
                    .add("endCol=" + endCol)
                    .add("row=" + row)
                    .toString();
        }
    }
}
