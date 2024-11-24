package LLD.chess;

public class Piece {
    private Color color;
    private ChessPosition chessPosition;
    private boolean killed;

    public Piece(Color color, ChessPosition chessPosition) {
        this.color = color;
        this.chessPosition = chessPosition;
    }

    public ChessPosition getChessPosition() {
        return chessPosition;
    }

    public Color getColor() {
        return color;
    }

    public boolean isKilled() {
        return killed;
    }
    public boolean canMove() {
        return!killed;
    }
    public void move(ChessPosition position) {
        this.chessPosition = position;
    }

    public String symbol() {
        String black_color_prefix = "\u001b[31;1m";
        String white_color_prefix = "\u001b[34;1m";
        String color_suffix = "\u001b[0m";
        String symbol = symbolImpl();
        if (getColor() == Color.BLACK) {

            return black_color_prefix + symbol + color_suffix;
        } else {

            return white_color_prefix + symbol + color_suffix;
        }
    }

    protected String symbolImpl() {
        return "";
    }
}
