package LLD.chess;

public class King extends Piece{

    private Board board;

    public King(Color color, ChessPosition chessPosition) {
        super(color, chessPosition);
    }

    public void setBoard(Board board) {
        this.board = board;
        this.board.registerKingPosition(this.getChessPosition(),this.getColor());
    }

    @Override
    public boolean canMove() {
        return super.canMove();
    }

    @Override
    public String symbol() {
        return  "King";
    }
}

