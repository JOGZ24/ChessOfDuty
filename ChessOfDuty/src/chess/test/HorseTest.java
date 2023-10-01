package chess.test;

import org.junit.Before;
import org.junit.Test;

import chess.Plateau;
import chess.Player;
import chess.Team;
import chess.pieces.Horse;
import chess.pieces.Pion;

public class HorseTest {
   Horse horse;
   Plateau plateau;
   Player joueur1;
   Player joueur2;
   Pion pion;
   Pion pion2;



    @Before public void initialize(){
      horse= new Horse(3, 3, Team.BLACK);
      joueur1= new Player("Julien", Team.BLACK);
      joueur2= new Player("Pierre",Team.WHITE);
      plateau= new Plateau(joueur1,joueur2);
      pion= new Pion(5, 4, Team.WHITE);
      pion2= new Pion(5, 2, Team.BLACK);
      plateau.setPiece(3, 3, horse);
    }

    @Test
    public void canMoveTest() {
      /*La ou il ne peut pas aller */
      org.junit.Assert.assertFalse(horse.canMove(0, 0, plateau));
      /*De tout les cotés ou il peut aller */
      org.junit.Assert.assertTrue(horse.canMove(5, 4, plateau));
      org.junit.Assert.assertTrue(horse.canMove(5, 2, plateau));
      org.junit.Assert.assertTrue(horse.canMove(1, 4, plateau));
      org.junit.Assert.assertTrue(horse.canMove(1, 2, plateau));
      /*Il peut manger un pion adverse*/
      plateau.setPiece(5, 4, pion);
      org.junit.Assert.assertTrue(horse.canMove(5, 4, plateau));
      /*Il ne peut pas manger un pion allié */
      plateau.setPiece(5, 2, pion2);
      org.junit.Assert.assertFalse(horse.canMove(5, 2, plateau));
    }
}
