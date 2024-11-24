package clr.heaps;

import epp.array.ArrayUtils;

import java.util.Arrays;

/**
 * An m n Young tableau is an m n matrix such that the entries of each row are in sorted order from
 * left to right and the entries of each column are in sorted order from top to bottom. Some of the
 * entries of a Young tableau may be 1, which we treat as nonexistent elements. Thus, a Young
 * tableau can be used to hold r mn finite numbers.
 */
public class YoungTableaus {
  private int m;
  private int n;
  private int[][] tableau;
  private int size = 0;

  public YoungTableaus(int m, int n) {
    this.m = m;
    this.n = n;
    tableau = new int[m][n];
    for (int i = 0; i < m; i++) {
      Arrays.fill(tableau[i], Integer.MAX_VALUE);
    }
  }

  public void insert(int value) {
    if (isFull()) {
      throw new IllegalStateException("The Young Tableau is full.");
    }

    int row = size / n ;
    int col = size % n;
    tableau[row][col] = value;
    shiftUp(row, col);
    size++;
  }

  public boolean contains(int value){
    int row = 0;
    int col = n-1;
    while(row<m&&col>=0){
      if(tableau[row][col]==value){
        return true;
      } else if(tableau[row][col]<value){
        row++;
      } else{
        col--;
      }
    }
    return false;

  }

  private void shiftUp(int i, int j) {
    int max = tableau[i][j];
    int maxx = i;
    int maxy = j;
    if (i > 0 && tableau[i - 1][j] > max) {
        maxx = i - 1;
        maxy = j;
        max = tableau[i - 1][j];
    }

    if (j > 0 && tableau[i][j - 1] > max) {
      maxx = i ;
      maxy = j-1;
      max = tableau[i][j-1];
    }
    if(!(maxx==i&&maxy==j)) {
      ArrayUtils.swap(tableau,i,j,maxx,maxy);
      shiftUp(maxx,maxy);
    }
  }

  public int extractMin() {
    if (isEmpty()) {
      throw new IllegalStateException("The Young Tableau is empty.");
    }
    int min = tableau[0][0];
    tableau[0][0] = Integer.MAX_VALUE;
    shiftDown(0, 0);
    return min;
  }

  private void shiftDown(int i, int j) {

      int min = tableau[i][j];
      int minx = i, miny = j;
      if (i + 1 < m && tableau[i + 1][j]<min) {
        min =   tableau[i + 1][j];
        minx = i + 1;
        miny = j;
      }
      if (j + 1 < n && tableau[i ][j+1]<min) {
        min =  tableau[i][j + 1];
        minx = i;
        miny = j + 1;
      }
      if ( !(minx==i && miny==j) ) {
        ArrayUtils.swap(tableau,i,j,minx,miny);

        shiftDown(minx,miny);
      }

  }

  public boolean isEmpty() {
    return tableau[0][0] == Integer.MAX_VALUE;
  }

  public boolean isFull() {
    return tableau[m - 1][n - 1] < Integer.MAX_VALUE;
  }

  public static void main(String[] args){
    YoungTableaus tableau = new YoungTableaus(3,4);
    for(int i = 0; i <3;i++){
      for(int j=0;j<4;j++){
        tableau.insert(1+(int) (Math.random()*100));
      }
    }
    ArrayUtils.print2DArray(tableau.tableau);
    System.out.println(tableau.extractMin());
    ArrayUtils.print2DArray(tableau.tableau);
    System.out.println(tableau.contains(55));

  }
}
