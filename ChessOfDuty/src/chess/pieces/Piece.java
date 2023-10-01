package chess.pieces;

import chess.Plateau;
import chess.Team;

public abstract class Piece {
    protected int[] pos;
    protected Team team;
    protected PieceType type;

    public Piece(int l, int c, Team team) {
        this.pos = new int[2];
        this.pos[0] = l;
        this.pos[1] = c;
        this.team = team;
    }

    public abstract boolean canMove(int l, int c, Plateau plateau);

    public int[] getPos() {
        return this.pos;
    }

    public int getPosLigne() {
        return pos[0];
    }

    public int getPosColonne() {
        return pos[1];
    }

    public void setPosLigne(int l) {
        this.pos[0] = l;
    }

    public void setPosColonne(int c) {
        this.pos[1] = c;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public PieceType getPieceType() {
        return this.type;
    }

    public char getChessMan() {
        return type.getChessMan();
    }

    @Override
    public String toString() {
        return getTeam().getColor() + getChessMan() + "" + "\033[0m";
    }

}