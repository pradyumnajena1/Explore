package LLD.chess;

import java.util.List;

public class GameState {
    private List<Piece> pieces ;
    int size;

    public GameState(List<Piece> pieces, int size) {
        this.pieces = pieces;
        this.size = size;
    }
}
