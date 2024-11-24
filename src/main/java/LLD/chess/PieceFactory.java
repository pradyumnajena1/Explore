package LLD.chess;

public class PieceFactory {

    public static Piece createPiece(PieceType type,ChessPosition position, Color color) {

        if (type == PieceType.KING){

            return new King(  color,position);
        }

        if ( type == PieceType.QUEEN){

            return new  Queen(color,position);
        }

        if ( type == PieceType.KNIGHT){

            return new  Knight(color,position);
        }
        if ( type == PieceType.ROCK){

            return new  Rock(color,position);
        }

        if ( type == PieceType.BISHOP){

            return new  Bishop(color,position);
        }

        if ( type == PieceType.PAWN){

            return new  Pawn(color,position);
        }

        throw new IllegalArgumentException("Invalid piece type");
    }
}
