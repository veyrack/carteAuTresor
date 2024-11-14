package administration;

import static org.junit.jupiter.api.Assertions.*;

import elements.Direction;
import entite.Aventurier;
import entite.Carte;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

class SimulationTest {

    @Test
    void testSimulation() throws IOException {
        Carte carte = new Carte(3, 4);
        carte.ajouterMontagne(1, 1);
        carte.ajouterTresor(0, 3, 2);
        carte.ajouterTresor(1, 3, 1);
        Aventurier aventurier1 = new Aventurier("Lara", 1, 2, Direction.S, "ADA");
        Aventurier aventurier2 = new Aventurier("Indiana", 0, 2, Direction.E, "AAD");

        List<Aventurier> aventuriers = Arrays.asList(aventurier1, aventurier2);
        Simulation simulation = new Simulation(carte, aventuriers);
        simulation.demarrer();

        assertEquals(0, aventurier1.getX(), "Lara devrait finir en x : 0");
        assertEquals(3, aventurier1.getY(), "Lara devrait finir en y : 3");
        assertEquals(2, aventurier2.getX(), "Indiana devrait finir en x : 2");
        assertEquals(2, aventurier2.getY(), "Indiana devrait finir en y : 2");
        assertEquals(2, aventurier1.getTresorsCollectes(), "Lara devrait avoir collecté 1 trésor");
        assertEquals(0, aventurier2.getTresorsCollectes(), "Indiana devrait avoir collecté 1 trésor");
    }
}
