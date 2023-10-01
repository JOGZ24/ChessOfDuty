package chess.pieces;

import chess.Plateau;
import chess.Team;

public class Tower extends Piece {

    public Tower(int l, int c, Team team) {
        super(l, c, team);
        this.type = PieceType.ROOK;
    }

    public boolean canMove(int l, int c, Plateau plateau) {
        boolean possible = true;

        if (plateau.isEmpty(l, c) || (!plateau.isEmpty(l, c) && plateau.getPiece(l, c).getTeam() != this.team)) {
            if (this.getPosLigne() == l) {
                if (c >= this.getPosColonne()) {
                    for (int i = this.getPosColonne() + 1; i <= c; i++) {
                        
                        if (!plateau.isEmpty(l, i)) {
                            if (i == c && plateau.getPiece(l, c).getTeam() != this.team) {
                                possible = true;
                            } else {
                                possible = false;
                            }

                            break;
                        }
                    }
                } else {
                    for (int i = this.getPosColonne() - 1; i >= c; i--) {
                        
                        if (!plateau.isEmpty(l, i)) {
                            if (i == c && plateau.getPiece(l, c).getTeam() != this.team) {
                                possible = true;
                            } else {
                                possible = false;
                            }

                            break;
                        }
                    }
                }
            } else if (this.getPosColonne() == c) {
                if (l > this.getPosLigne()) {
                    for (int i = this.getPosLigne() + 1; i <= l; i++) {

                        if (!plateau.isEmpty(i, c)) {
                            if (i == l && plateau.getPiece(l, c).getTeam() != this.team) {
                                possible = true;
                            } else {
                                possible = false;
                            }
                            break;
                        }
                    }
                } else {
                    for (int i = this.getPosLigne() - 1; i >= l; i--) {

                        if (!plateau.isEmpty(i, c)) {
                            if (i == l && plateau.getPiece(l, c).getTeam() != this.team) {
                                possible = true;
                            } else {
                                possible = false;
                            }
                            break;
                        }
                    }
                }
            } else {
                possible = false;
            }
        } else {
            possible = false;
        }
        return possible;
    }
}
