package chess.pieces;

import chess.Plateau;
import chess.Team;

public class Horse extends Piece {

    public Horse(int l, int c, Team team) {
        super(l, c, team);
        this.type = PieceType.KNIGHT;
    }

    public boolean canMove(int l, int c, Plateau plateau) {
        if (plateau.isEmpty(l, c) || (!plateau.isEmpty(l, c) && plateau.getPiece(l, c).getTeam() != this.team)) {
            if (this.getPosLigne() + 2 == l && (this.getPosColonne() + 1 == c || this.getPosColonne() - 1 == c)) {
                return true;
            } else if (this.getPosLigne() - 2 == l
                    && (this.getPosColonne() + 1 == c || this.getPosColonne() - 1 == c)) {
                return true;
            } else if (this.getPosColonne() + 2 == c && (this.getPosLigne() - 1 == l || this.getPosLigne() + 1 == l)) {
                return true;
            } else if (this.getPosColonne() - 2 == c && (this.getPosLigne() - 1 == l || this.getPosLigne() + 1 == l)) {
                return true;
            }
            return false;
        }
        return false;
    }

}
