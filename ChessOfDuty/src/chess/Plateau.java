package chess;

import java.util.ArrayList;

import chess.errors.IncompatinleTeam;
import chess.pieces.Bishop;
import chess.pieces.Horse;
import chess.pieces.King;
import chess.pieces.Piece;
import chess.pieces.Pion;
import chess.pieces.Queen;
import chess.pieces.Tower;

public class Plateau {
    private Piece[][] plateau;
    private ArrayList<Piece> deadPieces = new ArrayList<Piece>();
    private ArrayList<Piece> blackPiece;
    private ArrayList<Piece> whitePiece;
    private Player playerOne;
    private Player playerTwo;
    private int sizeX;
    private int sizeY;
    private Player currentPlayer;

    public Player getCurrentplayer() {
        return this.currentPlayer;
    }

    public void setCurrentplayer(Player currentplayer) {
        this.currentPlayer = currentplayer;
    }

    private static final int DEFAULT_SIZE_X = 8;
    private static final int DEFAULT_SIZE_Y = 8;

    public Plateau(Player playerOne, Player playerTwo, int sizeX, int sizeY) {
        this.plateau = new Piece[sizeX][sizeY];
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.blackPiece = new ArrayList<>();
        this.whitePiece = new ArrayList<>();
        this.currentPlayer = this.playerOne;
    }

    public Plateau(Player playerOne, Player playerTwo) {
        this(playerOne, playerTwo, DEFAULT_SIZE_X, DEFAULT_SIZE_Y);
    }

    public ArrayList<Piece> getBlackPiece() {
        return blackPiece;
    }

    public ArrayList<Piece> getWhitePiece() {
        return whitePiece;
    }

    public Piece[][] getPlateau() {
        return plateau;
    }

    public void setPlateau(Piece[][] plateau) {
        this.plateau = plateau;
    }

    public ArrayList<Piece> getDeadPieces() {
        return deadPieces;
    }

    public void setDeadPieces(ArrayList<Piece> deadPieces) {
        this.deadPieces = deadPieces;
    }

    public Player getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(Player playerOne) {
        this.playerOne = playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(Player playerTwo) {
        this.playerTwo = playerTwo;
    }

    public int getsizeX() {
        return sizeX;
    }

    public void setsizeX(int sizeX) {
        this.sizeX = sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }

    public void setSizeY(int sizeY) {
        this.sizeY = sizeY;
    }

    public boolean isOutOfPlateau(int l, int c) {
        if (0 <= l && l <= getsizeX()) {
            if (0 <= c && c <= getSizeY()) {
                return true;
            }
        }
        return false;
    }

    public void display() {
        System.out.print("   ");
        for (int i = 0; i < sizeY; i++) {
            System.out.print(" " + (char) ( i + 'A') + "  ");
        }
        System.out.println();
        System.out.println("\033[0;34m" + "   -------------------------------" + "\033[0;34m");
        for (int l = 0; l < sizeX; l++) {
            System.out.print(l+1);
            for (int c = 0; c < sizeY; c++) {
                System.out.print(" | ");
                if (isEmpty(l, c)) {
                    System.out.print(" ");
                } else {
                    System.out.print(this.plateau[l][c].toString());
                }

            }
            System.out.print(" | ");
            System.out.println();
        }
        System.out.print("   ");
        System.out.println("--------------------------------");
        
        System.out.println();
    }

    public boolean isEmpty(int l, int c) {
        return this.getPiece(l, c) == null;

    }

    public Piece getPiece(int l, int c) {
        return plateau[l][c];
    }

    public Piece getPiece(int l, int c, Team team) throws IncompatinleTeam {
        if(!this.isEmpty(l,c))
        {
            if(plateau[l][c].getTeam() == team)
            {
                return plateau[l][c];
            }
        }
        throw new IncompatinleTeam();
    }

    public void setPiece(int l, int c, Piece piece) {
        this.plateau[l][c] = piece;
    }

    public void initialize() {
        /* Black team */
        setPiece(0, 0, new Tower(0, 0, Team.BLACK));
        setPiece(0, 1, new Horse(0, 1, Team.BLACK));
        setPiece(0, 2, new Bishop(0, 2, Team.BLACK));
        setPiece(0, 3, new Queen(0, 3, Team.BLACK));
        setPiece(0, 4, new King(0, 4, Team.BLACK));
        setPiece(0, 5, new Bishop(0, 5, Team.BLACK));
        setPiece(0, 6, new Horse(0, 6, Team.BLACK));
        setPiece(0, 7, new Tower(0, 7, Team.BLACK));

        for (int c = 0; c < sizeY; c++) {
            setPiece(1, c, new Pion(1, c, Team.BLACK));
        }

        for (int ligne = 0; ligne < 2; ligne++) {
            for (int col = 0; col < this.sizeY; col++) {
                this.blackPiece.add(getPiece(ligne, col));
            }
        }

        /* White team */
        setPiece(7, 0, new Tower(7, 0, Team.WHITE));
        setPiece(7, 1, new Horse(7, 1, Team.WHITE));
        setPiece(7, 2, new Bishop(7, 2, Team.WHITE));
        setPiece(7, 3, new Queen(7, 3, Team.WHITE));
        setPiece(7, 4, new King(7, 4, Team.WHITE));
        setPiece(7, 5, new Bishop(7, 5, Team.WHITE));
        setPiece(7, 6, new Horse(7, 6, Team.WHITE));
        setPiece(7, 7, new Tower(7, 7, Team.WHITE));

        for (int c = 0; c < sizeY; c++) {
            setPiece(6, c, new Pion(6, c, Team.WHITE));
        }

        for (int ligne = 7; ligne > 5; ligne--) {
            for (int col = 0; col < this.sizeY; col++) {
                this.whitePiece.add(getPiece(ligne, col));
            }
        }
    }

    public void move(int l, int c, int newL, int newC) {
        
        Piece currentPiece = this.getPiece(l, c);
        if(!this.isEmpty(newL, newC))
        {
            if(currentPiece.getTeam() == Team.WHITE)
            {
                this.blackPiece.remove(this.getPiece(newL, newC));
            }else
            {
                this.whitePiece.remove(this.getPiece(newL, newC));
            }
        }        setPiece(newL, newC, currentPiece);
        currentPiece.setPosColonne(newC);
        currentPiece.setPosLigne(newL);
        setPiece(l, c, null);
        if (currentPiece instanceof Pion && ((Pion)currentPiece).reachedEnd(this)) {
            setPiece(newL, newC, new Queen(newL, newC, currentPiece.getTeam()));
            if(currentPiece.getTeam() == Team.WHITE)
            {
                this.whitePiece.remove(currentPiece);
                this.whitePiece.add(getPiece(newL, newC));
            }else{
                this.blackPiece.remove(currentPiece);
                this.blackPiece.add(getPiece(newL, newC));
            }
            
            
            
        }
    }

    public void addPiece(Piece p, Team t) {
        if (t == Team.BLACK) {
            this.blackPiece.add(p);
        } else {
            this.whitePiece.add(p);
        }
    }

    public Player changePlayer() {
        if (currentPlayer == playerOne) {
            this.currentPlayer = playerTwo;
            return playerTwo;
        } else {
            this.currentPlayer = playerOne;
            return playerOne;
        }
    }

    public King getKing(Team t)
    {
        if(t == Team.BLACK)
        {
            for(Piece p : blackPiece)
            {
                if(p instanceof King)
                {
                    return (King) p;
                }
            }
        }else
        {
            for(Piece p : whitePiece)
            {
                if(p instanceof King)
                {
                    return (King) p;
                }
            }
        }
        return null;
    }
}
