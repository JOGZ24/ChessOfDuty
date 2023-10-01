package chess.pieces;

import chess.Team;
import chess.Plateau;

public class Pion extends Piece {
    private boolean firstStep;

    public Pion(int l, int c, Team team) {
        super(l, c, team);
        this.firstStep = true;
        this.type = PieceType.PAWN;
    }

    @Override
    public boolean canMove(int l, int c, Plateau plateau) {
        int oneStep;
        int twoStep;
        if(this.team == Team.WHITE)
        {
            oneStep = -1;
            twoStep = -2;
        }else
        {
            oneStep = 1;
            twoStep = 2;
        }
        if(plateau.isEmpty(l, c))
        {
            if(this.getPosLigne()+oneStep == l && c == this.getPosColonne())
            {
                return true;
            }else if((this.getPosLigne()+twoStep == l && c == this.getPosColonne()) && this.firstStep)
            {
                this.firstStep = false;
                return true;
            }else
            {
                return false;
            }
        
        }else if(!plateau.isEmpty(l, c) && plateau.getPiece(l, c).getTeam() != this.team)
        {
            if((this.getPosColonne()+1 == c || this.getPosColonne()-1 == c) && this.getPosLigne()+oneStep == l)
            {
                return true;
            }
            return false;
        }
        return false;
        
    }

    public boolean reachedEnd(Plateau plateau)
    {
        if(this.team == Team.BLACK && (this.getPosLigne() == plateau.getsizeX()-1))
        {
            return true;
        }else if(this.team == Team.WHITE && (this.getPosLigne() == 0))
        {
            return true;
        }
        return false;
    }
}
