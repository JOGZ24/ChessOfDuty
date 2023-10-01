package chess.test;

import org.junit.Before;
import org.junit.Test;



import chess.Plateau;
import chess.Player;
import chess.Team;
import chess.pieces.Pion;

public class PlateauTest {
    Player player1;
    Player player2;
    Plateau plateau;
    Pion pionNoir ;
    Pion pionBlanc ;
    Pion pionNoir2 ;
    Pion pionBlanc2 ;
    

    @Before public void initialize(){
        player1 = new Player("Julien", Team.WHITE);
        player2 = new Player("Pierre",Team.BLACK);
        plateau = new Plateau(player1, player2);
        plateau.initialize();
        pionNoir= new Pion(0, 0, Team.BLACK);
        pionBlanc= new Pion(0, 0, Team.WHITE);
        plateau.addPiece(pionNoir, Team.BLACK);
        plateau.addPiece(pionBlanc, Team.WHITE);
        pionNoir2= new Pion(0, 0, Team.BLACK);
        pionBlanc2= new Pion(0, 0, Team.WHITE);
    }
    


    @Test
    public void isOutOfPlateauTest() {
        org.junit.Assert.assertFalse(plateau.isOutOfPlateau(17, 17));
        org.junit.Assert.assertTrue(plateau.isOutOfPlateau(1, 3));
        org.junit.Assert.assertFalse(plateau.isOutOfPlateau(112, 3));
        org.junit.Assert.assertTrue(plateau.isOutOfPlateau(7, 6));
    }

    @Test
    public void isEmptyTest(){
        org.junit.Assert.assertFalse(plateau.isEmpty(0, 0));
        org.junit.Assert.assertTrue(plateau.isEmpty(3, 3));
        org.junit.Assert.assertTrue(plateau.isEmpty(4, 5));
        org.junit.Assert.assertFalse(plateau.isEmpty(7, 7));
    }

    @Test
    public void addPieceTest(){
        org.junit.Assert.assertTrue(plateau.getBlackPiece().contains(pionNoir));
        org.junit.Assert.assertTrue(plateau.getWhitePiece().contains(pionBlanc));
        org.junit.Assert.assertFalse(plateau.getBlackPiece().contains(pionNoir2));
        org.junit.Assert.assertFalse(plateau.getWhitePiece().contains(pionBlanc2));
    }

   
  
    


}
