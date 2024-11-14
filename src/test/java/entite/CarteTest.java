package entite;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarteTest {

    @Test
    void testAjouterMontagne() {
        Carte carte = new Carte(3, 3);
        carte.ajouterMontagne(1, 1);

        assertFalse(carte.estAccessible(1, 1), "La case (1,1) devrait être inaccessible à cause de la montagne");
        assertTrue(carte.estAccessible(0, 0), "La case (0,0) devrait être accessible");
    }

    @Test
    void testAjouterTresor() {
        Carte carte = new Carte(3, 3);
        carte.ajouterTresor(2, 2, 3);

        assertEquals(3, carte.getCase(2, 2).getNbTresors(), "La case (2,2) devrait contenir 3 trésors");
    }
}
