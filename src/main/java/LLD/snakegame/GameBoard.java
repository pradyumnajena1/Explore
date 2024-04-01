package LLD.snakegame;

public class GameBoard {

    private int rows;
    private int columns;

    public GameBoard(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }
}
