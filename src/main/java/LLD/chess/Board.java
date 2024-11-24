package LLD.chess;

import java.util.ArrayList;
import java.util.List;

public class Board {

    private List<ChessPosition> boxes = new ArrayList<>();
    private List<Piece> pieces = new ArrayList<>();


    public List<Piece> getPieces() {
        return pieces;
    }

    public void resetBoard(){

    }

    public void registerKingPosition(ChessPosition chessPosition,Color color) {

    }

    public void executeMove(Command command) {

    }
}
