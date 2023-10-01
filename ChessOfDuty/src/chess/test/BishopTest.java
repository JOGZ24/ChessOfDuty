package chess.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import chess.Plateau;
import chess.Player;
import chess.Team;
import chess.pieces.Bishop;
import chess.pieces.Piece;
import chess.pieces.Pion;

public class BishopTest {
    Player player1, player2;
    Plateau plateau;
    Piece bishop, pion1, pion2;

    @Before
    public void initialize()
    {
        player1 = new Player(Team.WHITE);
        player2 = new Player(Team.BLACK);
        plateau = new Plateau(player1, player2);
        bishop = new Bishop(5, 5, Team.WHITE);
        pion1 = new Pion(3, 3, Team.BLACK);
        pion2 = new Pion(4, 4, Team.BLACK);
    }

    @Test
    public void testCanMove()
    {
        plateau.setPiece(5, 5, bishop);
        plateau.setPiece(3, 3, pion1);
        plateau.setPiece(4, 4, pion2);
        assertTrue(bishop.canMove(4, 4, plateau)); // le fou se déplace en diagonale haut gauche et mange le pion
        assertTrue(bishop.canMove(6, 6, plateau)); // le fou se déplace en diagonale bas droit
        assertTrue(bishop.canMove(7, 3, plateau)); // le fou se déplace en bas gauche
        assertTrue(bishop.canMove(3, 7, plateau)); // le fou se déplace en haut droit
        assertFalse(bishop.canMove(3, 3, plateau)); // le fou se déplace en haut gauche mais ne peut atteindre la case car un pion lui barre le chemin
    }
}
