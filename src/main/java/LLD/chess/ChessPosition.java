package LLD.chess;

public class ChessPosition {
    int x;
    int y;

    Piece piece;


    public ChessPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
