package administration;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            Simulation simulation = new Simulation();
            simulation.lireFichierEntree("src/main/resources/fichier_entree.txt");
            simulation.demarrer();
            simulation.ecrireFichierSortie("src/main/resources/fichier_sortie.txt");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
