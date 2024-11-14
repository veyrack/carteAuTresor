package entite;

import elements.Direction;
import elements.Position;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AventurierTest {

    @Test
    void testDeplacementSansObstacle() {
        Carte carte = new Carte(3, 3);
        Aventurier aventurier = new Aventurier("Indiana", 1, 1, Direction.N, "A");

        aventurier.deplacer(new Position(1, 0), carte);

        assertEquals(1, aventurier.getX(), "L'aventurier devrait être en (1,0)");
        assertEquals(0, aventurier.getY(), "L'aventurier devrait être en (1,0)");
    }

    @Test
    void testRotationGaucheEtDroite() {
        Aventurier aventurier = new Aventurier("Indiana", 1, 1, Direction.N, "G");

        aventurier.tournerGauche();
        assertEquals(Direction.O, aventurier.getDirection(), "L'aventurier devrait être orienté OUEST après une rotation à gauche");

        aventurier.tournerDroite();
        assertEquals(Direction.N, aventurier.getDirection(), "L'aventurier devrait être orienté NORD après une rotation à droite");
    }

    @Test
    void testCollecteTresor() {
        Carte carte = new Carte(3, 3);
        carte.ajouterTresor(1, 1, 2);

        Aventurier aventurier = new Aventurier("Indiana", 1, 1, Direction.N, "A");

        aventurier.collecterTresor(carte);
        assertEquals(1, aventurier.getTresorsCollectes(), "L'aventurier devrait avoir collecté 1 trésor");
        assertEquals(1, carte.getCase(1, 1).getNbTresors(), "Il devrait rester 1 trésor sur la case (1,1)");
    }
}
