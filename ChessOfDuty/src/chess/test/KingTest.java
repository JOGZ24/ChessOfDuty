package chess.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import chess.Plateau;
import chess.Player;
import chess.Team;
import chess.pieces.King;
import chess.pieces.Piece;
import chess.pieces.Pion;
import chess.pieces.Tower;

public class KingTest {
    Player player1;
    Player player2;
    Plateau plateau;
    Piece king, pion, tour1,tour2;

    @Before
    public void initialize()
    {
        player1 = new Player(Team.WHITE);
        player2 = new Player(Team.BLACK);
        plateau = new Plateau(player1, player2);
        king = new King(4, 4, Team.WHITE);
        // pion = new Pion(0, 1, Team.BLACK);
        // tour1 = new Tower(5, 1, Team.BLACK);
        // tour2 = new Tower(7, 6, Team.BLACK);
    }

    @Test
    public void testCanMove()
    {
        /*Test sans pion ennemi */
        plateau.setPiece(4, 4, king);
        assertTrue(king.canMove(4, 5, plateau));
        assertTrue(king.canMove(5, 5, plateau));
        assertTrue(king.canMove(3, 5, plateau));
        assertTrue(king.canMove(3, 4, plateau));
        assertTrue(king.canMove(4, 3, plateau));
        assertTrue(king.canMove(5, 3, plateau));
        assertTrue(king.canMove(3, 3, plateau));
        assertTrue(king.canMove(5, 4, plateau));
        plateau.setPiece(4, 4, null);
        /*Test avec pion ennemi */
        pion = new Pion(0, 1, Team.BLACK);
        plateau.addPiece(pion, Team.BLACK);
        

        king.setPosColonne(0);
        king.setPosLigne(0);
        plateau.setPiece(0, 0, king);
        // plateau.setPiece(0, 1, pion);
        assertTrue(king.canMove(0, 1, plateau));
        /*Test avec mise en échec */
        tour1 = new Tower(5, 1, Team.BLACK);
        plateau.addPiece(tour1, Team.BLACK);
        
        plateau.setPiece(0, 1, null);
        plateau.setPiece(0, 0, king);
        king.setPosColonne(0);
        king.setPosLigne(0);
        assertFalse(king.canMove(0, 1, plateau)); 
    }

    @Test
    public void testCheckMate() 
    {
        king.setPosColonne(0);
        king.setPosLigne(0);
        tour2 = new Tower(5, 0, Team.BLACK);
        plateau.addPiece(tour2, Team.BLACK);
        assertTrue(((King) king).checkMate(plateau));
    }
}


