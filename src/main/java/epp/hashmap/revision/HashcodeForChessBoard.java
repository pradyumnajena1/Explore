package epp.hashmap.revision;

import java.math.BigInteger;

public class HashcodeForChessBoard {

    public static final int PiceHashLength = 4;

    public static void main(String[] args) {
        Square square = new Square(ChessPiece.HORSE, COLOR.BLACK);
        Square square2 = new Square(ChessPiece.QUEEN, COLOR.WHITE);
        Square[] board = new Square[64];
        board[0] = square;
        board[1] = square2;
        board[2] = square;
        board[3] = square2;
        BigInteger hash = getHash(board);
        System.out.println(hash.toString(2));
        hash = movePieceFrom(1,0,hash);
        System.out.println(hash.toString(2));


    }

    private static BigInteger getHash(Square[] squares) {
        BigInteger result = new BigInteger(new byte[8]);
        System.out.println(result.toString(2));
        for (int i = 0; i < squares.length; i++) {
            Square currentSquare = squares[i];
            if (currentSquare != null) {

                result = placePieceAt(i, result, currentSquare);
            } else {
                result = removePieceAt(i, result);
            }
        }
        return result;
    }

    private static BigInteger placePieceAt(int location, BigInteger result, Square currentSquare) {
        BigInteger pieceHash = currentSquare.getHash();
        return placePieceAt(location, result, pieceHash);
    }

    private static BigInteger placePieceAt(int location, BigInteger result, BigInteger pieceHash) {
        int offset = location * PiceHashLength;
        for (int i = 0; i < PiceHashLength; i++) {
            if (pieceHash.testBit(i)) {
                result = result.setBit(offset + i);
            } else {
                result = result.clearBit(offset + i);
            }
        }
        return result;
    }

    private static BigInteger movePieceFrom(int from, int to, BigInteger result) {
        BigInteger pieceHash = getPieceHashFromBoard(from, result);
        System.out.println(pieceHash.toString(2));
        result = removePieceAt(from, result);
        result = placePieceAt(to, result, pieceHash);
        return result;
    }

    private static BigInteger getPieceHashFromBoard(int location, BigInteger result) {
        BigInteger pieceHash = BigInteger.valueOf(15l);
        int fromOffSet = location * PiceHashLength;
        for (int i = 0; i < PiceHashLength; i++) {
            if (!result.testBit(fromOffSet + i)) {
                pieceHash=  pieceHash.clearBit(i);
            }
        }
        return pieceHash;
    }


    private static BigInteger removePieceAt(int location, BigInteger result) {
        int offset = location * PiceHashLength;
        for (int i = 0; i < PiceHashLength; i++) {

            result = result.clearBit(offset + i);
        }
        return result;
    }

    private enum ChessPiece {
        KING(6), QUEEN(5), ROCK(4), BISHOP(3), HORSE(2), SOLDIER(1);


        private final int ordinate;

        ChessPiece(int ordinate) {
            this.ordinate = ordinate;
        }

        int getHash() {
            return ordinate;
        }
    }

    private enum COLOR {
        BLACK(0), WHITE(1);

        private final int index;

        COLOR(int i) {
            this.index = i;
        }

        int getHash() {
            return index << 3;
        }
    }

    private static class Square {
        ChessPiece piece;
        COLOR color;

        public Square(ChessPiece piece, COLOR color) {
            this.piece = piece;
            this.color = color;
        }

        BigInteger getHash() {
            long l = (color.getHash() | piece.getHash());
            return BigInteger.valueOf(l);
        }
    }
}
