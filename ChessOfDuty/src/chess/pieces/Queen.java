package chess.pieces;

import chess.Plateau;
import chess.Team;


public class Queen extends Piece{

    Bishop bishop;
    Tower tower;

    public Queen(int l, int c, Team team) {
        super(l, c, team);
        this.type = PieceType.QUEEN;
        this.bishop = new Bishop(l, c, team);
        this.tower = new Tower(l, c, team);
    }

    public boolean canMove(int l,int c, Plateau plateau){
        return bishop.canMove(l, c, plateau) || tower.canMove(l, c, plateau);
    }
    
}
