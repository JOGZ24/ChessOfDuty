package chess.pieces;

public enum PieceType {
    PAWN('♟', 10),
    KNIGHT('♞', 20),
    BISHOP('♝', 20),
    ROOK('♜', 20),
    QUEEN('♛', 40),
    KING('♚', -1);


    final char CHESS_MAN;
    final int DEFAULT_PV; // -1 = hp infini

    PieceType(char chessMan, int pvMax) {
        this.CHESS_MAN = chessMan;
        this.DEFAULT_PV = pvMax;
    }
    
    PieceType(char chessMan) {
        this(chessMan, 1);
    }

    public char getChessMan() {
        return this.CHESS_MAN;
    }
}
