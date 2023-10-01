package chess.test;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import chess.Plateau;
import chess.Player;
import chess.Team;
import chess.pieces.Pion;

public class PionTest {
    Pion pionNoir;
    Plateau plateau;
    Player playerOne;
    Player playerTwo;
    Pion pionFin;
    Pion pionBlanc;


      @Before public void initialize(){
       pionNoir = new Pion(4, 3, Team.BLACK);
       playerOne= new Player("Julien", Team.WHITE);
       playerTwo= new Player("Toto",Team.BLACK);
       plateau = new Plateau(playerOne, playerTwo);
       plateau.setPiece(4, 3, pionNoir);
       pionBlanc=new Pion(5, 2, Team.WHITE);
       plateau.setPiece(5, 2, pionBlanc);
    }

    @Test
    public void canMoveTest() {
        /*NOIR */
        /*Tester la ou il ne peut pas se déplacer */
        org.junit.Assert.assertFalse(pionNoir.canMove(0, 0, plateau));
        /*Avance un pion d'une case */
        org.junit.Assert.assertTrue(pionNoir.canMove(5, 3, plateau));
        /*Avance un pion de deux cases */
        org.junit.Assert.assertTrue(pionNoir.canMove(6, 3, plateau));
        /*Pion qui mange une autre pièce */ 
        org.junit.Assert.assertTrue(pionNoir.canMove(5, 2, plateau));

        /*BLANC*/
        /*Tester la ou il ne peut pas se déplacer */
        org.junit.Assert.assertFalse(pionBlanc.canMove(0, 0, plateau));
        /*Avance un pion d'une case */
        org.junit.Assert.assertTrue(pionBlanc.canMove(4, 2, plateau));
        /*Avance un pion de deux cases */
        org.junit.Assert.assertTrue(pionBlanc.canMove(3, 2, plateau));
        /*Pion qui mange une autre pièce */ 
        org.junit.Assert.assertTrue(pionBlanc.canMove(4, 3, plateau));
    }

    @Test
    public void testReachEnd()
    {
        pionBlanc.setPosColonne(0);
        pionBlanc.setPosLigne(0);
        plateau.setPiece(0, 0, pionBlanc);
        assertTrue(pionBlanc.reachedEnd(plateau));
        pionNoir.setPosColonne(7);
        pionNoir.setPosLigne(7);
        plateau.setPiece(7, 7, pionNoir);
        assertTrue(pionNoir.reachedEnd(plateau));
    }

}
