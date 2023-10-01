package chess.pieces;

import chess.Plateau;
import chess.Team;

public class Bishop extends Piece {

    public Bishop(int l, int c, Team team) {
        super(l, c, team);
        this.type = PieceType.BISHOP;
    }

    public void move(int l, int c) {

    }

    public boolean isInsideOfMap(int l, int c, Plateau plateau) {
        if (0 <= l && l < plateau.getsizeX()) {
            if (0 <= c && c < plateau.getSizeY()) {
                return true;
            }
        }
        return false;
    }

    public boolean canMove(int l, int c, Plateau plateau) {
        if (isInsideOfMap(l, c, plateau)) {
            if (plateau.isEmpty(l, c) || plateau.getPlateau()[l][c].getTeam() != this.team) {

                int posl = getPosLigne();
                int posc = getPosColonne();
                boolean contact = false;

                while (isInsideOfMap(posl, posc, plateau) && !contact) {
                    posl++;
                    posc++;

                    if (posl == l && posc == c) {
                        return true;
                    }

                    if (isInsideOfMap(posl, posc, plateau) && !plateau.isEmpty(posl, posc)) {
                        contact = true;
                    }
                }
                posl = getPosLigne();
                posc = getPosColonne();
                contact = false;

                while (isInsideOfMap(posl, posc, plateau) && !contact) {
                    posl = posl - 1;
                    posc = posc - 1;

                    if (posl == l && posc == c) {
                        return true;
                    }

                    if (isInsideOfMap(posl, posc, plateau) && !plateau.isEmpty(posl, posc)) {
                        contact = true;
                    }
                }

                posl = getPosLigne();
                posc = getPosColonne();
                contact = false;

                while (isInsideOfMap(posl, posc, plateau) && !contact) {
                    posl = posl + 1;
                    posc = posc - 1;

                    if (posl == l && posc == c) {
                        return true;
                    }

                    if (isInsideOfMap(posl, posc, plateau) && !plateau.isEmpty(posl, posc)) {
                        contact = true;
                    }
                }

                posl = getPosLigne();
                posc = getPosColonne();
                contact = false;

                while (isInsideOfMap(posl, posc, plateau) && !contact) {
                    posl = posl - 1;
                    posc = posc + 1;

                    if (posl == l && posc == c) {
                        return true;
                    }

                    if (isInsideOfMap(posl, posc, plateau) && !plateau.isEmpty(posl, posc)) {
                        contact = true;
                    }
                }
            }
        }
        return false;
    }

}
