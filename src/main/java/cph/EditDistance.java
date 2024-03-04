package cph;

import epp.array.ArrayUtils;

public class EditDistance {
    public static void main(String[] args) {
        String x = "LOVE";
        String y = "MOVIE";
        int editDistance = getEditDistance(x, y);
        System.out.println(editDistance);
    }

    private static int getEditDistance(String x, String y) {
        int[][] distance = new int[x.length() + 1][y.length() + 1];
        for (int i = 0; i <= y.length(); i++) {
            distance[0][i] = i;
        }
        for (int i = 0; i <= x.length(); i++) {
            distance[i][0] = i;
        }
        for (int i = 1; i <= x.length(); i++) {
            for (int j = 1; j <= y.length(); j++) {
                if (x.charAt(i - 1) == y.charAt(j - 1)) {
                    distance[i][j] = distance[i - 1][j - 1];
                } else {
                    distance[i][j] = 1 + Math.min(Math.min(distance[i - 1][j], distance[i][j - 1]), distance[i - 1][j - 1]);
                }

            }
        }
        ArrayUtils.print2DArray(distance);
        return distance[x.length()][y.length()];
    }
}
