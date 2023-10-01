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
import chess.pieces.Tower;

public class TowerTest {
    Plateau plateau;
    Player player1, player2;
    Piece tour, pion1, pion2;

    @Before
    public void initialize()
    {
        player1 = new Player(Team.WHITE);
        player2 = new Player(Team.BLACK);
        plateau = new Plateau(player1, player2);
        tour = new Tower(5, 5, Team.WHITE);
        pion1 = new Pion(5, 0, Team.BLACK);
        pion2 = new Pion(5, 2, Team.BLACK);
    }

    @Test
    public void testCanMove()
    {
        plateau.setPiece(5, 5, tour);
        plateau.setPiece(5, 0, pion1);
        plateau.setPiece(5, 2, pion2);
        assertTrue(tour.canMove(5, 7, plateau)); // la tour va vers la droite
        assertTrue(tour.canMove(1, 5, plateau)); // la tour va vers le haut
        assertTrue(tour.canMove(7, 5, plateau)); // la tour va vers le bas
        assertTrue(tour.canMove(5, 2, plateau)); // la tour va vers la gauche est mange le pion sur cette case
        assertFalse(tour.canMove(5, 0, plateau)); // la tour va vers la gauche mais ne peut se déplacer à cause d'un pion qui lui barre le chemin
    }
}
