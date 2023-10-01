package chess.pieces;

import java.util.ArrayList;

import chess.Plateau;
import chess.Team;

public class King extends Piece {
    private boolean bloque;

    public King(int l, int c, Team team) {
        super(l, c, team);
        this.type = PieceType.KING;
        this.bloque = false;
    }

    public boolean canMove(int l,int c, Plateau plateau){
        boolean possible = false;
        if(plateau.isEmpty(l,c)  || (!plateau.isEmpty(l,c) && plateau.getPiece(l, c).getTeam() != this.team)) {
            if(this.getPosLigne()+1 == l && this.getPosColonne()+1 == c) possible = true;
            else if(this.getPosLigne() == l && this.getPosColonne()+1 == c) possible = true;
            else if(this.getPosLigne()+1 == l && this.getPosColonne() == c) possible = true;
            else if(this.getPosLigne()-1 == l && this.getPosColonne()+1 == c) possible = true;
            else if(this.getPosLigne()-1 == l && this.getPosColonne()-1 == c) possible = true;
            else if(this.getPosLigne()-1 == l && this.getPosColonne()+1 == c) possible = true;
            else if(this.getPosLigne()+1 == l && this.getPosColonne()-1 == c) possible = true;
            else if(this.getPosLigne() == l && this.getPosColonne()-1 == c) possible = true;
            else if(this.getPosLigne()-1 == l && this.getPosColonne() == c) possible = true;
            else possible = false;
        }
        if(possible) {
            if(this.team == Team.WHITE)
            {
                ArrayList<Piece> blackTeam = plateau.getBlackPiece();
                for(Piece piece : blackTeam) {
                    if(piece.canMove(l, c, plateau)) {
                        System.out.println(plateau.getPiece(l, c));
                        bloque = true;
                    }
                }
            }
            else {
                ArrayList<Piece> whiteTeam = plateau.getWhitePiece();
                for(Piece piece : whiteTeam) {
                    if(piece.canMove(l, c, plateau)) {
                        System.out.println(plateau.getPiece(l, c));
                        bloque = true;
                    }
                }
            }
        }
        return possible;
    }

    public boolean checkMate(Plateau plateau) {
        int ligne = this.getPosLigne()-1;
        int maxLigne = ligne+3;
        int col = this.getPosColonne()-1;
        int maxCol = this.getPosColonne() + 3;
        boolean echec = false;
        if(ligne < 0)
        {
            ligne++;
        }else if(maxLigne > plateau.getsizeX())
        {
            maxLigne = plateau.getsizeX();
        }
        if(col < 0)
        {
            col++;
        }else if(maxCol > plateau.getSizeY())
        {
            maxCol = plateau.getSizeY();
        }
        for(int l=ligne;l<maxLigne && !echec;l++)
        {
            for(int c=col;c<maxCol+1;c++)
            {
                if(!canMove(l, c, plateau))
                {
                    if(!plateau.isEmpty(l, c))
                    {
                        if(plateau.getPiece(l, c).getTeam() == this.team)
                        {
                            echec = false;
                        }else if(!bloque)
                        {
                            echec = true;
                        }
                        
                    }
                    else if(plateau.isEmpty(l, c) && bloque)
                    {
                        System.out.println(l+":"+c);
                        echec = true;
                        break;
                    }
                }else{
                    echec = false;
                }
            }
        }
        return echec;
    }
    
}
