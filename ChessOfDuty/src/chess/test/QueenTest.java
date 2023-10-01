package chess.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import chess.Plateau;
import chess.Player;
import chess.Team;
import chess.pieces.Piece;
import chess.pieces.Pion;
import chess.pieces.Queen;

public class QueenTest {
    Player player1, player2;
    Plateau plateau;
    Piece queen, pion1, pion2, pion3;

    @Before
    public void initialize()
    {
        player1 = new Player(Team.WHITE);
        player2 = new Player(Team.BLACK);
        plateau = new Plateau(player1, player2);
        queen = new Queen(5, 5, Team.WHITE);
        pion1 = new Pion(5, 2, Team.BLACK);
        pion2 = new Pion(3, 3, Team.BLACK);
        pion3 = new Pion(4, 4, Team.BLACK);
    }

    @Test
    public void testCanMove()
    {
        plateau.setPiece(5, 5, queen);
        plateau.setPiece(5, 2, pion1);
        plateau.setPiece(3, 3, pion2);
        plateau.setPiece(4, 4, pion3);
        assertTrue(queen.canMove(5, 7, plateau)); // la reine va vers la droite
        assertTrue(queen.canMove(1, 5, plateau)); // la reine va vers le haut
        assertTrue(queen.canMove(7, 5, plateau)); // la reine va vers le bas
        assertTrue(queen.canMove(5, 2, plateau)); // la reine va vers la gauche est mange le pion sur cette case
        assertTrue(queen.canMove(4, 4, plateau)); // la reine se déplace en diagonale haut gauche et mange le pion
        assertTrue(queen.canMove(6, 6, plateau)); // la reine se déplace en diagonale bas droit
        assertTrue(queen.canMove(7, 3, plateau)); // la reine se déplace en bas gauche
        assertTrue(queen.canMove(3, 7, plateau)); // la reine se déplace en haut droit
        assertFalse(queen.canMove(3, 3, plateau)); // la reine se déplace en haut gauche mais ne peut atteindre la case car un pion lui barre le chemin
    }
}
